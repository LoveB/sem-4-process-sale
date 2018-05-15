package integration;

 class PrinterFactory {
     private static final PrinterFactory FACTORY_INSTANCE = new PrinterFactory();


     private PrinterFactory() {
     }

     static PrinterFactory getFactory() {
         return FACTORY_INSTANCE;
     }

     /**
      * @return An instance of the <code>Printer</code> that shall be used.
      */
     static Printer getPrinter(String printerType) {
         if (printerType.equalsIgnoreCase("RECEIPT")) {
             return new ReceiptPrinter();
         }
         return null;
     }
 }
