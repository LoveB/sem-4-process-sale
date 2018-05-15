package model;

import controller.Controller;
import integration.DbHandler;
import integration.ExtSystemHandler;
import integration.ItemDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashRegisterTest {
    @Test
    public void addPayment() throws Exception {
        ExtSystemHandler extSystemHandler = new ExtSystemHandler();
        DbHandler dbHandler = new DbHandler();
        Controller controller = new Controller(extSystemHandler, dbHandler);

        Sale sale = new Sale();
        CashRegister cashRegister = new CashRegister();
        ItemId itemId = new ItemId(100004);
        int priceInt = 200;
        int percInt = 25;
        int paidAmountInt = 1500;

        Amount price = new Amount(priceInt);
        Percentage tax = new Percentage(percInt);
        String itemName = "Jacket";
        String itemDesc = "Jacket description";
        ItemDTO itemInstance = new ItemDTO(itemId, price, tax, itemName, itemDesc, "Red", "L");

        sale.addItemToSale(itemInstance);
        sale.stopAddingItems();

        Amount paidAmount = new Amount(paidAmountInt);
        CashPayment payment = new CashPayment(paidAmount, sale);

        controller.paySale(paidAmount);
        //sale.pay(payment);
        cashRegister.addPayment(payment);

        Amount expBalance = new Amount(200);
        Amount resultBalance = cashRegister.getBalance();
        assertEquals(expBalance, resultBalance);

    }

}