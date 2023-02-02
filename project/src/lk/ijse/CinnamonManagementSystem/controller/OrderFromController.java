package lk.ijse.CinnamonManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CinnamonManagementSystem.util.Navigation;
import lk.ijse.CinnamonManagementSystem.util.Routes;

import java.io.IOException;

public class OrderFromController {
    public AnchorPane pane;

    public void btnCustomerOrder(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER_ORDER_DETAILS,pane);
    }

    public void btnSuopplierOrder(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER_ORDER_DETAILS,pane);
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }
}
