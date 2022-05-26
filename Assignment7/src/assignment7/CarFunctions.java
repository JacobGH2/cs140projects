package assignment7;

public interface CarFunctions {
	// return the fuel economy in miles per gallon of the car
	default int getFuelEconomyInMilesPerGallon() {
		return 0;
	}
	
	// return the fuel capacity in gallons of the car
	default int getFuelCapacityInGallons() {
		return 0;
	}
	
	// return the current gallons of fuel of the car
	default double getCurrentFuelInGallons() {
		return 0.0;
	}
	
	// return the id of the car
	default String getId() {
		return "";
	}
	
	// set the current gallons of fuel of the car
	default void setCurrentFuelInGallons(double v) {
		throw new UnsupportedOperationException("Please implement setCurrentFuelInGallons");
	}
	
	// get the total range of the car in miles 
	default double getTotalRangeInMiles() {
		return getFuelCapacityInGallons()*getFuelEconomyInMilesPerGallon();
	}
	
	// get the remaining range of the car in miles
	default double getRemainingRangeInMiles() {
		return getCurrentFuelInGallons()*getFuelEconomyInMilesPerGallon();
	}
}
