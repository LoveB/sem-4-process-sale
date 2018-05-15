package model;


public interface PayMethod {
     Amount pay(Amount paidAmount, Sale sale );
}
