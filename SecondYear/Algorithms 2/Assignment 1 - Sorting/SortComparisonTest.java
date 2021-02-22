/**
 * Average running time (in milliseconds) of 3 calls to the sorting algorithms
 * 
 * 						| Insertion	| Selection	| Quick		| Merge		|
 * 1000 Random			| 17.93ms	| 6.46ms	| 0.66ms	| 0.96ms	|
 * 1000 Few Unique		| 16.29ms	| 5.72ms	| 0.68ms	| 0.98ms	|
 * 1000 Nearly Ordered	| 21.74ms	| 5.26ms	| 1.13ms	| 1.23ms	|
 * 1000 Reverse Order	| 21.05ms	| 5.08ms	| 4.85ms	| 0.92ms	|
 * 1000 Sorted			| 17.21ms	| 5.14ms	| 5.60ms	| 0.86ms	|
 * 10000 Random			| 73.22ms 	| 44.24ms	| 2.21ms	| 2.99ms	|
 * 
 */

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Alice Doherty
 *  @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	double[] emptyArray = new double[0];
    	assertEquals("Checking insertionSort for an empty array", emptyArray, SortComparison.insertionSort(emptyArray));
    	assertEquals("Checking selectionSort for an empty array", emptyArray, SortComparison.selectionSort(emptyArray));
    	assertEquals("Checking quickSort for an empty array", emptyArray, SortComparison.quickSort(emptyArray));
    	assertEquals("Checking mergeSort for an empty array", emptyArray, SortComparison.mergeSort(emptyArray));
    }
    
    /**
     * Check that insertionSort() works
     */    
    @Test
    public void testInsertionSort()
    {   	
    	// Test with single element array
    
    	double[] singleArray = {10.4};
    	String singleString = "[10.4]";
    	assertEquals("Checking insertionSort with single element array", singleString, Arrays.toString(SortComparison.insertionSort(singleArray)));
    	
    	
    	String sortedArray = "[1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14]";
    	
    	// Test with array of random doubles
    	
    	double[] randomArray = {2377.88,
    			2910.66, 
    			8458.14, 
    			1522.08, 
    			5855.37,
    			1934.75,
    			8106.23,
    			1735.31,
    			4849.83,
    			1518.63
    			};
    	
    	assertEquals("Checking insertionSort with random array", sortedArray, Arrays.toString(SortComparison.insertionSort(randomArray)));
    	
    	// Test with ordered array
    	
    	double[] orderedArray = {1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14};
    	
    	assertEquals("Checking insertionSort with ordered array", sortedArray, Arrays.toString(SortComparison.insertionSort(orderedArray)));
    	
    }

    /**
     * Check that selectionSort() works
     */
    @Test
    public void testSelectionSort()
    {    	
    	// Test with single element array
    
    	double[] singleArray = {10.4};
    	String singleString = "[10.4]";
    	assertEquals("Checking selectionSort with single element array", singleString, Arrays.toString(SortComparison.selectionSort(singleArray)));
    	
    	
    	String sortedArray = "[1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14]";
    	
    	// Test with array of random doubles
    	
    	double[] randomArray = {2377.88,
    			2910.66, 
    			8458.14, 
    			1522.08, 
    			5855.37,
    			1934.75,
    			8106.23,
    			1735.31,
    			4849.83,
    			1518.63
    			};
    	
    	assertEquals("Checking selectionSort with random array", sortedArray, Arrays.toString(SortComparison.selectionSort(randomArray)));
    	
    	// Test with ordered array
    	
    	double[] orderedArray = {1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14};
    	
    	assertEquals("Checking selectionSort with ordered array", sortedArray, Arrays.toString(SortComparison.selectionSort(orderedArray)));
    }
    
    /**
     * Check that quickSort works
     */
    @Test
    public void testQuickSort()
    {
    	// Test with single element array
        
    	double[] singleArray = {10.4};
    	String singleString = "[10.4]";
    	assertEquals("Checking quickSort with single element array", singleString, Arrays.toString(SortComparison.quickSort(singleArray)));
    	
    	
    	String sortedArray = "[1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14]";
    	
    	// Test with array of random doubles
    	
    	double[] randomArray = {2377.88,
    			2910.66, 
    			8458.14, 
    			1522.08, 
    			5855.37,
    			1934.75,
    			8106.23,
    			1735.31,
    			4849.83,
    			1518.63
    			};
    	
    	assertEquals("Checking quickSort with random array", sortedArray, Arrays.toString(SortComparison.quickSort(randomArray)));
    		
    	// Test with ordered array
    	
    	double[] orderedArray = {1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14};
    	
    	assertEquals("Checking quickSort with ordered array", sortedArray, Arrays.toString(SortComparison.quickSort(orderedArray)));
    }
    
    /**
     * Check that mergeSort() works
     */
    @Test
    public void testMergeSort()
    {
    	// Test with single element array
        
    	double[] singleArray = {10.4};
    	String singleString = "[10.4]";
    	assertEquals("Checking mergeSort with single element array", singleString, Arrays.toString(SortComparison.mergeSort(singleArray)));
    	
    	
    	String sortedArray = "[1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14]";
    	
    	// Test with array of random doubles
    	
    	double[] randomArray = {2377.88,
    			2910.66, 
    			8458.14, 
    			1522.08, 
    			5855.37,
    			1934.75,
    			8106.23,
    			1735.31,
    			4849.83,
    			1518.63
    			};
    	
    	assertEquals("Checking mergeSort with random array", sortedArray, Arrays.toString(SortComparison.mergeSort(randomArray)));
    	
    	// Test with ordered array
    	
    	double[] orderedArray = {1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14};
    	
    	assertEquals("Checking mergeSort with ordered array", sortedArray, Arrays.toString(SortComparison.mergeSort(orderedArray)));
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    
//    public static void main(String[] args)
//    {
//    	double startTime;
//    	double endTime;
//    	double timeElapsed;
//    	double[] originalArray;
//    	double[] array;
//    	
//    	// Change .txt input file depending on which one you want to test
//    	
//    	In in = new In("numbers10000.txt");
//		originalArray = in.readAllDoubles();
//		
//		// insertionSort()
//		array = copy(originalArray);
//		startTime = System.nanoTime();
//		SortComparison.insertionSort(array);
//		endTime = System.nanoTime();
//		timeElapsed = endTime - startTime;
//		System.out.println("Insertion sort time taken: " + timeElapsed / 1000000);
//		
//		// selectionSort()
//		array = copy(originalArray);
//		startTime = System.nanoTime();
//		SortComparison.selectionSort(array);
//		endTime = System.nanoTime();
//		timeElapsed = endTime - startTime;
//		System.out.println("Selection sort time taken: " + timeElapsed / 1000000);
//		
//		
//		// quickSort()
//		array = copy(originalArray);
//		startTime = System.nanoTime();
//		SortComparison.quickSort(array);
//		endTime = System.nanoTime();
//		timeElapsed = endTime - startTime;
//		System.out.println("Quicksort time taken: " + timeElapsed / 1000000);
//		
//		// mergeSort()
//		array = copy(originalArray);
//		startTime = System.nanoTime();
//		SortComparison.mergeSort(array);
//		endTime = System.nanoTime();
//		timeElapsed = endTime - startTime;
//		System.out.println("Mergesort time taken: " + timeElapsed / 1000000);
//    }
//    
//	private static double[] copy(double[] array) {
//		double[] copyArray = new double[array.length];
//		
//		for(int i = 0; i < array.length; i++) {
//			copyArray[i] = array[i];
//		}
//		return copyArray;
//	}
}
