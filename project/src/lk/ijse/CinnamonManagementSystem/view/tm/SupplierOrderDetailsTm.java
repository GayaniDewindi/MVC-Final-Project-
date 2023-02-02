package lk.ijse.CinnamonManagementSystem.view.tm;

public class SupplierOrderDetailsTm {
     private String itemCode;
     private String itemName;
     private String qty;
     private String price;
     private String total;

    public SupplierOrderDetailsTm() {
    }

    public SupplierOrderDetailsTm(String itemCode, String itemName, String qty, String price, String total) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setQty(qty);
        this.setPrice(price);
        this.setTotal(total);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
