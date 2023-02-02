package lk.ijse.CinnamonManagementSystem.model;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.CinnamonManagementSystem.to.Item;
import lk.ijse.CinnamonManagementSystem.util.CrudUtil;
import lk.ijse.CinnamonManagementSystem.view.tm.CustomerOrderDetailsTm;
import lk.ijse.CinnamonManagementSystem.view.tm.SupplierOrderDetailsTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Items VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, item.getItemCode(), item.getItemName(), item.getPrice() ,item.getDescriptions(),item.getQty());
    }

    public static Item search(String itemCode) throws SQLException, ClassNotFoundException {
        String sql = "select * from Items where item_code=?";
        ResultSet result = CrudUtil.execute(sql, itemCode);
        if (result.next()) {
            return new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)

            );
        }
        return null;
    }

    public static boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql = "Update Items set item_name=?,price=?,destription=? ,qtyOnHand=?where item_code=?";
        return  (CrudUtil.execute(sql,item.getItemName(),item.getPrice(),item.getDescriptions(),item.getQty(),item.getItemCode()));

    }

    public static boolean delete(Item item) throws SQLException, ClassNotFoundException {
        String sql = "delete from Items where item_code=? ";
        return CrudUtil.execute(sql, item.getItemCode());
    }

    public static ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        String sql = "select * from Items";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList<Item> item = new ArrayList<>();
        while (result.next()) {
            item.add(new Item(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            ));

        }
        return item;
    }

    public static ResultSet getAllIds() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT item_code FROM items");
    }

    public static ResultSet getLastId() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT cus_OrderId FROM customer_orders ORDER BY cus_OrderId DESC LIMIT 1");
    }

    public static ResultSet getDetails(String valueOf) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT item_name,price FROM items WHERE item_code=?", valueOf);
    }

    public static boolean updateItem(ObservableList<CustomerOrderDetailsTm> tm) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < tm.size(); i++) {
            if (CrudUtil.execute("UPDATE  items SET  qtyOnHand=qtyOnHand-? WHERE item_code=?",
                    tm.get(i).getQty(),
                    tm.get(i).getItemCode()

            )) {

            } else {
                System.out.println("error");
                return false;
            }
        }
        return true;
    }

    public static boolean updateItems(ObservableList<SupplierOrderDetailsTm> tm) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < tm.size(); i++) {
            if (CrudUtil.execute("UPDATE  items SET  qtyOnHand=qtyOnHand+? WHERE item_code=?",
                    tm.get(i).getQty(),
                    tm.get(i).getItemCode()

            )) {

            } else {
                System.out.println("error");
                return false;
            }
        }
        return true;
    }
}


