package lk.ijse.CinnamonManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.CinnamonManagementSystem.util.Navigation;
import lk.ijse.CinnamonManagementSystem.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ManageDetailsFormController implements Initializable {
    public Pane pane;
    public AnchorPane pane2;
    public Label txtDate;
    public Label txtTime;

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,pane);

    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER,pane);
    }

    public void btnEmployeesOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEES,pane);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDER,pane);
    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCK,pane);
    }

    public void btnVehicelOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VEHICLE,pane);
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.REPORT,pane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane2);
    }
    private void setDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(format.format(new Date()));
    }
    private void timeNow() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        txtTime.setText(format.format(new Date()));

    }

    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        timeNow();
    }
}
