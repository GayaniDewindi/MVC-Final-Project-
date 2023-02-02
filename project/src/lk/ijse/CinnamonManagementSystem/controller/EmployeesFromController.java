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
import lk.ijse.CinnamonManagementSystem.db.DBConnection;
import lk.ijse.CinnamonManagementSystem.model.CustomerModel;
import lk.ijse.CinnamonManagementSystem.model.EmployeesModel;
import lk.ijse.CinnamonManagementSystem.to.Customer;
import lk.ijse.CinnamonManagementSystem.to.Employees;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesFromController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;

    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colNic;
    public TextField txtNic;
    public AnchorPane pane;
    public TableView<Employees> tblEmployee;
    public JFXTextField txtSearch;

    public void initialize() throws SQLException, ClassNotFoundException {
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Contact"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Nic") );
        getAllData();
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();
        String nic=txtNic.getText();

        Employees employees = new Employees(id,name,address,contact,nic);
        boolean isAdded = EmployeesModel.save(employees);
        if (isAdded){
            new Alert(Alert.AlertType.CONFIRMATION,"Employee Added").show();
            emptyTextField();
            getAllData();
        }else{
            new Alert(Alert.AlertType.WARNING,"Something Wrong Try again").show();
            emptyTextField();
        }

    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {

        emptyTextField();
    }
    

    public void btnBackOnAction(ActionEvent actionEvent) {
    }
    

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        Employees employees = new Employees(id);

        boolean isDeleted = EmployeesModel.delete(employees);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Employees").show();
            emptyTextField();
            getAllData();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
            emptyTextField();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String nic = txtNic.getText();

        Employees employees = new Employees(id,name,address,contact,nic);
        boolean isUpdated =EmployeesModel.update(employees);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Employees Updated").show();
            emptyTextField();
            getAllData();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
            emptyTextField();
        }

    }

    public void btnSearchOnActin(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            String id = txtSearch.getText();
            Employees employees = EmployeesModel.search(id);
            if (employees != null){
                fillData(employees);
                txtSearch.clear();
            }

        }
    }

    public void enterKeyPressedOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            String id = txtId.getText();
            Employees employees = EmployeesModel.search(id);
            if (employees != null){
                fillData(employees);
            }

        }
    }
    private void fillData(Employees employees){
        txtId.setText(employees.getId());
        txtName.setText(employees.getName());
        txtAddress.setText(employees.getAddress());
        txtContact.setText(employees.getContact());
        txtNic.setText(employees.getNic());
    }
    public void getAllData() throws SQLException, ClassNotFoundException {
        ArrayList<Employees> AllData = EmployeesModel.getAllEmployees();
        ObservableList<Employees> observableList = FXCollections.observableArrayList();
        for (Employees employees :
                AllData){
            observableList.add(new Employees(
                    employees.getId(),
                    employees.getName(),
                    employees.getAddress(),
                    employees.getContact(),
                    employees.getNic()
            ));
        }
        tblEmployee.setItems(observableList);
    }
    public void emptyTextField(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtNic.clear();
    }

    public void reportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/CinnamonManagementSystem/view/report/gayani.jrxml");


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());

            //                  JasperPrintManager.printReport(jasperPrint,true);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
