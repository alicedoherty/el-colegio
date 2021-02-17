import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Alice Doherty
 *  @version HT 2020
 */

 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
	 
    static double[] insertionSort (double[] array){
//    	if (array.length == 0 || array.length == 1)
//    		return array;
    	
    	double tmp;
    	
        for (int i = 1; i < array.length; i++) {
            for(int j = i; j > 0; j--){
                if(array[j] < array[j-1]){
                    tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                }
            }   
        }
        return array;
    }
	
	 /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    
    static double[] selectionSort (double[] array){
    	if (array.length == 0 || array.length == 1)
    		return array;
    	
        int n = array.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min = i;
            for (int j = i+1; j < n; j++)
                if (array[j] < array[min])
                    min = j;

            // Swap the found minimum element with the first element
            double temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
        
        return array;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] quickSort (double[] array){
    	if (array.length == 0 || array.length == 1)
    		return array;
    	
    	return quickSort(array, 0 , array.length-1);
    }
    
    private static double[] quickSort (double[] array, int lo, int hi) {
    	if (hi <= lo)
    		return array;
    	
    	int pivotIndex = partition(array, lo, hi);
    	quickSort(array, lo, pivotIndex-1);
    	quickSort(array, pivotIndex+1, hi);	
    	
    	return array;
    }
    
    private static int partition (double[] array, int lo, int hi) {
    	int i = lo;
    	int j = hi+1;
    	
    	double pivot = array[lo];
    	
    	// change how loops are done
    
    	
    	while(true) {
    		while(array[++i] < pivot) {
    			if(i == hi) {
    				break;
    			}
    		}
    		
    		while(pivot < array[--j]) {
    			if(j == lo) {
    				break;
    			}
    		}
    		
    		if(i >= j)
    			break;
    		
    		double tmp = array[i];
    		array[i] = array[j];
    		array[j] = tmp;
    		
    	}
    	
    	array[lo] = array[j];
    	array[j] = pivot;
    	return j;
    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSort (double[] array) {
    	if (array.length == 0 || array.length == 1)
    		return array;
    	
    	double[] auxiliary = new double[array.length];
    	
    	return mergeSort(array, auxiliary, 0, array.length-1);
    }
    
    private static double[] mergeSort (double[] array, double[] auxiliary, int lo, int hi) {
    	if(hi <= lo) return array;
    	
    	int mid = lo + (hi-lo)/2;
    	mergeSort(array, auxiliary, lo, mid);
    	mergeSort(array, auxiliary, mid+1, hi);
    	merge(array, auxiliary, lo, mid, hi);
    	
    	return array;
    }
    
    private static double[] merge (double[] array, double[] auxiliary, int lo, int mid, int hi) {
    	for (int i = lo; i <= hi; i++)
    		auxiliary[i] = array[i];
    	
    	// rename variables
    	
    	int loCount = lo, midCount = mid+1;
    	for(int i = lo; i <= hi; i++) {
    		if (loCount > mid)
    			array[i] = auxiliary[midCount++];
    		else if (midCount > hi)
    			array[i] = auxiliary[loCount++];
    		else if (auxiliary[midCount] < auxiliary[loCount])
    			array[i] = auxiliary[midCount++];
    		else
    			array[i] = auxiliary[loCount++];
    	}
    	
    	return array;
    	
    }


   


    public static void main(String[] args) {  	
		In in = new In("numbers1000.txt");
		double[] array = in.readAllDoubles();
		Stopwatch stopwatch = new Stopwatch();
		quickSort(array);
		//StdOut.println(insertionSort(array));
		double time = stopwatch.elapsedTime();
		StdOut.println("elapsed time " + time);
		
		System.out.println(Arrays.toString(array));
    	
    }

 }

