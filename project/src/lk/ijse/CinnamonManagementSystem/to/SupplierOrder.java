package lk.ijse.CinnamonManagementSystem.to;

public class SupplierOrder {
     private String orderId;
     private String date;
     private String time;
     private String supplierId;
     private String total;

    public SupplierOrder() {
    }

    public SupplierOrder(String orderId, String date, String time, String supplierId, String total) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setTime(time);
        this.setSupplierId(supplierId);
        this.setTotal(total);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SupplierOrder{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
