package assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class RunP1 {
	public static void main(String[] args) {
		// if we don't get one argument, print a message and exit
		if(args.length != 1) {
			System.out.println("format is: run_p1 \"input file\"");
			System.exit(0);
		}
		
		// allocate two empty int arrays to hold the test values
		// give then an initial size of 0, and then resize them
		// in the try/catch block
		int[] valuesToSort = new int[0];
		int[] valuesToTest = new int[0];

		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));

			// ArrayList to temporarily hold the test values
			java.util.ArrayList<String> values = new java.util.ArrayList<>();

			String inn;

			// read the file and add the values to the ArrayList
			while((inn = input.readLine()) != null)	{
				values.add(inn);
			}
			input.close();

			// initialize the
			valuesToSort = new int[values.size()];
			valuesToTest = new int[values.size()];

			// copy the test values into the two in arrays
			for(int i = 0; i < values.size(); i++) {
				int v = Integer.parseInt(values.get(i));
				valuesToSort[i] = v;
				valuesToTest[i] = v;
			}
		}	catch(Exception e) {
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// boolean to determine if we want to output the array values
		boolean outputTheValues = false;

		// instantiate the class that has the methods that we are going to test
		// put your class here
		HelhoskiP1 testClass = new HelhoskiP1();
		
		// output the test values
		if(outputTheValues)	{
			System.out.println("unsorted values and values to test");
			for(int i = 0; i < valuesToSort.length; i++)	{
				if(outputTheValues) {
					System.out.println(valuesToSort[i]);
				}
			}
		}

		// lower bound index of the array for the tests
		int lowerBound = 15;

		// upper bound index of the array for the tests
		int upperBound = 40;

		// the value to test against for forLoopTest and whileLoopTest
		int testValue = 22;

		// one of the test values for the doWhileLoopTest
		int testValue1 = 18;

		// the other test value for the doWhileLoopTest
		int testValue2 = 12;

		// sort the values with indices in [lowerBound, upperBound]
		int[] sortedValues = testClass.selectionSort(valuesToSort, lowerBound, upperBound);

		// check if the array is sorted for indices in [lowerBound, upperBound]
		boolean sortWorked = true;
		for(int i = lowerBound; i < upperBound; i++){
			if(sortedValues[i] > sortedValues[i+1])	{
				sortWorked = false;
			}
		}

		// output the sorted values
		if(outputTheValues)	{
			System.out.println("sorted values");
			for(int i = 0; i < sortedValues.length; i++) {
				System.out.println(sortedValues[i]);
			}
		}
		
		// run the for loop test
		int forLoopTestResult = testClass.forLoopTest(lowerBound, upperBound, testValue, valuesToTest);
		
		// run the while loop test
		int whileLoopTestResult = testClass.whileLoopTest(lowerBound, upperBound, testValue, valuesToTest);
		
		// run the do-while loop test
		int doWhileLoopTestResult = testClass.doWhileLoopTest(lowerBound, upperBound, testValue1, testValue2, valuesToTest);
		
		// run the switch test
		int[] switchTestResult = testClass.switchTest(lowerBound, upperBound, valuesToTest);
		
		// output the indices, test values, and results
		
		System.out.println("lowerBound = " + lowerBound);
		System.out.println("upperBound = " + upperBound);
		System.out.println("testValue = " + testValue);
		System.out.println("testValue1 = " + testValue1);
		System.out.println("testValue2 = " + testValue2);
		System.out.println("sortWorked = " + sortWorked);
		System.out.println("forLoopTestResult = " + forLoopTestResult);
		System.out.println("whileLoopTestResult = " + whileLoopTestResult);
		System.out.println("doWhileLoopTestResult = " + doWhileLoopTestResult);
		for(int i = 0; i < switchTestResult.length; i++)	{
			System.out.println("switchTestResult[" + i + "] = " + switchTestResult[i]);
		}
	}
}
