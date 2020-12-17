// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  Alice Doherty
 *  @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    // Tests 3 arrays which have 3 sets of collinear points using countCollinear and countCollinearFast
    @Test
    public void testCounts()
    {
    	int[] a1 = {1, 5, 2};
    	int[] a2 = {2, 3, 7};
    	int[] a3 = {9, 3, 1};
    	
    	int expectedResult = 3;
    	
    	assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinear(a1, a2, a3));
    	assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));  	
    }
    
    // Tests 3 arrays which have 0 sets of collinear points using countCollinear and countCollinearFast
    @Test
    public void testCountZero()
    {
    	int[] a1 = {1098, 5, 24};
    	int[] a2 = {267, 13, 7009};
    	int[] a3 = {91, 33, 101};  
    	
    	int expectedResult = 0;
    	
    	assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinear(a1, a2, a3));
    	assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));  	
    }
    
    // Tests that countCollinear does not include horizontal lines in count
    @Test
    public void testHorizontal()
    {
    	int[] a1 = {5, 5, 5};
    	int[] a2 = {267, 13, 7009};
    	int[] a3 = {91, 33, 101};  
    	
    	int expectedResult = 0;
    	
    	assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinear(a1, a2, a3));
    	assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));  	
    }
    
    // Tests that sort() returns arrays sorted in correct order
    @Test
    public void testSort()
    {
    	int[] a = {234, 10, 1078};
    	int[] aExpected = {10, 234, 1078};
    	
    	Collinear.sort(a);

    	assertArrayEquals(Arrays.toString(a) + " failed to sort array correctly", aExpected, a);
    }
    
    // Test that sort() doesn't return array sorted in descending order
    @Test
    public void testSortOrder()
    {
    	int[] a = {1, 30422, 112, 3999, 56};
    	int[] aDescending = {30422, 3999, 112, 56, 1};
    	
    	Collinear.sort(a);
 
    	assertFalse(Arrays.toString(a) + " sorted array in descending order", Arrays.equals(aDescending, a));
    }

    // Tests that binarySearch returns true if the key is an element of the array
    @Test
    public void binarySearchTrue()
    {
    	int[] a = {10023, 20, 4, 567};
    	int x = 567;
    	
    	assertTrue("binarySearch failed to find value that is in array", Collinear.binarySearch(a, x));
    }
    
    // Tests that binarySearch returns false if key is not an element of the array
    @Test
    public void binarySearchFalse()
    {
    	int[] a = {10023, 20, 4, 567};
    	int x = 99;
    	
    	assertFalse("binarySearch returned false positive", Collinear.binarySearch(a, x));
    }
    
    // Main method used to measure experimental performance of countCollinear(Fast)
//    public static void main(String[] args) {
//    	In in = new In("r05000-1.txt");
//		int[] a1 = in.readAllInts();
//		
//    	in = new In("r05000-2.txt");
//		int[] a2 = in.readAllInts();
//		
//    	in = new In("r05000-3.txt");
//		int[] a3 = in.readAllInts();
//		
//		Stopwatch stopwatch = new Stopwatch();
//		// StdOut.println(Collinear.countCollinear(a1, a2, a3));
//		StdOut.println(Collinear.countCollinearFast(a1, a2, a3));
//		double time = stopwatch.elapsedTime();
//		StdOut.println("Elapsed time: " + time);
//    }
}
