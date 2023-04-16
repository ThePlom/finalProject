package cit594.datamanagement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dataPreProcessing {
	

	public static boolean verifyLegalArguments(String[] args) {
		
		//Check name of argument is used only once
		if ((allElementsUnique(args)
		
		//Check COVID file format
				 && (matchesRegex(args[0], "covid") 
				 && isInputFileReadable(args[0])
				 && validCovidFileExt(args[0]))
		
		 //Check properties file format
				 && (matchesRegex(args[1], "properties") 
				 && isInputFileReadable(args[1])) 
		 
		 //Check population file format
				 && (matchesRegex(args[2], "population") 
				 && isInputFileReadable(args[2]))
		 //Check log file format
				 && (matchesRegex(args[3], "log") 
				 && isLoggerInstantiable(args[3])))) {
			return true;
		}
			 	 
		else return false;
	 }
	
	public static boolean isLoggerInstantiable(String logFileName) {
	    File logFile = new File(logFileName);
	    try {
	        FileWriter fw = new FileWriter(logFile);
	        fw.close();
	        Logger.getLogger(Logger.class.getName()).log(Level.INFO, "Logger successfully instantiated.");
	        return true;
	    } catch (IOException e) {
	        Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, "Failed to instantiate logger.", e);
	        return false;
	    }
	}
	
	public static boolean isInputFileReadable(String fileName) {
	    File inputFile = new File(fileName);
	    try {
	        FileReader fr = new FileReader(inputFile);
	        fr.close();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	public static boolean validCovidFileExt(String covidFileName) {
	    return (covidFileName.toLowerCase().endsWith(".json") ||
	    		covidFileName.toLowerCase().endsWith(".csv"));
	
	}
	
	public static boolean matchesRegex(String argInput, String desiredArg) {
		    String regex = "^--(?<"+ argInput + ">.+?)=(?<value>.+)$";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(argInput);
		    return matcher.matches();
		}
	
	public static boolean allElementsUnique(String[] args) {
	    Set<String> set = new HashSet<>();
	    for (int i = 0; i < args.length; i++) {
	        if (set.contains(args[i])) {
	            return false;
	        }
	        set.add(args[i]);
	    }
	    return true;
	}
	
}

