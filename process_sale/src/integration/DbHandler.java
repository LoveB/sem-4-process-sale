package integration;

import model.ItemId;
import model.Sale;


/**
 * This class is handling all database calls
 */

public class DbHandler {
    private ItemRegistry itemRegistry;
    private SaleRegistry saleRegistry;

    /**
     * Creates a new instance.
     *
     */
    public DbHandler(){
        this.itemRegistry = new ItemRegistry("12345");
        this.saleRegistry = SaleRegistry.getInstance();
    }

    /**
     * Search <code>itemRegistry</code> for item that is being entered
     *
     * @param itemId Used to find item in database
     * @return itemDTO for searched item
     */
    public ItemDTO searchItem(ItemId itemId) throws DatabaseConnectionFailureException, ItemRegistryException {

        if (itemId.getItemId() == 123) {
            throw new DatabaseConnectionFailureException(itemRegistry);
        } else {
            ItemDTO foundItem = itemRegistry.searchItemIdToDTO(itemId);
            if (foundItem != null) {
                itemRegistry.setItemAsSold(foundItem);
                return foundItem;
            }
            return null;
        }
    }

    /**
     * Tells saleRegistry to log sale as closed.
     *
     * @param sale Used to transfer to saleRegistry.
     */
    public void closeSale(Sale sale){
        if (sale.getFullyPaid()) {
            saleRegistry.addSaleToRegistry(sale);
        }
    }

}
