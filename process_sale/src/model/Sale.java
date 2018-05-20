package model;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import integration.ItemDTO;


/**
 * Represents one particular sale transaction, where one or more particular items are
 * sold to one particular customer.
 */
public class Sale {
    private List<SaleItem> saleItems = new ArrayList<>();
    private Amount runningTotal = new Amount(0);
    private LocalDateTime saleTime;
    private CashPayment payment;
    private Amount totalTax;
    private Boolean fullyPaid = false;
    private List<TotalRevenueObserver> revenueObservers = new ArrayList<>();

    /**
     * Creates a new instance.
     *
     */
    public Sale() {

        this.saleTime = LocalDateTime.now();
    }

    /**
     * The specified observer will be notified whenever the queue number changes.
     *
     * @param observer The observer to notify about queue number changes.
     */
    public void addTotalRevenueObserver(TotalRevenueObserver observer) {
        revenueObservers.add(observer);
    }

    /**
     * adds an item to the specific sale
     *
     * @param itemToAdd specifies what item that is added and is used to add correct details to the
     *                list of items and to update runningTotal.
     * @return a SaleInfo object that contains item name, item description, price and running total.
     */
    public SaleInfo addItemToSale(ItemDTO itemToAdd){
        updateRunningTotal(itemToAdd);
        createNewSaleItem(itemToAdd);
        SaleInfo saleInfo = createSaleInfo(itemToAdd);
        return saleInfo;
    }

    private void createNewSaleItem(ItemDTO foundItem){
        saleItems.add(new SaleItem( foundItem.getItemId(), foundItem.getItemPrice(), foundItem.getItemTax(), foundItem.getItemName()));
    }

    private SaleInfo createSaleInfo(ItemDTO foundItem){
        return new SaleInfo(foundItem.getItemName(), foundItem.getItemDescription(), foundItem.getItemPrice(), runningTotal);
    }

    private void updateRunningTotal(ItemDTO addedItem){
        runningTotal = runningTotal.plus(addedItem.getItemPrice());
    }

    /**
     * adds a cash payment to the specific sale
     *
     * @param payment specifies what cash payment that is added to the sale. Payment is also used to calculate change.
     * @return amount of change to give back.
     */
     Amount CashPay(CashPayment payment){
        this.payment = payment;
        Amount paidAmount = payment.getPaidAmount();
        notifyObservers(paidAmount);
        Amount change = payment.calculateChange();
        if(payment.paymentTooSmall()){
            delay(2000);
            System.out.println("Payment is too small");
            System.out.println();
            System.out.println("- - - - - - - - - - - - - - - - - - - ");
            return new Amount(0);
        }
        fullyPaid = true;
        return change;
    }

    /**
     * adds an additional payment to the specific sale if last payment was too small
     *
     * @param paidAdditional specifies what payment that is added to the sale.
     * @return amount of change to give back.
     */
     Amount CashPayAdditional(Amount paidAdditional){
       Amount totalPaidAmount = addAdditionalToTotalPaid(paidAdditional);
       notifyObservers(paidAdditional);
       payment.setPaidAmount(totalPaidAmount);
        if(!payment.paymentTooSmall()){
            fullyPaid = true;
        }else {
            delay(2000);
            System.out.println("Payment is too small");
            System.out.println();
            System.out.println("- - - - - - - - - - - - - - - - - - - ");
        }
        Amount change = payment.calculateChange();
        return change;
    }

    private void delay(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private Amount addAdditionalToTotalPaid(Amount paidAdditional){
        return payment.getPaidAmount().plus(paidAdditional);
    }

    private void notifyObservers(Amount paidAmount) {
        for (TotalRevenueObserver revenueObserver : revenueObservers) {
            revenueObserver.updateTotalRevenue(paidAmount);
        }
    }

    /**
     * Tells the system that no more items will be added.
     *
     * @return the amount of the running total.
     */
    public Amount stopAddingItems(){
        this.calculateTotalTax();
        return runningTotal;
    }

    private void calculateTotalTax(){
        Amount tax = new Amount();
        for (SaleItem item : saleItems){
            tax = taxBalancePlusThisTax(item, tax);
        }
        this.totalTax = tax;
    }

    private Amount taxBalancePlusThisTax(SaleItem item, Amount tax){
        return tax.plus(item.itemPrice.multPercentage(item.itemTax));
    }

    /**
     * Creates Receipt from the specific sale.
     *
     * @return the receipt.
     */
    public Receipt createReceipt(){
        return new Receipt(this);
    }

    private static class SaleItem {
        private ItemId itemId;
        private Amount itemPrice;
        private Percentage itemTax;
        private String itemName;

        SaleItem(ItemId itemId, Amount itemPrice, Percentage itemTax, String itemName) {
            this.itemId = itemId;
            this.itemPrice = itemPrice;
            this.itemTax = itemTax;
            this.itemName = itemName;
        }
    }

    /**
     * Creates a string representation of the SaleItem.
     *
     * @return the String.
     */
    String saleItemsToString(){
        StringBuilder builder = new StringBuilder();
        for (SaleItem item : saleItems){
            builder.append("Item: ");
            appendLine(builder, item.itemName);
            builder.append("Id: ");
            appendLine(builder, item.itemId.toString());
            builder.append("Price: ");
            appendLine(builder, item.itemPrice.toString());
            builder.append("Tax: ");
            appendLine(builder, item.itemTax.toString());
            endSection(builder);
        }
        return builder.toString();
    }

    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }

    private void endSection(StringBuilder builder) {
        builder.append("\n");
    }

    /**
     * Get the value of runningTotal
     *
     * @return the value of runningTotal
     */
    Amount getRunningTotal(){
    return runningTotal;
    }

    /**
     * Get the value of saleTime
     *
     * @return the value of saleTime
     */
    LocalDateTime getSaleTime(){
        return saleTime;
    }

    /**
     * Get the value of payment
     *
     * @return the value of payment
     */
    CashPayment getPayment() {
        return payment;
    }

    /**
     * Get the value of totalTax
     *
     * @return the value of totalTax
     */
    Amount getTotalTax() {
        return totalTax;
    }

    public Boolean getFullyPaid() {
        return fullyPaid;
    }


}


