package view;

import model.Amount;
import model.TotalRevenueObserver;

/**
 * Represents the display of the total revenue.
 */
public class TotalRevenueView implements TotalRevenueObserver {
    private Amount totalRevenue = new Amount();

    /**
     * updates the total revenue
     * @param payment the payment that shall be added to the total revenue.
     */
    @Override
    public void updateTotalRevenue(Amount payment) {
        totalRevenue = totalRevenue.plus(payment);
        showTotalRevenue(totalRevenue);
    }

    private void showTotalRevenue(Amount total){
        delay(2000);
        System.out.println("Total revenue: " + total);
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
