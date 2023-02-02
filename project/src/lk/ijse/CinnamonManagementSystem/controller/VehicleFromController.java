package lk.ijse.CinnamonManagementSystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CinnamonManagementSystem.model.CustomerModel;
import lk.ijse.CinnamonManagementSystem.model.EmployeesModel;
import lk.ijse.CinnamonManagementSystem.model.VehicleModel;
import lk.ijse.CinnamonManagementSystem.to.Employees;
import lk.ijse.CinnamonManagementSystem.to.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleFromController {
    public AnchorPane pane;
    public TextField txtBrand;
    public ComboBox cmbEmpid;
    public TextField txtVehicle;
    public TextField txtUsed;
    public TextField txtEngineCC;
    public TableView<Vehicle> tblVehicle;
    public TableColumn tblEmpId;
    public TableColumn tblEmpName;
    public TableColumn tblVehicleNo;
    public TableColumn tblBrand;
    public TableColumn tblFuelUsed;
    public TableColumn tblEngineCC;
    public Label txtEmpName;

    public void initialize() throws SQLException, ClassNotFoundException {
        tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Vehicle_No"));
        tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Brand"));
        tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Fuel_Used"));
        tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Engine_cc") );
        getAllData();
        setEIds();
    }

    private void setEIds() {
        ArrayList<String> id=new ArrayList<>();
        try {
            ResultSet set=EmployeesModel.getAllEmployeesid();
            while (set.next()){
                id.add(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        cmbEmpid.getItems().addAll(id);

    }

    public void EmpNewOnAction(ActionEvent actionEvent) {

    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String vNo = txtVehicle.getText();
        String brand =txtBrand.getText();
        String Fuel_Used = txtUsed.getText();
        String Engine_cc =txtEngineCC.getText();

        Vehicle vehicle = new Vehicle(
                vNo,
                brand,
                String.valueOf(cmbEmpid.getValue()),
                Fuel_Used,
                Engine_cc);
        boolean isAdded = VehicleModel.save(vehicle);
        if (isAdded){
            new Alert(Alert.AlertType.CONFIRMATION,"Vehicle Added").show();
            emptyTextField();
            getAllData();
        }else{
            new Alert(Alert.AlertType.WARNING,"Something Wrong Try again").show();
            emptyTextField();
        }
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String vNo = txtVehicle.getText();
        String brand =txtBrand.getText();
        String Fuel_Used = txtUsed.getText();
        String Engine_cc =txtEngineCC.getText();

        Vehicle vehicle = new Vehicle(vNo,brand,Fuel_Used,Engine_cc);
        boolean isUpdated = VehicleModel.update(vehicle);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Item Updated").show();
            emptyTextField();
            getAllData();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
            emptyTextField();
        }

    }
    private void fillData(Vehicle vehicle){
        txtVehicle.setText(vehicle.getVehicle_No());
        txtBrand.setText(vehicle.getBrand());
        txtEmpName.setText(vehicle.getE_id());
        txtUsed.setText(vehicle.getBrand());
        txtEngineCC.setText(vehicle.getEngine_cc());
    }
    public void getAllData() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> AllData = VehicleModel.getAllVehicle();
        ObservableList<Vehicle> observableList = FXCollections.observableArrayList();
        for (Vehicle vehicle :
                AllData){
            observableList.add(new Vehicle(
                    vehicle.getVehicle_No(),
                    vehicle.getBrand(),
                    vehicle.getE_id(),
                    vehicle.getFuel_Used(),
                    vehicle.getEngine_cc()
            ));
        }
        tblVehicle.setItems(observableList);
    }
    public void emptyTextField(){
        txtVehicle.clear();
        txtBrand.clear();
        txtUsed.clear();
        txtEngineCC.clear();
    }

    public void btnSearchOnActio(ActionEvent actionEvent)  {
        String id = txtVehicle.getText();

        try {
            Vehicle vehicle = VehicleModel.search(id);
            if(vehicle != null) {
                fillData(vehicle);

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnDeteleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String id = txtVehicle.getText();

           Vehicle vehicle=new Vehicle(id);

            boolean isDeleted = VehicleModel.delete(vehicle);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Vehicle").show();
                emptyTextField();
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
                emptyTextField();
            }
    }

    public void combOnAction(ActionEvent actionEvent) {
        try {
            ResultSet set = VehicleModel.getCutomerDetails(String.valueOf(cmbEmpid.getValue()));
            if (set.next()) {
                System.out.println(set.getString(1));
                txtEmpName.setText(set.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        emptyTextField();
    }
}
