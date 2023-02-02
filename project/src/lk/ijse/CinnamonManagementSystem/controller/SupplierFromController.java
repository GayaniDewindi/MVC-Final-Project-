package lk.ijse.CinnamonManagementSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CinnamonManagementSystem.model.CustomerModel;
import lk.ijse.CinnamonManagementSystem.model.SupplierModel;
import lk.ijse.CinnamonManagementSystem.to.Customer;
import lk.ijse.CinnamonManagementSystem.to.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierFromController {
    public TextField txtEmail;
    public TableColumn colAction;
    public TableColumn colSalary;
    public TableColumn colAddress;
    public TableColumn colName;
    public TableColumn colID;
    public TableView <Supplier>tblSupplier;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtName;
    public TextField txtId;
    public AnchorPane pane;
    public JFXTextField txtSearch;
    public Label txtCID;
    public Label txtCname;
    public Label txtCContact;
    public Label txtCEmail;
    private Matcher idMatcher;
    private Matcher nameMatcher;
    private Matcher emailMatcher;
    private Matcher nicMatcher;
    private Matcher teleNumMatcher;

    public void initialize() throws SQLException, ClassNotFoundException {
        tblSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Contact"));
        tblSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Email") );
        getAllData();
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        emptyTextField();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }


    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();
        String email=txtEmail.getText();


        Supplier supplier = new Supplier(id,name,address,contact,email);
        boolean isAdded = SupplierModel.save(supplier);
        if (isAdded){
            new Alert(Alert.AlertType.CONFIRMATION,"Supplier Added").show();
            emptyTextField();
        }else{
            new Alert(Alert.AlertType.WARNING,"Something Wrong Try again").show();
            emptyTextField();
        }
    }

    public void setPaten(){
        Pattern idPattern = Pattern.compile("^(S0)([0-9]{1})([0-9]{1})$");
        idMatcher = idPattern.matcher(txtId.getText());

        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
        nameMatcher = namePattern.matcher(txtName.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,3})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());


      //  Pattern nicPattern = Pattern.compile("^([a-zA-Z0-9\\/,\\s])+([a-zA-z\\s,])+([a-zA-z])$");
    //    nicMatcher = nicPattern.matcher(txtNic.getText());

        Pattern teleNumPattern = Pattern.compile("^(074|075|072|077|071|078)([0-9]{7}$)");
        teleNumMatcher = teleNumPattern.matcher(txtContact.getText());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        Supplier supplier = new Supplier(id,name,address,contact,email);
        boolean isUpdated = SupplierModel.update(supplier);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated").show();
            emptyTextField();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
            emptyTextField();
        }
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        Supplier supplier = new Supplier(id);

        boolean isDeleted = SupplierModel.delete(supplier);

        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Supplier").show();
            emptyTextField();
        }else{
            new Alert(Alert.AlertType.WARNING,"Something Worng").show();
            emptyTextField();
        }
    }
    public void enterKeyPressed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            String id = txtId.getText();
            Supplier supplier = SupplierModel.search(id);
            if (supplier != null){
                fillData(supplier);
            }

        }
    }
    public void btnSearchKeyPressed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            String id = txtSearch.getText();
            Supplier supplier = SupplierModel.search(id);
            if (supplier != null){
                fillData(supplier);
            }

        }
    }

        private void fillData(Supplier supplier ){
            txtId.setText(supplier.getId());
            txtName.setText(supplier.getName());
            txtAddress.setText(supplier.getAddress());
            txtContact.setText(supplier.getContact());
            txtEmail.setText(supplier.getEmail());
        }
        public void getAllData() throws SQLException, ClassNotFoundException {
            ArrayList<Supplier> AllData = SupplierModel.getAllSupplier();
            ObservableList<Supplier> observableList = FXCollections.observableArrayList();
            for (Supplier supplier1 :
                    AllData){
                observableList.add(new Supplier(
                        supplier1.getId(),
                        supplier1.getName(),
                        supplier1.getAddress(),
                        supplier1.getContact(),
                        supplier1.getEmail()
                ));
            }
            tblSupplier.setItems(observableList);
        }
    public void emptyTextField(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
    }


    public void idOnKeyReleased(KeyEvent keyEvent) {
        txtCID.setText("");
        txtId.setStyle("-fx-text-fill: blue;");
        Pattern idPattern = Pattern.compile("^(S0)([0-9]{1})([0-9]{1})$");
        idMatcher = idPattern.matcher(txtId.getText());

        if (!idMatcher.matches()) {
            txtId.requestFocus();
            txtId.setStyle("-fx-text-fill: red;");
            txtCID.setText("Invalid ID Number");
        }
    }

    public void nameOnKeyReleased(KeyEvent keyEvent) {
        txtCname.setText("");
        txtName.setStyle("-fx-text-fill: blue;");
        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
        nameMatcher = namePattern.matcher(txtName.getText());

        if (!nameMatcher.matches()) {
            txtName.requestFocus();
            txtName.setStyle("-fx-text-fill: red;");
            txtCname.setText("Invalid Name Number");
        }
    }

    public void contactOnKeyReleased(KeyEvent keyEvent) {
        txtCContact.setText("");
        txtContact.setStyle("-fx-text-fill: blue;");
        Pattern teleNumPattern = Pattern.compile("^(074|075|072|077|071|078)([0-9]{7}$)");
        teleNumMatcher = teleNumPattern.matcher(txtContact.getText());

        if (!teleNumMatcher.matches()) {
            txtContact.requestFocus();
            txtContact.setStyle("-fx-text-fill: red;");
            txtCContact.setText("Invalid  TelePhone Number");
        }
    }

    public void emailOnKeyReleased(KeyEvent keyEvent) {
        txtCEmail.setText("");
        txtEmail.setStyle("-fx-text-fill: blue;");
        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,3})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());

        if (!emailMatcher.matches()) {
            txtEmail.requestFocus();
            txtEmail.setStyle("-fx-text-fill: red;");
            txtCEmail.setText("Invalid  Email");
        }
    }
}
