// See SortComparisonTest.java for execution time results

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
	 
    static double[] insertionSort (double[] a){
    	double tmp;
    	
        for (int i = 1; i < a.length; i++) {
            for(int j = i; j > 0; j--){
                if(a[j] < a[j-1]){
                    tmp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = tmp;
                }
            }   
        }
        return a;
    }
	
	 /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    
    static double[] selectionSort (double[] a){
        for (int i = 0; i < a.length-1; i++) {
            int minimum = i;
            
            for (int j = i+1; j < a.length; j++) {
                if (a[j] < a[minimum])
                    minimum = j;
            }

            double tmp = a[minimum];
            a[minimum] = a[i];
            a[i] = tmp;
        }
        return a;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    
    static double[] quickSort (double[] a){
    	if (a.length == 0 || a.length == 1)
    		return a;
    	
    	return quickSort(a, 0 , a.length-1);
    }
    
    private static double[] quickSort (double[] a, int lo, int hi) {
    	if (hi <= lo)
    		return a;
    	
    	int pivotIndex = partition(a, lo, hi);
    	quickSort(a, lo, pivotIndex-1);
    	quickSort(a, pivotIndex+1, hi);	
    	
    	return a;
    }
    
    private static int partition (double[] a, int lo, int hi) {
    	int i = lo;
    	int j = hi+1;
    	
    	double pivot = a[lo];

    	while(true) {
    		while(a[++i] < pivot) {
    			if(i == hi) {
    				break;
    			}
    		}
    		
    		while(pivot <= a[--j]) {
    			if(j == lo) {
    				break;
    			}
    		}
    		
    		if(i >= j)
    			break;
    		
    		double tmp = a[i];
    		a[i] = a[j];
    		a[j] = tmp;
    		
    	}
    	
    	a[lo] = a[j];
    	a[j] = pivot;
    	return j;
    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */

    static double[] mergeSort (double[] a) {
    	if (a.length == 0 || a.length == 1)
    		return a;
    	
    	double[] auxiliary = new double[a.length];
    	
    	return mergeSort(a, auxiliary, 0, a.length-1);
    }
    
    private static double[] mergeSort (double[] a, double[] auxiliary, int lo, int hi) {
    	if(hi <= lo) 
    		return a;
    	
    	int mid = lo + (hi-lo)/2;
    	mergeSort(a, auxiliary, lo, mid);
    	mergeSort(a, auxiliary, mid+1, hi);
    	merge(a, auxiliary, lo, mid, hi);
    	
    	return a;
    }
    
    private static double[] merge (double[] a, double[] auxiliary, int lo, int mid, int hi) {
    	for (int x = lo; x <= hi; x++)
    		auxiliary[x] = a[x];

    	int i = lo, j = mid+1;
    	for(int k = lo; k <= hi; k++) {
    		if (i > mid)
    			a[k] = auxiliary[j++];
    		else if (j > hi)
    			a[k] = auxiliary[i++];
    		else if (auxiliary[j] < auxiliary[i])
    			a[k] = auxiliary[j++];
    		else
    			a[k] = auxiliary[i++];
    	}
    	return a;	
    }
 }

