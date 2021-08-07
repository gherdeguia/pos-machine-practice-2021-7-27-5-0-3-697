package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return generateReceiptLine(getReceiptItems(barcodes)) + generateReceiptTotal(getReceiptItems(barcodes));
    }

    private List<ReceiptItem> getReceiptItems(List<String> barcodes) {
        List<ItemInfo> itemInfoList = ItemDataLoader.loadAllItemInfos();
        List<ReceiptItem> itemList = new ArrayList<>();
        List<String> distinctBarcodes = barcodes
                .stream()
                .distinct()
                .collect(Collectors.toList());
        for (String barcode : distinctBarcodes) {
            for (ItemInfo itemInfo : itemInfoList) {
                if (barcode.equals(itemInfo.getBarcode())) {
                    int unitQty = Collections.frequency(barcodes, barcode);
                    int unitPrice = itemInfo.getPrice();
                    int unitSubTotal = calculateItemSubtotal(unitQty, unitPrice);
                    ReceiptItem item = new ReceiptItem(itemInfo.getName(), unitQty, unitPrice, unitSubTotal);
                    itemList.add(item);
                }
            }
        }
        return itemList;
    }

    private String generateReceiptLine(List<ReceiptItem> receiptItems){
        StringBuilder outputLine = new StringBuilder("***<store earning no money>Receipt***\n");
        for(ReceiptItem receiptLine : receiptItems){
            outputLine.append(String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", receiptLine.getItemName(), receiptLine.getUnitQty(), receiptLine.getUnitPrice(), receiptLine.getUnitSubtotal()));
        }
        return outputLine.toString();
    }

    private int getReceiptTotal(List<ReceiptItem> receiptItems){
        int receiptTotal = 0;
        for(ReceiptItem receiptLine : receiptItems){
            receiptTotal += receiptLine.getUnitSubtotal();
        }
        return receiptTotal;
    }

    private String generateReceiptTotal(List<ReceiptItem> receiptItems){
        return "----------------------\n" +
                String.format("Total: %d (yuan)\n",getReceiptTotal(receiptItems)) +
                "**********************";
    }

    private int calculateItemSubtotal(int quantity, int price) {
        return quantity*price;
    }


}
