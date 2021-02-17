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
    public static void main(String[] args)
    {
        //TODO: implement this method
    	
    	// reset array each time
    }

}

