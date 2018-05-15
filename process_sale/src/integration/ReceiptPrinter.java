package integration;

import model.Receipt;
import model.Sale;

/**
 * Represents the external printer.
 */
class ReceiptPrinter implements Printer{
    private boolean receiptPrinted = false;

    ReceiptPrinter(){}

    /**
     * Prints the specified receipt. This dummy implementation prints to
     * <code>System.out</code> instead of a printer.
     *
     * @param sale is used to create the receipt string.
     */
    @Override
    public void print(Sale  sale) {
        Receipt receipt = sale.createReceipt();
        System.out.println(receipt.createReceiptString());
        receiptPrinted = true;
    }
}
