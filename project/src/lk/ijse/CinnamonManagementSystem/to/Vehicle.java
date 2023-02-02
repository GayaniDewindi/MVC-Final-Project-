package lk.ijse.CinnamonManagementSystem.to;

public class Vehicle {
    private String vehicle_No;
    private String brand;
    private String e_id;
    private String fuel_Used;
    private String engine_cc;

    public Vehicle() {
    }

    public Vehicle(String vehicle_No, String brand, String e_id, String fuel_Used, String engine_cc) {
        this.setVehicle_No(vehicle_No);
        this.setBrand(brand);
        this.setE_id(e_id);
        this.setFuel_Used(fuel_Used);
        this.setEngine_cc(engine_cc);
    }

    public Vehicle(String vehicle_No) {
        this.setVehicle_No(vehicle_No);
    }

    public Vehicle(String vehicle_No, String brand, String fuel_Used, String engine_cc) {
        this.vehicle_No = vehicle_No;
        this.brand = brand;
        this.fuel_Used = fuel_Used;
        this.engine_cc = engine_cc;
    }

    public String getVehicle_No() {
        return vehicle_No;
    }

    public void setVehicle_No(String vehicle_No) {
        this.vehicle_No = vehicle_No;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getFuel_Used() {
        return fuel_Used;
    }

    public void setFuel_Used(String fuel_Used) {
        this.fuel_Used = fuel_Used;
    }

    public String getEngine_cc() {
        return engine_cc;
    }

    public void setEngine_cc(String engine_cc) {
        this.engine_cc = engine_cc;
    }
}
