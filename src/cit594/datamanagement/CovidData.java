package edu.upenn.cit594.datamanagement;

import java.util.Date;

/**
 * General Notes on the dataStructure:
 * 	- If an integer value is -10 then it is likely not initialized 
 *  - zipcode and reported date are the only fields that need to be correctly populated for a CovidData obj to exist
 *  - for all other fields, if the data is null (NOT 0) then it was malformed in the dataset and should be ignored for calculations
 * @author ploma
 *
 */
public class CovidData {
	
	public String zipcode;
	public Date reportedDate;
	
	//if a person gets their second dose, they are removed from partial and moved to full
	public Integer accumPartialVacCount = -10;		
	public Integer accumFullVacCount = -10;			//fully vaccinated
	public Integer boosterCount = -10;				//boosters administered 
	
	//optional for project
	public Integer accumTotalTestCount = -10;		
	public Integer accumPosTestCount = -10;		
	public Integer accumNegTestCount = -10;			
		
	//hospitalizations
	public Integer accumPatientCount = -10;			//patients that have been admitted in total
	public Integer accumDeathCount = -10;			// patients who have died in total 
	
	
	
	public CovidData(String zipcode, Date reportedDate) {
		this.zipcode = zipcode;
		this.reportedDate = reportedDate;
	}
	
}
