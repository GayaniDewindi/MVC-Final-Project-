package lk.ijse.CinnamonManagementSystem.model;

import javafx.collections.ObservableList;
import lk.ijse.CinnamonManagementSystem.db.DBConnection;
import lk.ijse.CinnamonManagementSystem.to.SupplierOrder;
import lk.ijse.CinnamonManagementSystem.util.CrudUtil;
import lk.ijse.CinnamonManagementSystem.view.tm.SupplierOrderDetailsTm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierOrderModel {
    public static boolean save(SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orders VALUES(?, ?, ?)";
        return CrudUtil.execute(sql,supplierOrder.getOrderId(), supplierOrder.getDate(), supplierOrder.getSupplierId());
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM Orders ORDER BY id DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "D0" + id;
        }
        return "O001";

    }

    public static boolean setOrder(SupplierOrder supplierOrder, ObservableList<SupplierOrderDetailsTm> tm) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            connection.setAutoCommit(false);
            if (SupplierOrderModel.setOrder(supplierOrder)) {
                if (SupplierOrderDetailsModel.setDetails(supplierOrder, tm)) {
                    if (ItemModel.updateItems(tm)) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                    }

                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    private static boolean setOrder(SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO supliers_orders VALUES (?,?,?,?,?)",
                supplierOrder.getOrderId(),
                supplierOrder.getDate(),
                supplierOrder.getTime(),
                supplierOrder.getSupplierId(),
                supplierOrder.getTotal()

        );
    }
}
