package cit594.datamanagement;

public class ZipCode {
    private String zipCode;
    private double marketValue;
    private double totalLivableArea;
	private double population;

    public ZipCode(String zipCode, double marketValue, double totalLivableArea, double population) {
        this.zipCode = zipCode;
        this.marketValue = marketValue;
        this.totalLivableArea = totalLivableArea;
        this.population = population;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public double getTotalLivableArea() {
        return totalLivableArea;
    }
    
    public double getPopulation() {
    	return population;
    }
}
