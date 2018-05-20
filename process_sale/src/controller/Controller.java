package controller;

import integration.*;
import model.*;


/**
 * This is the application's only controller class. All calls to the model pass
 * through here.
 */
public class Controller {
    private ExtSystemHandler extSystemHandler;
    private DbHandler dbHandler;
    private Sale sale = new Sale();
    private PayMethod payMethod ;


    /**
     * Creates a new instance.
     *
     * @param extSystemHandler Used to get all classes that handle external systems.
     * @param dbHandler Used to get all classes that handle the database
     */
    public Controller(ExtSystemHandler extSystemHandler, DbHandler dbHandler){
        this.extSystemHandler = extSystemHandler;
        this.dbHandler = dbHandler;
    }

    /**
     * Creates a new instance of Sale
     *
     * @param payMethod The chosen method of payment.
     */
    public void startSale(PayMethod payMethod){
       this.payMethod = payMethod;
    }

    /**
     * The specified observer will be notified whenever the queue number changes.
     *
     * @param observer The observer to notify about queue number changes.
     */
    public void addTotalRevenueObserver(TotalRevenueObserver observer) {
        sale.addTotalRevenueObserver(observer);
    }


    /**
     * Adds a new item to sale object and updates running total.
     *
     * @param itemId Used to search for item in database and get itemDTO
     * @return saleInfo including item description, item price and running total.
     */
    public SaleInfo addItem(ItemId itemId) throws OperationFailedException {
        try {
            ItemDTO foundItem = dbHandler.searchItem(itemId);
            SaleInfo saleInfo = sale.addItemToSale(foundItem);
            return saleInfo;

            } catch(DatabaseConnectionFailureException databaseFailureExc){
                throw new OperationFailedException("Unable to access database", databaseFailureExc);
            } catch(ItemRegistryException itemRegExc){
                throw new OperationFailedException("Item not found: " + itemId, itemRegExc);
        }
    }

    /**
     * Tells the system that no more items will be added
     *
     * @return Total cost including the calculated tax.
     */
    public Amount stopAddingItems(){
        Amount totalCost = sale.stopAddingItems();
        return totalCost;
    }

    /**
     * Tells the system what amount that been paid by customer.Program calls the correct payMethod
     * based on input in startSale.
     *
     * @param paidAmount Used to create a payment object and to calc change.
     * @return How much change to give back to customer.
     */

    public Amount paySale(Amount paidAmount){
        Amount change = payMethod.pay(paidAmount, sale);
        closeSale();
        return change;
    }

    private void closeSale(){
        dbHandler.closeSale(sale);
        extSystemHandler.closeSale(sale);
    }

    /**
     * Get the value of sale
     *
     * @return the value of sale
     */
    public Sale getSale() {
        return sale;
    }

}
