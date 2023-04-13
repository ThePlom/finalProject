package cit594;

import cit594.datamanagement.programInputVerification;
import cit594.ui.UserInterface;

public class Main {
	
    public static void main(String[] args) {
		
    	//Verify that all inputs are acceptable
    	if (!programInputVerification.verifyLegalArguments(args)) {
    		UserInterface.startFaultError();
    		System.exit(0);
    	}
    	
    	
    }}
    		
    		
    		
