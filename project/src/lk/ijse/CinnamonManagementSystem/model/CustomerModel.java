package lk.ijse.CinnamonManagementSystem.model;

import lk.ijse.CinnamonManagementSystem.to.Customer;
import lk.ijse.CinnamonManagementSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer VALUES(?,?,?,?,?,?)";
        return CrudUtil.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail(),customer.getNic());
    }
    public static Customer search(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from Customer where cus_id=?";
        ResultSet result = CrudUtil.execute(sql, id);
        if (result.next()){
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)
            );
        }
        return null;
    }
    public static  boolean update (Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "Update Customer set cus_name=?,address=?,mobile=?,email=?,nic=? where cus_id=?";
        return (CrudUtil.execute(sql, customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail(),customer.getNic(),customer.getId()));
    }
        public static boolean delete(Customer customer) throws SQLException, ClassNotFoundException {
            String sql = "delete from Customer where cus_id=? ";
            return CrudUtil.execute(sql,customer.getId());
        }
        public static ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        String sql = "select * from Customer";
            ResultSet result = CrudUtil.execute(sql);
            ArrayList <Customer> customers = new ArrayList<>();
            while (result.next()){
                customers.add(new Customer(
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                        result.getString(6)
                ));
            }
            return customers;
        }

    public static ResultSet getAllIds() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT nic FROM customer");
    }

    public static ResultSet getCutomerDetails(String valueOf) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT cus_name FROM customer WHERE nic=?",valueOf);
    }
}

