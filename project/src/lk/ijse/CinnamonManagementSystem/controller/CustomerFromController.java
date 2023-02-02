package lk.ijse.CinnamonManagementSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.CinnamonManagementSystem.db.DBConnection;
import lk.ijse.CinnamonManagementSystem.model.CustomerModel;
import lk.ijse.CinnamonManagementSystem.to.Customer;
import lk.ijse.CinnamonManagementSystem.util.Navigation;
import lk.ijse.CinnamonManagementSystem.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFromController {
    public AnchorPane pane;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TableView <Customer> tblCustomer;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colMobile;
    public TableColumn colEmail;
    public JFXTextField txtSearch;
    public TextField txtMobile;
    public TextField txtEmail;
    public TextField txtNic;
    public TableColumn colNic;
    public Label txtTime;
    public Label txtCID;
    public Label txtCNAME;
    public Label txtCMobil;
    public Label txtCEmail;
    public Label txtCNic;
    private Matcher idMatcher;
    private Matcher nameMatcher;
    private Matcher emailMatcher;
    private Matcher nicMatcher;
    private Matcher teleNumMatcher;


    public void initialize() throws SQLException, ClassNotFoundException {
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Contact"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Email") );
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Nic") );

        getAllData();
       // setPaten();
        //timeNow();
    }
   /*private void timeNow() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        txtTime.setText(format.format(new Date()));

    }*/
 /*   public void setPaten(){
        Pattern idPattern = Pattern.compile("^(C0)([0-9]{1})([0-9]{1})$");
        idMatcher = idPattern.matcher(txtId.getText());

        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
        nameMatcher = namePattern.matcher(txtName.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,3})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());


        Pattern nicPattern = Pattern.compile("^([a-zA-Z0-9\\/,\\s])+([a-zA-z\\s,])+([a-zA-z])$");
        nicMatcher = nicPattern.matcher(txtNic.getText());

        Pattern teleNumPattern = Pattern.compile("^(074|075|072|077|071|078)([0-9]{7}$)");
        teleNumMatcher = teleNumPattern.matcher(txtMobile.getText());
    }*/
    public void btnRemoveOnAction(ActionEvent actionEvent) {
        emptyTextField();
    }



    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtMobile.getText();
        String email = txtEmail.getText();
        String nic=txtNic.getText();

        Customer customer = new Customer(id,name,address,contact,email,nic);
        boolean isUpdated =CustomerModel.update(customer);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated").show();
            emptyTextField();
            getAllData();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
            emptyTextField();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        Customer customer = new Customer(id);

        boolean isDeleted = CustomerModel.delete(customer);

        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Customer").show();
            emptyTextField();
            getAllData();
        }else{
            new Alert(Alert.AlertType.WARNING,"Something Worng").show();
            emptyTextField();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtMobile.getText();
        String email = txtEmail.getText();
        String nic = txtNic.getText();


        Customer customer = new Customer(id, name, address, contact, email, nic);

          /*  if(idMatcher.matches()){
                if(nameMatcher.matches()){
                    if (emailMatcher.matches()){
                        if(teleNumMatcher.matches()) {
                            if (nicMatcher.matches()) {*/
                                boolean isAdded = CustomerModel.save(customer);
                                if (isAdded) {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Added").show();
                                    emptyTextField();
                                    getAllData();
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Something Wrong Try again").show();
                                    emptyTextField();
                                }

                            } /*else {
                                txtCNic.requestFocus();
                                txtNic.setStyle("-fx-text-fill: red;");
                                txtNic.setText("Invalid  Nic");

                            }
                        }else {
                            txtCMobil.requestFocus();
                            txtMobile.setStyle("-fx-text-fill: red;");
                            txtMobile.setText("Invalid  Mobile");
                        }
                    }else {
                        txtCEmail.requestFocus();
                        txtEmail.setStyle("-fx-text-fill: red;");
                        txtEmail.setText("Invalid  Email");
                    }
                }else {
                    txtName.requestFocus();
                    txtName.setStyle("-fx-text-fill: red;");
                    txtCNAME.setText("Invalid Name Number");
                }
                        }else {
                txtId.requestFocus();
                txtId.setStyle("-fx-text-fill: red;");
                txtCID.setText("Invalid ID Number");
            }*/



    

    public void keyPressedEntergetData(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            String id = txtId.getText();
            Customer customer = CustomerModel.search(id);
            if (customer != null){
                fillData(customer);
            }

        }
    }

    public void btnSearchOnKeyPressed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            String id = txtSearch.getText();
            Customer customer = CustomerModel.search(id);
            if (customer != null){
                fillData(customer);
                txtSearch.clear();

            }

        }
    }

    public void btnBackOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.BACK,pane);
    }
    private void fillData(Customer customer){
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtMobile.setText(customer.getContact());
        txtEmail.setText(customer.getEmail());
        txtNic.setText(customer.getNic());
    }
    public void getAllData() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> AllData = CustomerModel.getAllCustomer();
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        for (Customer customer :
        AllData){
            observableList.add(new Customer(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getContact(),
                    customer.getEmail(),
                    customer.getNic()
            ));
        }
        tblCustomer.setItems(observableList);
    }
    public void emptyTextField(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtEmail.clear();
        txtNic.clear();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.CONFIRMATION,"Print Data").show();
    /*    InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/CinnamonManagementSystem/view/report/gayani.jrxml");


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());

            //                  JasperPrintManager.printReport(jasperPrint,true);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void idOnKeyReleased(KeyEvent keyEvent) {
        txtCID.setText("");
        txtId.setStyle("-fx-text-fill: blue;");
        Pattern idPattern = Pattern.compile("^(C0)([0-9]{1})([0-9]{1})$");
        idMatcher = idPattern.matcher(txtId.getText());

        if (!idMatcher.matches()) {
            txtId.requestFocus();
            txtId.setStyle("-fx-text-fill: red;");
            txtCID.setText("Invalid ID Number");
        }
    }

    public void nameOnKeyReleased(KeyEvent keyEvent) {
        txtCNAME.setText("");
        txtName.setStyle("-fx-text-fill: blue;");
        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
        nameMatcher = namePattern.matcher(txtName.getText());

        if (!nameMatcher.matches()) {
            txtName.requestFocus();
            txtName.setStyle("-fx-text-fill: red;");
            txtCNAME.setText("Invalid Name Number");
        }
    }

    public void mobileOnKeyReleased(KeyEvent keyEvent) {
        txtCMobil.setText("");
        txtMobile.setStyle("-fx-text-fill: blue;");
        Pattern teleNumPattern = Pattern.compile("^(074|075|072|077|071|078)([0-9]{7}$)");
        teleNumMatcher = teleNumPattern.matcher(txtMobile.getText());

        if (!teleNumMatcher.matches()) {
            txtMobile.requestFocus();
            txtMobile.setStyle("-fx-text-fill: red;");
            txtCMobil.setText("Invalid  TelePhone Number");
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

    public void nicOnKeyReleased(KeyEvent keyEvent) {
        txtCNic.setText("");
        txtNic.setStyle("-fx-text-fill: blue;");
        Pattern nicPattern = Pattern.compile("^([a-zA-Z0-9\\/,\\s])+([a-zA-z\\s,])+([a-zA-z])$");
        nicMatcher = nicPattern.matcher(txtNic.getText());

        if (!nicMatcher.matches()) {
            txtNic.requestFocus();
            txtNic.setStyle("-fx-text-fill: red;");
            txtCNic.setText("Invalid  Email");
        }
    }
}

