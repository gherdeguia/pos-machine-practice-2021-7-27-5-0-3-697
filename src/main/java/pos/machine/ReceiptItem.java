package pos.machine;

public class ReceiptItem {

    private final String itemName;
    private final int unitPrice;
    private final int unitSubtotal;
    private final int unitQty;

    public ReceiptItem(String name, int quantity, int price, int subTotal) {
        this.itemName = name;
        this.unitQty = quantity;
        this.unitPrice = price;
        this.unitSubtotal = subTotal;
    }

    public String getItemName(){
        return this.itemName;
    }

    public int getUnitQty(){
        return this.unitQty;
    }

    public int getUnitPrice(){
        return this.unitPrice;
    }

    public int getUnitSubtotal(){
        return this.unitSubtotal;
    }
}
