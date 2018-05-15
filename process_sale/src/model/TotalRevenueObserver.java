package model;

public interface TotalRevenueObserver {

        /**
         * Called when the queue number is updated.
         *
         * @param payment The new queue number.
         */
        void updateTotalRevenue(Amount payment);
    }
