package integration;

/**
 * Thrown when something goes wrong while performing an operation in the <code>CarRegistry</code>.
 */

public class ItemRegistryException extends Exception {

    /**
     * Creates a new instance representing the condition described in the specified message.
     *
     * @param msg A message that describes what went wrong.
     */
     ItemRegistryException(String msg){
        super(msg);
    }
}
