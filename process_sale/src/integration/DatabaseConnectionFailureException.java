package integration;

public class DatabaseConnectionFailureException extends RuntimeException {
    private ItemRegistry databaseThatFailedToBeAccessed;

    /**
     * Creates a new instance representing the condition described in the specified message.
     *
     * @param itemRegistry The database for the items.
     */
     DatabaseConnectionFailureException(ItemRegistry itemRegistry){
        super("Unable to access database " + itemRegistry.getDatabaseNr());
        this.databaseThatFailedToBeAccessed = itemRegistry;
    }

    public ItemRegistry getDatabaseThatFailedToBeAccesssed() {
        return databaseThatFailedToBeAccessed;
    }
}