package lk.ijse.CinnamonManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CinnamonManagementSystem.model.CustomerModel;
import lk.ijse.CinnamonManagementSystem.model.ItemModel;
import lk.ijse.CinnamonManagementSystem.model.OrderModel;
import lk.ijse.CinnamonManagementSystem.to.Order;
import lk.ijse.CinnamonManagementSystem.util.Navigation;
import lk.ijse.CinnamonManagementSystem.util.Routes;
import lk.ijse.CinnamonManagementSystem.view.tm.CustomerOrderDetailsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerOrderDetailsController implements Initializable {
    public AnchorPane pane;
    public Label lblOrderId;
    public Label lblOrderDate;
    public JFXComboBox cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox cmbItemCode;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView tblOrderCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public Label txtItemName;
    public Label txtCustName;
    public Label txtOderId;
    public Label txtDate;
    public Label txtPrice;
    public Label tblTotal;
    ObservableList<CustomerOrderDetailsTm> tm = FXCollections.observableArrayList();

    public void cmbItemOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = ItemModel.getDetails(String.valueOf(cmbItemCode.getValue()));
            if (set.next()) {
                txtItemName.setText(set.getString(1));
                txtPrice.setText(set.getString(2));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        tm.add(new CustomerOrderDetailsTm(
                String.valueOf(cmbItemCode.getValue()),
                txtItemName.getText(),
                txtQty.getText(),
                txtPrice.getText(),
                String.valueOf(Double.parseDouble(txtPrice.getText()) * Double.parseDouble(txtQty.getText()))

        ));
        tblOrderCart.refresh();
        gettotal();
        cmbItemCode.getItems().clear();
        setItemIds();
        txtItemName.setText("");
        txtPrice.setText("");
        txtQty.setText("");
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            if (OrderModel.setOrder(
                    new Order(
                            txtOderId.getText(),
                            txtDate.getText(),
                            timeNow(),
                            String.valueOf(cmbCustomerId.getValue()),
                            total()

                    ), tm
            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
                tm.clear();
                tblOrderCart.getItems().clear();
                txtQty.setText("");
                cmbCustomerId.getItems().clear();
                cmbItemCode.getItems().clear();
                setOrderId();
                setCutIds();
                setItemIds();
                txtItemName.setText("");
                txtCustName.setText("");
                txtPrice.setText("");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String total() {
        double total = 0;
        for (int i = 0; i < tm.size(); i++) {
            total += Double.parseDouble(tm.get(i).getTotal());
        }
        return String.valueOf(total);
    }

    private String timeNow() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        return format.format(new Date());

    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BACK, pane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void cmbCusId(ActionEvent actionEvent) {
        try {
            ResultSet set = CustomerModel.getCutomerDetails(String.valueOf(cmbCustomerId.getValue()));
            if (set.next()) {
                txtCustName.setText(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        setCutIds();
        setItemIds();
        setOrderId();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tblOrderCart.setItems(tm);

    }

    private void setOrderId() {
        try {
            ResultSet set = ItemModel.getLastId();
            if (set.next()) {
                String[] os = set.getString(1).split("O");
                int id = Integer.parseInt(os[1]);
                id++;
                txtOderId.setText("O" + id);
            }else {
                txtOderId.setText("O1");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setItemIds() {
        ArrayList<String> ids = new ArrayList<>();
        try {
            ResultSet set = ItemModel.getAllIds();
            while (set.next()) {
                ids.add(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        cmbItemCode.getItems().addAll(ids);

    }

    private void setCutIds() {
        ArrayList<String> ids = new ArrayList<>();
        try {
            ResultSet set = CustomerModel.getAllIds();
            while (set.next()) {
                ids.add(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        cmbCustomerId.getItems().addAll(ids);
    }

    private void setDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(format.format(new Date()));
    }
    public void gettotal(){
        double total=0;
        for (CustomerOrderDetailsTm ab:tm) {
            total += Double.parseDouble(String.valueOf(ab.getTotal()));
         //   total += ab.getTotal();
        }
        tblTotal.setText(String.valueOf(total));
    }

}
