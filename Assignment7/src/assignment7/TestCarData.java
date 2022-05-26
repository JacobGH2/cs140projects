package assignment7;

import java.util.ArrayList;
import java.util.Iterator;

public class TestCarData {
	public static void main(String[] args) {
		if(args.length != 5) {
			System.out.println("format testCarData \"input file\" \"min total range\" \"min total range\" \"max remaining range\" \"max remaining range\"");
			System.exit(0);
		}
		
		// get the command line arguments
		String filename = args[0];
		double minTotalRange = Double.parseDouble(args[1]);
		double maxTotalRange = Double.parseDouble(args[2]);
		double minRemainingRange = Double.parseDouble(args[3]);
		double maxRemainingRange = Double.parseDouble(args[4]);
		
		// create a ManageCarData object
		ManageCarDataFunctions manageCarData = new ManageCarData();
		
		// read the car definitions from the input file
		manageCarData.readData(filename);
		
		// get the list of cars that is stored as an arraylist and print it out
		System.out.println("carList");
		ArrayList<CarFunctions> carList = manageCarData.getCarList();
		for(CarFunctions c : carList) {
			System.out.println(c);
		}
		System.out.println();

		// get the list of cars stored in the PriorityQueue ordered by total range via an iterator and print it out
		System.out.println("carListByTotalRange iterator");
		ArrayList<CarFunctions> carListByTotalRangeByIterator = manageCarData.getCarListByTotalRangeUsingIterator();
		for(CarFunctions c : carListByTotalRangeByIterator) {
			System.out.println(c);
		}
		System.out.println();
		
		// get an iterator for the PriorityQueue ordered by total range and print them out
		System.out.println("carListByTotalRange iterator local");
		Iterator<CarFunctions> itByTotalRange = manageCarData.getCarListByTotalRange().iterator();
		while(itByTotalRange.hasNext()) {
			System.out.println(itByTotalRange.next());
		}
		System.out.println();
		
		// get the list of cars stored in the PriorityQueue ordered by remaining range via an iterator and print it out
		System.out.println("carListByRemainingRange iterator");
		ArrayList<CarFunctions> carListByRemainingRangeByIterator = manageCarData.getCarListByRemainingRangeUsingIterator();
		for(CarFunctions c : carListByRemainingRangeByIterator) {
			System.out.println(c);
		}
		System.out.println();
		
		// get an iterator for the PriorityQueue ordered by remaining range and print them out
		System.out.println("carListByRemainingRange iterator local");
		Iterator<CarFunctions> itByRemaininglRange = manageCarData.getCarListByRemainingRange().iterator();
		while(itByRemaininglRange.hasNext()) {
			System.out.println(itByRemaininglRange.next());
		}
		System.out.println();
		
		// get the list of cars stored in the PriroityQueue ordered by total range having total range [minTotalRange, maxTotalRange]
		System.out.println("carListByTotalRange.poll().getTotalRangeInMiles() in [" + minTotalRange + "," + maxTotalRange + "]");
		ArrayList<String> carListByTotalRangeByPoll = manageCarData.getCarListByTotalRangeViaPoll(minTotalRange, maxTotalRange);
		for(String s : carListByTotalRangeByPoll) {
			System.out.println(s);
		}
		System.out.println();
		
		// get the list of cars stored in the PriroityQueue ordered by remaining range having total range [minRemainingRange, maxRemainingRange]
		System.out.println("carListByTotalRange.poll().getRemainingRangeInMiles() in [" + minRemainingRange + "," + maxRemainingRange + "]");
		ArrayList<String> carListByRemainingRangeByPoll = manageCarData.getCarListByRemainingRangeViaPoll(minRemainingRange, maxRemainingRange);
		for(String s : carListByRemainingRangeByPoll) {
			System.out.println(s);
		}
		System.out.println();
		
		// get the list of cars stored in the PriorityQueue ordered by total range via an iterator and print it out
		System.out.println("carListByTotalRange iterator (if empty, you didn't refill carListByTotalRange after polling all of the elements)");
		carListByTotalRangeByIterator = manageCarData.getCarListByTotalRangeUsingIterator();
		for(CarFunctions c : carListByTotalRangeByIterator) {
			System.out.println(c);
		}
		System.out.println();
		
		// get the list of cars stored in the PriorityQueue ordered by remaining range via an iterator and print it out
		System.out.println("carListByRemainingRange iterator (if empty, you didn't refill carListByRemainingRange after polling all of the elements)");
		carListByRemainingRangeByIterator = manageCarData.getCarListByRemainingRangeUsingIterator();
		for(CarFunctions c : carListByRemainingRangeByIterator) {
			System.out.println(c);
		}
		System.out.println();
	}
}
