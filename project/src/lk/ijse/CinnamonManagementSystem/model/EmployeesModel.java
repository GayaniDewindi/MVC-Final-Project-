package lk.ijse.CinnamonManagementSystem.model;

import javafx.scene.control.Alert;
import lk.ijse.CinnamonManagementSystem.to.Employees;
import lk.ijse.CinnamonManagementSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesModel {
    public static boolean save(Employees employees) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Employees VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,employees.getId(),employees.getName(),employees.getAddress(),employees.getContact(),employees.getNic());
    }
    public static Employees search(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from Employees where  e_id =?";
        ResultSet result = CrudUtil.execute(sql, id);
        if (result.next()){
            return new Employees(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static  boolean update (Employees employees) throws SQLException, ClassNotFoundException {
        String sql = "Update Employees set e_name=?,address=?,mobile=?,nic=? where e_id=?";
        return  (CrudUtil.execute(sql,employees.getName(),employees.getAddress(),employees.getContact(),employees.getNic(),employees.getId())) ;
    }
    public static boolean delete(Employees employees) throws SQLException, ClassNotFoundException {
        String sql = "delete from Employees where e_id=? ";
        return CrudUtil.execute(sql,employees.getId());
    }
    public static ArrayList<Employees> getAllEmployees() throws SQLException, ClassNotFoundException {
        String sql = "select * from Employees";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList <Employees> employees = new ArrayList<>();
        while (result.next()){
            employees.add(new Employees(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            ));
        }
        return employees;
    }

    public static ResultSet getAllEmployeesid() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT e_id from employees");
    }
}
