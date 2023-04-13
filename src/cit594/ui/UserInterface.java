package cit594.ui;

import java.awt.SystemTray;

public class UserInterface {
	
	String availableActions  =  
			 "1: Show all available actions" +
			 "2: Show total population for all zip codes \n" +
		     "3. Show total vaccinations per capita for each zip code on a given date \n" +
		     "4. Show average market value of properties in a specified zip code \n" +
		     "5. Show average total livable area for properties in a given zip code \n" +
		     "6. Show total market value of propertiesi, per capita, for a given zip code" +
		     "7. Custom Feature\n";
	

public static void startFaultError() {
	System.out.print("An error was discovered during file verification. Program ending.");
}
public static void welcomeUser() {
	System.out.print("=========Program Initiated=========");
}

public static void displayActions() {
	System.out.print("Select an action by entering a number from"
			+ "the options below" + "\n Enter 0 to end program");
}}
