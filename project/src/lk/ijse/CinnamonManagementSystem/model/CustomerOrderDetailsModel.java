package lk.ijse.CinnamonManagementSystem.model;

import javafx.collections.ObservableList;
import lk.ijse.CinnamonManagementSystem.to.Order;
import lk.ijse.CinnamonManagementSystem.util.CrudUtil;
import lk.ijse.CinnamonManagementSystem.view.tm.CustomerOrderDetailsTm;

import java.sql.SQLException;

public class CustomerOrderDetailsModel {

    public static boolean setDetails(Order order, ObservableList<CustomerOrderDetailsTm> tm) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < tm.size(); i++) {
            if (CrudUtil.execute("INSERT INTO customer_orders_details VALUES (?,?,?,?)",
                    order.getOrderId(),
                    tm.get(i).getItemCode(),
                    tm.get(i).getQty(),
                    tm.get(i).getTotal()
            )) {

            } else {
                System.out.println("error");
                return false;
            }
        }

        return true;
    }
}
