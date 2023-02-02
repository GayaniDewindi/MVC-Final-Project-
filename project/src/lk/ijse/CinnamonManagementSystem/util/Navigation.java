package lk.ijse.CinnamonManagementSystem.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static Pane pane;

    public static void navigate(Routes route, Pane pane) throws IOException {
        Navigation.pane =  pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case MANAGE:
                window.setTitle("DashBoard Form");
                initUI("ManageDetailsForm.fxml");
                break;
            case RECEPTION:
                window.setTitle("DashBoard Form");
                initUI("ReceptionManageFrom.fxml");
                break;
            case CUSTOMER:
                window.setTitle("Customer Manage Form");
                initUI("CustomerFrom.fxml");
                break;
            case EMPLOYEES:
                window.setTitle("Place Order Form");
                initUI("EmployeesFrom.fxml");
                break;
            case SUPPLIER:
                window.setTitle("DashBoard Form");
                initUI("SupplierFrom.fxml");
                break;
            case CUSTOMER_ORDER_DETAILS:
                window.setTitle("DashBoard Form");
                initUI("CustomerOrderDetails.fxml");
                break;
            case ITEM:
                window.setTitle("DashBoard Form");
                initUI("ItemFrom.fxml");
                break;

            case SUPPLIER_ORDER_DETAILS:
                window.setTitle("DashBoard Form");
                initUI("SupplierOrderDetails.fxml");
                break;
            case CUSTOMER_ORDER:
                window.setTitle("Customer Order Form");
                initUI("CustomerOrderForm.fxml");
                break;
            case STOCK:
                window.setTitle("Stock Form");
                initUI("StockDetails.fxml");
                break;
            case VEHICLE:
                window.setTitle("Vehicle  Form");
                initUI("VehicleFrom.fxml");
                break;
            case ORDER:
                window.setTitle(" Order Form");
                initUI("OrderFrom.fxml");
                break;
            case BACK:
                window.setTitle(" Order From");
                initUI("OrderFrom.fxml");
                break;
            case MANAGE_DETAILS:
                window.setTitle(" Manage From");
                initUI("ManageDetailsForm.fxml");
                break;
            case LOGIN:
                window.setTitle(" Login From");
                initUI("LoginForm.fxml");
                break;
            case  REPORT:
                window.setTitle(" Report Form");
                initUI("Report.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }

    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/CinnamonManagementSystem/view/" + location)));
    }
}
