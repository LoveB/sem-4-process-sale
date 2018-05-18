package model;

public interface TotalRevenueObserver {

        /**
         * Called when the paidAmount is updated.
         *
         * @param payment The new amount.
         */
        void updateTotalRevenue(Amount payment);
    }
