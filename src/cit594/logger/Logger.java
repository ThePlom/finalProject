package cit594.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	
	//private variables for singleton instatiation
    private static Logger instance;
    private FileWriter fileWriter;

    //private constructor
    private Logger(String fileName) {
        try {
            File file = new File(fileName);
             if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true); // append to file
        } catch (IOException e) {
        	System.out.print("Log-file not created. Error found");
        }
    }
    

    /**
     * Gets a singleton instance of the Logger
     * @param fileName to be used as log file
     * @return instance of Logger to be used in program
     */
    public static Logger getInstance(String fileName) {
        if (instance == null) {
            instance = new Logger(fileName);
        }
        return instance;
    }

    /**
     * Logs a message to the log file
     * @param message to log
     */
    public void log(String message) {
    	//check that the filewriter isn't null first
        if (fileWriter != null) {
            try {
            	//write the message, each message on new line
                fileWriter.write(message + "\n");
                //must flush the logger each time
                fileWriter.flush();
            } catch (IOException e) {
            	System.out.print("Log-file not able to be written to. Error found");
            }
        }
    }

    /**
     * Closes the Logger instatiated
     */
    public void close() {
    	//generic close method
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
            	System.out.print("Log-file not able to be closed.Error found");
            }
        }
    }
}
