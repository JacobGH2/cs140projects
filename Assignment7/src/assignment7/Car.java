package assignment7;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;



public class Car implements CarFunctions {
	private final String id; 
	private final int fuelEc;
	private final int fuelCap;
	private double currentFuel;
	
	public Car(String ID, int fe, int fc, double cf) {
		id = ID;
		fuelEc = fe;
		fuelCap = fc;
		currentFuel = cf;
	}
	
	public String getId() {
		return id;
	}
	
	public int getFuelEconomyInMilesPerGallon() {
		return fuelEc;
	}
	
	public int getFuelCapacityInGallons() {
		return fuelCap;
	}
	
	public double getCurrentFuelInGallons() {
		return currentFuel;
	}
	
	public void setCurrentFuelInGallons(double v) {
		currentFuel = v;
	}
	
	
	public String toString() {
		return getId() + "\t" + getFuelEconomyInMilesPerGallon() 
			+ "\t" + getFuelCapacityInGallons() + "\t" 
			+ getCurrentFuelInGallons() + "\t" + getTotalRangeInMiles() + "\t" 
			+ getRemainingRangeInMiles();
	}
}
