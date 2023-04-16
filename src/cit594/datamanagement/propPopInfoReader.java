package cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class propPopInfoReader {
	
	
	/**
	 * 
	 * @param fileName of properties CSV
	 * @return an array of succesfully parsed ZipCodes with associated 
	 * market value and total livable area
	 * @throws IOException
	 */
	    public static List<ZipCode> extractZipCodeInfo(String fileName, String populationFile) throws IOException {
	        
	        // Save zipcode objects when identified
	        List<ZipCode> zipCodes = new ArrayList<>();
	        
	        // Read population file and store population data in a map
	        Map<String, Integer> populationMap = readPopulationFile(populationFile);
	        
	        // FIRST: find HEADERS
	        
	        // Create bufferedReader & identify headers
	        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	            String headerLine = br.readLine();
	            String[] headers = headerLine.split(",");
	            
	            // assume headers nonExistent; proceed to prove existence
	            int marketValueIndex = -1;
	            int totalLivableAreaIndex = -1;
	            int zipCodeIndex = -1;
	            int population = -1;
	            
	            // iterate over each line, try to identify field
	            for (int i = 0; i < headers.length; i++) {
	                String header = headers[i];
	                
	                if (header.equalsIgnoreCase("Market_value")) {
	                    marketValueIndex = i;
	                } else if (header.equalsIgnoreCase("Total_livable_area")) {
	                    totalLivableAreaIndex = i;
	                } else if (header.equalsIgnoreCase("zip_code")) {
	                    zipCodeIndex = i;
	                }
	            }
	            
	            // check
	            if (marketValueIndex == -1 || totalLivableAreaIndex == -1 || zipCodeIndex == -1) {
	                throw new IOException("CSV file is missing one or more required fields");
	            }
	            
	            // SECOND: Parse VALUES
	            
	            String line;
	            
	            // User bufferedReader, isolate each line, parse values and save to array
	            while ((line = br.readLine()) != null) {
	                String[] fields = parseCSVLine(line);
	                
	                // Check that 3 fields were successfully parsed
	                if (fields.length != headers.length) {
	                    throw new IOException("CSV record has wrong number of fields");
	                }
	                
	                // Extract first 5 chars, save as Zipcode
	                String zipCodeString = fields[zipCodeIndex].replaceAll("[^\\d]", "");
	                
	                if (zipCodeString.length() < 5) {
	                    continue; // skip this record and move to the next one
	                }
	                zipCodeString = zipCodeString.substring(0, 5);
	                
	                // Extract market value and
	                // livable area
	                Double marketValue = null;
	                if (fields[marketValueIndex] != null) {
	                    try {
	                        marketValue = Double.parseDouble(fields[marketValueIndex]);
	                    } catch (NumberFormatException e) {
	                        // Skip record if market value cannot be parsed as a double
	                        continue;
	                    }
	                }
	                
	                Double totalLivableArea = null;
	                if (fields[totalLivableAreaIndex] != null) {
	                    try {
	                        totalLivableArea = Double.parseDouble(fields[totalLivableAreaIndex]);
	                    } catch (NumberFormatException e) {
	                        // Skip record if total livable area cannot be parsed as a double
	                        continue;
	                    }
	                }
	                             
	                // Add population data to the zipcode object
	                if (populationMap.containsKey(zipCodeString)) {
	                }
	                
	                
	                ZipCode zipCode = new ZipCode(zipCodeString, marketValue, totalLivableArea, population);

	                
	                // Add this zipcode to the array of zipcodes
	                zipCodes.add(zipCode);
	            }
	        }
	        
	        return zipCodes;
	    }

	    private static Map<String, Integer> readPopulationFile(String fileName) throws IOException {
	        Map<String, Integer> populationMap = new HashMap<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	            String headerLine = br.readLine(); // discard header line
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] fields = line.split(",");
	                if (fields.length == 2) {
	                    String zipCodeString = fields[0].trim();
	                    int population = Integer.parseInt(fields[1].trim());
	                    populationMap.put(zipCodeString, population);
	                }
	            }
	        }
	        return populationMap;
	    }
    
    /**
     * 
     * @param line
     * @return
     */
    private static String[] parseCSVLine(String line) {
    	
    	//Array to hold fields when parsed
        List<String> fields = new ArrayList<>();
        
        //use StringBuilder for efficiency
        StringBuilder sb = new StringBuilder();
        
        //set flag for extraneous quotations
        boolean inQuote = false;
        
        //iterate over the given line
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '\"') {
                if (inQuote && i < line.length() - 1 && line.charAt(i + 1) == '\"') {
                    sb.append('\"');
                    i++;
                } else {
                    inQuote = !inQuote;
                }
            } else if (c == ',' && !inQuote) {
                fields.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        fields.add(sb.toString());
        return fields.toArray(new String[0]);
    }
}

