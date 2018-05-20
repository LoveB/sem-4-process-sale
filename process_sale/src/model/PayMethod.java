package model;

public interface PayMethod {

     /**
      * Called when a payment for the sale is made.
      *
      * @param paidAmount The paid amount.
      * @param sale The current sale.
      */
     Amount pay(Amount paidAmount, Sale sale );
}
