package lk.ijse.CinnamonManagementSystem.model;
import lk.ijse.CinnamonManagementSystem.to.Supplier;
import lk.ijse.CinnamonManagementSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Suppliers VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getContact(),supplier.getEmail());
    }
    public static Supplier search(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from Suppliers where  sup_id =?";
        ResultSet result = CrudUtil.execute(sql, id);
        if (result.next()){
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static  boolean update (Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "Update Suppliers set sup_name=?,address=?,contact=?,email=? where sup_id=?";
        return (CrudUtil.execute(sql,supplier.getName(),supplier.getAddress(),supplier.getContact(),supplier.getEmail(),supplier.getId()));

    }
    public static boolean delete(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "delete from Suppliers where e_id=? ";
        return CrudUtil.execute(sql,supplier.getId());
    }
    public static ArrayList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException {
        String sql = "select * from Suppliers";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList <Supplier> suppliers = new ArrayList<>();
        while (result.next()){
            suppliers.add(new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            ));
        }
        return suppliers;
    }

    public static ResultSet getAllIds() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT cus_id FROM Supplier");
    }
}
