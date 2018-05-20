package integration;

import model.Sale;

public interface Printer {

    /**
     * Called when the something shall be printed.
     *
     * @param sale The current sale.
     */
    void print(Sale sale);
}
