package integration;

import java.util.ArrayList;
import java.util.List;
import model.Amount;
import model.Percentage;
import model.ItemId;

/**
 * Contains all calls to the data store with items that may be bought.
 */
 class ItemRegistry{
    private List<ItemData> items = new ArrayList<>();
    private static String databaseNr;

    /**
     * Creates a new instance.
     *
     */
     ItemRegistry(String dBNr) {
        this.databaseNr = dBNr;
        addItems();
    }

    /**
     * Search for a item matching the specified search criteria.
     *
     * @param itemId Used to find the car in the database with the same id
     * @return ItemDTO for the item with the same itemId as was entered as a search parameter
     */
    ItemDTO searchItemIdToDTO(ItemId itemId)throws ItemRegistryException{
        ItemData item = searchItemIdToData(itemId);
        ItemDTO itemDTO = convertIdToDTO(item);
        return itemDTO;
    }

    /**
     * Set item as sold in database
     *
     * @param soldItem Used to find item in database
     */
    void setItemAsSold(ItemDTO soldItem)throws ItemRegistryException{
        ItemId soldItemId = soldItem.getItemId();
        ItemData item = searchItemIdToData(soldItemId);
        item.sold = true;
    }

    private ItemData searchItemIdToData(ItemId itemId) throws ItemRegistryException {
        for (ItemData item : items) {
            if (matches(item.itemId, itemId) && !item.sold) {
                return item;
            }
        }
        throw new ItemRegistryException("Item " + itemId + " not found in database");
    }

    private ItemDTO convertIdToDTO(ItemData item){
        ItemDTO convertedDTO = new ItemDTO(item.itemId, item.itemPrice, item.itemTax, item.itemName, item.itemDescription, item.colour, item.size );
        return convertedDTO;
    }

    private boolean matches(ItemId found, ItemId searched){
        if (found.getItemId() != searched.getItemId()){
            return false;
        }
        return true;
    }

    private void addItems() {
        items.add(new ItemData(new ItemId(100001), new Amount(400), new Percentage(25), "Sweater", "Description for sweater", "red", "M"));
        items.add(new ItemData(new ItemId(100002), new Amount(700), new Percentage(25), "Jeans", "Description for jeans", "blue", "L"));
        items.add(new ItemData(new ItemId(100003), new Amount(150), new Percentage(25), "T-Shirt", "Description for T-Shirt", "white", "M"));
        items.add(new ItemData(new ItemId(100004), new Amount(200), new Percentage(25), "Skirt", "Description for skirt", "green", "S"));
        items.add(new ItemData(new ItemId(100005), new Amount(350), new Percentage(25), "Shorts", "Description for shorts", "black", "S"));
    }

    private static class ItemData {
        private ItemId itemId;
        private Amount itemPrice;
        private Percentage itemTax;
        private String itemName;
        private String itemDescription;
        private String colour;
        private String size;
        private boolean sold;

        ItemData(ItemId itemId, Amount itemPrice, Percentage itemTax, String itemName, String itemDescription, String colour, String size) {
            this.itemId = itemId;
            this.itemPrice = itemPrice;
            this.itemTax = itemTax;
            this.itemName = itemName;
            this.itemDescription = itemDescription;
            this.colour = colour;
            this.size = size;
            this.sold = false;
        }
    }

     static String getDatabaseNr() {
        return databaseNr;
    }
}











