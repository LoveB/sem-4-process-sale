package view;

import model.Amount;
import model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver {
    private Amount totalRevenue = new Amount();

    private void showTotalRevenue(Amount total){
        System.out.println("Total revenue: " + total);
        System.out.println();
        System.out.println("- - - - - - - - - - - - - - - - - - - ");
    }

    @Override
    public void updateTotalRevenue(Amount payment) {
        totalRevenue = totalRevenue.plus(payment);

        showTotalRevenue(totalRevenue);

    }

}
