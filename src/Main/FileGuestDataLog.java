package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileGuestDataLog {

    private final static String GUEST_DATA_LOG_FILE = "guest_data_log_file.txt";
    private final static boolean APPEND_TO_FILE = true; //skriver overgamla vid false, fortsätter på dokumentet vid true

    public static void addRowToLog(String dataRowInStringFormat) throws IOException {

        try {
            FileWriter myFileWriter = new FileWriter(GUEST_DATA_LOG_FILE, APPEND_TO_FILE);
            try (PrintWriter myPrintWriter = new PrintWriter(myFileWriter)) {
                myPrintWriter.println(dataRowInStringFormat);
                myPrintWriter.close();
            } catch (Exception e) {
                System.err.println("Something failad when creating the PrintWriter: " + e);
            }
            myFileWriter.close();

        } catch (IOException e) {
            System.err.println("Something went wrong trying log guest data: " + e);
        }

        System.out.println("Guest data has been logged to file");
    }

}
