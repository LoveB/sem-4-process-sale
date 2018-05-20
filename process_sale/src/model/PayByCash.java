package model;

public class PayByCash implements PayMethod {

    /**
     * adds an amount to the total cash payment ov the sale.
     *
     * @param paidAmount The amount paid in cash for the current sale
     * @param sale The current sale.
     * @return the amount of the change to give back.
     */
    @Override
    public Amount pay(Amount paidAmount, Sale sale){
        CashPayment payment = new CashPayment(paidAmount, sale);
        CashRegister cashRegister = new CashRegister();
        Amount change = sale.CashPay(payment);
        while(!sale.getFullyPaid()){
            change = sale.CashPayAdditional(new Amount(50));
        }
        cashRegister.addPayment(payment);
        return change;
    }
}
