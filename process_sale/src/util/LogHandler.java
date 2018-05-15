package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.io.StringWriter;
/**
 * This class is responsible for the log.
 */
public class LogHandler {
    private List<String> logList = new ArrayList<>();

    public LogHandler() throws IOException {

    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exception The exception that shall be logged.
     */
    public void logException(Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append("\nException was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        logMsgBuilder.append("\nException cause: ");
        logMsgBuilder.append(exception.getCause());
        logMsgBuilder.append("\n");

        logMsgBuilder.append("\nStack trace: ");
        logMsgBuilder.append("\n");

        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        logMsgBuilder.append(errors.toString());

        logMsgBuilder.append("\n.................................................................................................");
        String logString = logMsgBuilder.toString();
        this.logList.add(logString);

    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }

    public void printLogList(){
        for(String logItem : logList){
            System.out.println(logItem);

            System.out.println();
        }
    }

    public List<String> getLogList() {
        return logList;
    }
}
