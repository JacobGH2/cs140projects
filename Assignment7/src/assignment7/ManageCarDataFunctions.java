package assignment7;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface ManageCarDataFunctions {
	// method the read the input file and populate the Arraylist and two PriorityQueues
	default void readData(String filename) {
		throw new UnsupportedOperationException("Please implement readData");
	}
	
	// return the ArrayList of cars
	default ArrayList<CarFunctions> getCarList() {
		return new ArrayList<>();
	}
	
	// return the PriorityQueue of cars that is in total range order
	default PriorityQueue<CarFunctions> getCarListByTotalRange() {
		return new PriorityQueue<>();
	}
	
	// return an ArrayList of cars for the PriorityQueue of cars that is in total range order
	// by using an iterator to populate the ArrayList
	default ArrayList<CarFunctions> getCarListByTotalRangeUsingIterator() {
		return new ArrayList<>();
	}
	
	// return the PriorityQueue of cars that is in remaining range order
	default PriorityQueue<CarFunctions> getCarListByRemainingRange() {
		return new PriorityQueue<>();
	}
	
	// return an ArrayList of cars for the PriorityQueue of cars that is in remaining range order
	// by using an iterator to populate the ArrayList
	default ArrayList<CarFunctions> getCarListByRemainingRangeUsingIterator() {
		return new ArrayList<>();
	}
	
	// return an ArrayList of String of cars from the PriorityQueue ordered by total order using the poll() method
	// in which the cars have a total range in [minTotalRange, maxTotalRange]
	// and include the index of the car from the ArrayList and the indices of cars that have the same fuel economy
	default ArrayList<String> getCarListByTotalRangeViaPoll(double minTotalRange, double maxTotalRange) {
		return new ArrayList<>();
	}
	
	// return an ArrayList of String of cars from the PriorityQueue ordered by remaining order using the poll() method
	// in which the cars have a remaining range in [minRemainingRange, minRemainingRange]
	// and include the index of the car from the ArrayList and the indices of cars that have the same fuel economy	
	default ArrayList<String> getCarListByRemainingRangeViaPoll(double minRemainingRange, double maxRemainingRange) {
		return new ArrayList<>();
	}
}
