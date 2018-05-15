package integration;

import java.util.ArrayList;
import java.util.List;
import model.Sale;

/**
 * Contains all calls to the data store with performed sales.
 */
class SaleRegistry {
    private static SaleRegistry instance = new SaleRegistry();
    private List<Sale> sales = new ArrayList<>();

    private SaleRegistry() {
    }

    /**
     * Saves the specified sale permanently.
     *
     * @param sale The sale that will be saved.
     */
    void addSaleToRegistry(Sale sale) {
        sales.add(sale);
    }

    public static SaleRegistry getInstance(){
        return instance;
    }

    List<Sale> getSales() {
        return sales;
    }
}

