package lk.ijse.CinnamonManagementSystem.model;

import lk.ijse.CinnamonManagementSystem.to.Customer;
import lk.ijse.CinnamonManagementSystem.to.Vehicle;
import lk.ijse.CinnamonManagementSystem.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {
    public static boolean save(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO vehicle VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,
                vehicle.getVehicle_No(),
                vehicle.getBrand(),
                vehicle.getE_id(),
                vehicle.getFuel_Used(),
                vehicle.getEngine_cc());
    }
    public static Vehicle search(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from vehicle where vehicle_No=?";
        ResultSet result = CrudUtil.execute(sql, id);
        if (result.next()){
            return new Vehicle(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }
    public static  boolean update (Vehicle vehicle) throws SQLException, ClassNotFoundException {
        String sql = "Update vehicle set brand=?,e_id=?,fuel_Used=?,engine_cc=?, where vehicle_No=?";
        return (CrudUtil.execute(sql, vehicle.getBrand(), vehicle.getE_id(), vehicle.getFuel_Used(), vehicle.getEngine_cc(),vehicle.getVehicle_No()));
    }
    public static boolean delete(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        String sql = "delete from vehicle where vehicle_No=? ";
        return CrudUtil.execute(sql,vehicle.getVehicle_No());
    }
    public static ArrayList<Vehicle> getAllVehicle() throws SQLException, ClassNotFoundException {
        String sql = "select * from vehicle";
        ResultSet result = CrudUtil.execute(sql);
        ArrayList <Vehicle> vehicles = new ArrayList<>();
        while (result.next()){
            vehicles.add(new Vehicle(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)

            ));
        }
        return vehicles;
    }

    public static ResultSet getCutomerDetails(String valueOf) throws SQLException, ClassNotFoundException {
        System.out.println(valueOf);
        return CrudUtil.execute("SELECT  e_name FROM employees WHERE e_id=?",valueOf);
    }
}
