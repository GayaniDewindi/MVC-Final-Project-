package lk.ijse.CinnamonManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CinnamonManagementSystem.model.ItemModel;
import lk.ijse.CinnamonManagementSystem.model.SupplierOrderModel;
import lk.ijse.CinnamonManagementSystem.model.SupplierModel;
import lk.ijse.CinnamonManagementSystem.to.SupplierOrder;
import lk.ijse.CinnamonManagementSystem.util.Navigation;
import lk.ijse.CinnamonManagementSystem.util.Routes;
import lk.ijse.CinnamonManagementSystem.view.tm.SupplierOrderDetailsTm;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class SupplierOrderDetailsController implements Initializable {
    public AnchorPane pane;
    public Label lblCustomerName;
    public JFXComboBox cmbItemCode;
    public Label lblDescription;
    public Label lblOrderId;
    public Label lblOrderDate;
    public JFXComboBox cmbSupplierId;
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
    ObservableList<SupplierOrderDetailsTm> tm = FXCollections.observableArrayList();

    public void cmbItemOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = ItemModel.getDetails(String.valueOf(cmbItemCode.getValue()));
            if (set.next()) {
                lblDescription.setText(set.getString(1));
                lblUnitPrice.setText(set.getString(2));
               // lblQtyOnHand.setText(set.getString(3));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        tm.add(new SupplierOrderDetailsTm(
                String.valueOf(cmbItemCode.getValue()),
                lblDescription.getText(),
                txtQty.getText(),
                lblUnitPrice.getText(),
                String.valueOf(Double.parseDouble(lblUnitPrice.getText()) * Double.parseDouble(txtQty.getText()))
        ));
        tblOrderCart.refresh();
        cmbItemCode.getItems().clear();
        setItemIds();
        lblDescription.setText("");
        lblUnitPrice.setText("");
        txtQty.setText("");
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

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            if (SupplierOrderModel.setOrder(
                    new SupplierOrder(
                            lblOrderId.getText(),
                            lblOrderDate.getText(),
                            timeNow(),
                            String.valueOf(cmbSupplierId.getValue()),
                            total()

                    ),tm
            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
                tm.clear();
                tblOrderCart.getItems().clear();
                txtQty.setText("");
                cmbItemCode.getItems().clear();
                setOrderId();
                setCutIds();
                setItemIds();
                lblDescription.setText("");
                lblCustomerName.setText("");
                lblUnitPrice.setText("");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCutIds() {
        ArrayList<String> ids = new ArrayList<>();
        try {
            ResultSet set = SupplierModel.getAllIds();
            while (set.next()) {
                ids.add(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        cmbSupplierId.getItems().addAll(ids);
    }

    private void setOrderId() {
        try {
            ResultSet set = ItemModel.getLastId();
            if (set.next()) {
                String[] os = set.getString(1).split("O");
                int id = Integer.parseInt(os[1]);
                id++;
                lblOrderId.setText("O" + id);
            }else {
                lblOrderId.setText("O1");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
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
        Navigation.navigate(Routes.BACK,pane);
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

    private void setDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblOrderDate.setText(format.format(new Date()));
    }
}
