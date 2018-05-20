package startup;

import controller.Controller;
import view.TotalRevenueView;
import view.View;
import integration.ExtSystemHandler;
import integration.DbHandler;
import java.io.IOException;

public class Main {
    /**
     * Starts the application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) throws IOException {
        ExtSystemHandler extSystemHandler = new ExtSystemHandler();
        DbHandler dbHandler = new DbHandler();
        Controller controller = new Controller(extSystemHandler, dbHandler);
        controller.addTotalRevenueObserver(new TotalRevenueView());
        new View(controller).sampleExecution();
    }
}
