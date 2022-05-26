package assignment7;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Iterator;

public class ManageCarData implements ManageCarDataFunctions {
	private final ArrayList<CarFunctions> carList;
	private final PriorityQueue<CarFunctions> carListByTotalRange;
	private final PriorityQueue<CarFunctions> carListByRemainingRange;
	private final Comparator<CarFunctions> totalRangeComp = 
			Comparator.comparingDouble(CarFunctions::getTotalRangeInMiles)
			.thenComparing(CarFunctions::getId);
	private final Comparator<CarFunctions> remainRangeComp = 
			Comparator.comparingDouble(CarFunctions::getRemainingRangeInMiles)
			.thenComparing(CarFunctions::getId);

	public ManageCarData() {
		carList = new ArrayList<>();
		carListByTotalRange = new PriorityQueue<>(totalRangeComp);
		carListByRemainingRange = new PriorityQueue<>(remainRangeComp);
	}

	public void readData(String filename) {
		try (Scanner input = new Scanner(new File(filename))) {
			while(input.hasNextLine()) {
				String[] parts = input.nextLine().split("\t");
				Car newCar = new Car(parts[0], Integer.parseInt(parts[1]), 
						Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
				carList.add(newCar);
				carListByTotalRange.add(newCar);
				carListByRemainingRange.add(newCar);
			}
		} catch(Exception e) {

		}
	}

	public ArrayList<CarFunctions> getCarList() {
		ArrayList<CarFunctions> carListCopy = new ArrayList<>();
		Car carCopy;
		for (CarFunctions c : carList) {
			carCopy = new Car(c.getId(), c.getFuelEconomyInMilesPerGallon(), 
					c.getFuelCapacityInGallons(), c.getCurrentFuelInGallons());
			carListCopy.add(carCopy);
		}

		return carListCopy;
	}

	public PriorityQueue<CarFunctions> getCarListByTotalRange() {
		PriorityQueue<CarFunctions> queueCopy = new PriorityQueue<>(totalRangeComp);
		for (CarFunctions c : carList) {
			queueCopy.add(c);
		}
		return queueCopy;
	}

	public ArrayList<CarFunctions> getCarListByTotalRangeUsingIterator() {
		ArrayList<CarFunctions> newList = new ArrayList<>();
		Iterator<CarFunctions> i = carListByTotalRange.iterator();
		while(i.hasNext()) {
			newList.add(i.next());
		}
		return newList;
	}

	public PriorityQueue<CarFunctions> getCarListByRemainingRange() {
		PriorityQueue<CarFunctions> queueCopy = new PriorityQueue<>(remainRangeComp);
		for (CarFunctions c : carList) {
			queueCopy.add(c);
		}
		return queueCopy;
	}

	public ArrayList<CarFunctions> getCarListByRemainingRangeUsingIterator() {
		ArrayList<CarFunctions> newList = new ArrayList<>();
		Iterator<CarFunctions> i = carListByRemainingRange.iterator();
		while(i.hasNext()) {
			newList.add(i.next());
		}
		return newList;
	}

	public ArrayList<String> getCarListByTotalRangeViaPoll(double minTotalRange, 
			double maxTotalRange) {
		ArrayList<String> stringList = new ArrayList<>();
		while (!carListByTotalRange.isEmpty()) {
			CarFunctions currentCar = carListByTotalRange.poll();
			double currentRange = currentCar.getTotalRangeInMiles();
			if (currentRange >= minTotalRange && currentRange <= maxTotalRange) {
				String currentCarString = currentCar.toString();
				for (int i = 0; i < carList.size(); i++) {
					if (carList.get(i).equals(currentCar)) {
						currentCarString += "\t" + i;
					}
				}
				for (int j = 0; j < carList.size(); j++) {
					if (carList.get(j).getFuelEconomyInMilesPerGallon() == currentCar.getFuelEconomyInMilesPerGallon()) {
						currentCarString += "\t" + j;
					}
				}
				stringList.add(currentCarString);
			}
		}


		for (CarFunctions c : carList) {
			carListByTotalRange.add(c);
		}

		return stringList;
	}

	public ArrayList<String> getCarListByRemainingRangeViaPoll(double minRemainingRange, 
			double maxRemainingRange) {
		ArrayList<String> remainList = new ArrayList<>();
		while (!carListByRemainingRange.isEmpty()) {
			CarFunctions currentCar =  carListByRemainingRange.poll();
			double remainingRange = currentCar.getRemainingRangeInMiles();

			if (remainingRange >= minRemainingRange && remainingRange <= maxRemainingRange) {
				String cString = currentCar.toString();
				for (int i = 0; i < carList.size(); i++) {
					if (currentCar.equals(carList.get(i))) {
						cString += "\t" + i;
					}
				}
				for (int j = 0; j < carList.size(); j++) {
					if (carList.get(j).getFuelEconomyInMilesPerGallon() == currentCar.getFuelEconomyInMilesPerGallon()) {
						cString += "\t" + j;
					}

				}

				remainList.add(cString);

			}

		}
		for (CarFunctions c : carList) {
			carListByRemainingRange.add(c);
		}
		return remainList;

	}


}
