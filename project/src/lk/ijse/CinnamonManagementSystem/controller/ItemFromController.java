package lk.ijse.CinnamonManagementSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CinnamonManagementSystem.model.EmployeesModel;
import lk.ijse.CinnamonManagementSystem.model.ItemModel;
import lk.ijse.CinnamonManagementSystem.model.VehicleModel;
import lk.ijse.CinnamonManagementSystem.to.Employees;
import lk.ijse.CinnamonManagementSystem.to.Item;
import lk.ijse.CinnamonManagementSystem.to.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemFromController {
    public AnchorPane pane;
    public TextField txtId;
    public TextField txtName;
    public TextField txtPrice;
    public TableView<Item> tblItem;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn colDescriptions;
    public TextField txtDescription;
    public JFXTextField txtSearch;
    public TableColumn colqty;
    public TextField txtqty;


    public void initialize() throws SQLException, ClassNotFoundException {
        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Descriptions"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Price"));
        tblItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Qty"));
        getAllData();
    }


    public void btnRemoveOnAction(ActionEvent actionEvent) {
        emptyTextField();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        Item item= new Item(id);
        boolean isDeleted = ItemModel.delete(item);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Item").show();
            emptyTextField();
            getAllData();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
            emptyTextField();
        }
    }


    public void btnEnterSearchOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            String id = txtId.getText();
            Item item = ItemModel.search(id);
            if (item != null){
                fillData(item);
                txtSearch.clear();
            }

        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name=txtName.getText();
        String description=txtDescription.getText();
        String price=txtPrice.getText();
        String qty=txtqty.getText();
        Item item=new Item(id,name,price,description,qty);
        boolean isUpdated =ItemModel.update(item);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Updated").show();
            emptyTextField();
            getAllData();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
            emptyTextField();
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name=txtName.getText();
        String description=txtDescription.getText();
        String price=txtPrice.getText();
        String qty=txtqty.getText();
        Item item=new Item(id,name,description,price,qty);
        boolean isAdded = ItemModel.save(item);
        if (isAdded){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Added").show();
            emptyTextField();
            getAllData();
        }else{
            new Alert(Alert.AlertType.WARNING,"Something Wrong Try again").show();
            emptyTextField();
        }
    }
    private void fillData(Item item){
        txtId.setText(item.getItemCode());
        txtName.setText(item.getItemName());
        txtDescription.setText(item.getDescriptions());
        txtPrice.setText(item.getPrice());
        txtqty.setText(item.getQty());

    }
    public void getAllData() throws SQLException, ClassNotFoundException {
        ArrayList<Item> AllData = ItemModel.getAllItem();
        ObservableList<Item> observableList = FXCollections.observableArrayList();
        for (Item item :
                AllData){
            observableList.add(new Item(
                    item.getItemCode(),
                    item.getItemName(),
                    item.getPrice(),
                    item.getDescriptions(),
                    item.getQty()
            ));
        }
        tblItem.setItems(observableList);
    }
    public void emptyTextField(){
        txtId.clear();
        txtName.clear();
        txtDescription.clear();
        txtPrice.clear();
        txtqty.clear();
    }

    public void btnEnterSearchKeyPressed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            String id = txtSearch.getText();
            Item item = ItemModel.search(id);
            if (item != null){
                fillData(item);
            }

        }
    }
}
