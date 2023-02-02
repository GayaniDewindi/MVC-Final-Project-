package lk.ijse.CinnamonManagementSystem.to;

public class Item {
  private String itemCode;
  private String itemName;
  private String descriptions;
  private String qty;
  private String price;



    public Item() {
    }



    public Item(String itemCode) {
        this.setItemCode(itemCode);
    }

    public Item(String itemCode, String itemName, String descriptions,String price, String qty) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.descriptions = descriptions;
        this.price = price;
        this.qty = qty;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
