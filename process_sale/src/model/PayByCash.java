package model;


public class PayByCash implements PayMethod {

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
