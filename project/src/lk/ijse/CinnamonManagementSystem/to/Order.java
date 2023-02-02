package lk.ijse.CinnamonManagementSystem.to;

public class Order {
    private String orderId;
    private String date;
    private String time;
    private String customerId;
    private String total;

    public Order() {
    }

    public Order(String orderId, String date, String time, String customerId, String total) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.customerId = customerId;
        this.total = total;
    }

    public Order(String orderId) {
        this.setOrderId(orderId);
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", customerId='" + customerId + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
