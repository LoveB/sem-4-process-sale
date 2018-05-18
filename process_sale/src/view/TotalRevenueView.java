package view;

import model.Amount;
import model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver {
    private Amount totalRevenue = new Amount();


    @Override
    public void updateTotalRevenue(Amount payment) {
        totalRevenue = totalRevenue.plus(payment);

        showTotalRevenue(totalRevenue);

    }

    private void showTotalRevenue(Amount total){
        delay(2000);
        System.out.println("Total revenue: " + total);
        // System.out.println();
        //System.out.println("- - - - - - - - - - - - - - - - - - - ");
        System.out.println();

    }
    private void delay(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
