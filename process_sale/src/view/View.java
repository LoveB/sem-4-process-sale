package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import controller.OperationFailedException;
import integration.ItemRegistryException;
import model.*;
import util.LogHandler;


/**
 * Represents the view
 */
public class View {
    private Controller controller;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
     private LogHandler logger;

    /**
     * Creates a new instance.
     *
     * @param controller The controller that is used for all operations.
     */
    public View(Controller controller) throws IOException  {

        this.controller = controller;
        this.logger = new LogHandler();

    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void sampleExecution() {
        PayMethod CashPay = new PayByCash();

        controller.startSale(CashPay);


        List<ItemId> items = new ArrayList<>();

        items.add(new ItemId(111));
        items.add(new ItemId(123));
        items.add(new ItemId(100002));

        System.out.println();

        System.out.println("REGISTERED ITEMS: ");
        String dottedLine = "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ";

        for (ItemId item : items) {


            try {
                System.out.println(dottedLine);

                SaleInfo saleInfoSweater = controller.addItem(item);
                printSaleInfo(saleInfoSweater);

                System.out.println();

            }

        catch (OperationFailedException exc){
                handleException(exc.getMessage(), exc);
        }

    }

        System.out.println("....................................................................");
        Amount totalCost = controller.stopAddingItems();
        System.out.println("Total Cost with tax included: " + totalCost);

        System.out.println();

        System.out.println("....................................................................");
        Amount paidAmount = new Amount(2000);
        System.out.println("Paid Amount: " + paidAmount);

        System.out.println();
        System.out.println();

        System.out.println("- - - - - - - DISPLAY - - - - - - - - ");
        System.out.println();

        Amount change =  controller.paySale(paidAmount);



        System.out.println();

        System.out.println("Change: " + change);

        System.out.println();
        System.out.println(dottedLine);
        System.out.println();

        System.out.println("LOG");
        System.out.println();

        logger.printLogList();


    }

    private void handleException(String uiMsg, Exception exc) {

            errorMsgHandler.showErrorMsg(uiMsg);
            logger.logException(exc);

        }

        private void printSaleInfo(SaleInfo saleInfo){
        System.out.println("Item Name: " + saleInfo.getItemName());
        System.out.println("Item Description: " + saleInfo.getItemDescription());
        System.out.println("Item Price: " + saleInfo.getItemPrice());
        System.out.println("Running Total: " + saleInfo.getRunningTotal());
    }




}
