package cit594;

import java.io.IOException;
import java.util.List;

import cit594.datamanagement.ZipCode;
import cit594.datamanagement.dataPreProcessing;
import cit594.datamanagement.propPopInfoReader;
import cit594.ui.UserInterface;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	
    	
    	  	String propertiesFileName = "properties.csv"; // Replace with the actual file name
    	  	String populationFileName = "population.csv"; // Replace with the actual file name
    	  	propPopInfoReader fileReader = new propPopInfoReader();
    	    
    	    List<ZipCode> zipCodes = fileReader.extractZipCodeInfo(propertiesFileName, populationFileName);
    	    
    	    
    	    
    	    
    	    // Do something with the list of ZipCode objects
    	
		
//    	//Verify that all inputs are acceptable
//    	if (!dataPreProcessing.verifyLegalArguments(args)) {
//    		UserInterface.startFaultError();
//    		System.exit(0);
//    	}
//    	
    	//send documents for data extraction
    	
    	//continually prompt user for choice
    	
    	//display results 
    	
    }}
    		
    		
    		
