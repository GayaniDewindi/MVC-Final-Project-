package lk.ijse.CinnamonManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class CustomerOrders {
    public AnchorPane pane;
    public TableView colCustomerOrder;
    public TableColumn colItemID;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colprice;
    public JFXComboBox cmbCusId;
    public Label txtItemName;
    public Label txtPrice;
    public JFXTextField combat;
    public Label txtTotal;
    public Label txtCusName;

    public void cmbCusIdOnAction(ActionEvent actionEvent) {
    }

    public void ADDOnAction(ActionEvent actionEvent) {
    }

    public void OkPlaceOnAction(ActionEvent actionEvent) {
    }
}
