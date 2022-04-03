package assignment1;

public class HelhoskiP1 {
  	// constructor for the class, you don't need to put anything between the braces
	public HelhoskiP1() {
	}

	// method to sort the elements of an int array having index in
	// [lowerBound, upperBound] using selection sort in ascending order
	public int[] selectionSort(int[] values, int lowerBound, int upperBound) {
	    int min;
	    for (int i = lowerBound; i <= upperBound; i++) {
	        min = i;
	        for (int j = i + 1; j <= upperBound; j++) {
	            if (values[j] < values[min]) {
	                min = j;
	            }
	        }
	        if (min != i) {
	            int temp = values[min];
	            values[min] = values[i];
	            values[i] = temp;
	        }
	    }
	    return values;
	}

	// method to return the number of array elements >= testValue with indices in
	// [lowerBound, upperBound] using a for loop to examine the array elements
	public int forLoopTest(int lowerBound, int upperBound, int testValue, int[] values) {
	    int numValues = 0;
	    for (int i = lowerBound; i <= upperBound; i++) {
	        if (values[i] >= testValue) {
	            numValues++;
	        }
	    }
	    return numValues;
	}

	// method to return the number of array elements <= testValue with indices not
	// in [lowerBound, upperBound] using a while loop to examine the array elements
	public int whileLoopTest(int lowerBound, int upperBound, int testValue, int[] values) {
	    int numValues = 0;
	    int i = 0;
	    while (i < values.length) {
	        if ((i < lowerBound || i > upperBound) && (values[i] <= testValue)) {
	            numValues++;
	        }
	        i++;
	    }
	    return numValues;
	}

	// method to return the number of array elements in [testValue1, testValue2] or
	// [testValue2, testValue1] with indices in [lowerBound, upperBound] using a
	// do-while loop to examine the array elements
	public int doWhileLoopTest(int lowerBound, int upperBound, int testValue1, 
			int testValue2, int[] values) {
	    int numValues = 0;
	    int i = lowerBound;
	    int lowerTest;
	    int higherTest;
	    if (testValue1 > testValue2) {
	    	lowerTest = testValue2;
	    	higherTest = testValue1;
	    } else {
	    	lowerTest = testValue1;
	    	higherTest = testValue2;
	    }
	    do {
	    	if (values[i] >= lowerTest && values[i] <= higherTest) {
	    		numValues++;
	    	}
	    	i++;
	    } while ((i >= lowerBound) && (i <= upperBound));
	    return numValues;
	}

	// method to return the number of array elements that match the switch
	// cases 0, 1, 2, 3, 4, 5, 6, 7, 8, 10 - 15, and the default case with
	// indices in [lowerBound, upperBound]
	// return an array of size 11, with indices 0 - 8 corresponding to cases
	// 0 - 8, index 9 corresponding to case 10 - 15, and index 10
	// corresponding to the default case
	public int[] switchTest(int lowerBound, int upperBound, int[] values) {
	    int[] matches = new int[11];
	    int element;
	    for (int i = lowerBound; i <= upperBound; i++) {
	        element = values[i];
	        switch (element) {
	            case 0:
	                matches[0]++;
	                break;
	            case 1:
	                matches[1]++;
	                break;
	            case 2:
	                matches[2]++;
	                break;
	            case 3:
	                matches[3]++;
	                break;
	            case 4:
	                matches[4]++;
	                break;
	            case 5:
	                matches[5]++;
	                break;
	            case 6:
	                matches[6]++;
	                break;
	            case 7:
	                matches[7]++;
	                break;
	            case 8:
	                matches[8]++;
	                break;
	            case 10: case 11: case 12: case 13: case 14: case 15:
	                matches[9]++;
	            break;
	            default:
	                matches[10]++;
	            break;

	        }
	    }
	    return matches;
	}
}
