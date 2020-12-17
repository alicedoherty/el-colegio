// -------------------------------------------------------------------------
/**
 * This class contains only two static methods that search for points on the
 * same line in three arrays of integers.
 *
 * @author Alice Doherty
 * @version 18/09/18 12:21:09
 */
class Collinear {
	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-horizontal lines that go through 3 points in
	 * arrays a1, a2, a3. This method is static, thus it can be called as
	 * Collinear.countCollinear(a1,a2,a3)
	 * 
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the
	 *            point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the
	 *            point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the
	 *            point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a
	 *         horizontal line.
	 *
	 *         Array a1, a2 and a3 contain points on the horizontal line y=1, y=2
	 *         and y=3, respectively. A non-horizontal line will have to cross all
	 *         three of these lines. Thus we are looking for 3 points, each in a1,
	 *         a2, a3 which lie on the same line.
	 *
	 *         Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they
	 *         are on the same line) if
	 * 
	 *         x1(y2-y3)+x2(y3-y1)+x3(y1-y2)=0
	 *
	 *         In our case y1=1, y2=2, y3=3.
	 *
	 *         You should implement this using a BRUTE FORCE approach (check all
	 *         possible combinations of numbers from a1, a2, a3)
	 *
	 *         ------------------------------------------------------------------
	 *         
	 *         Experimental Performance -----------------------------------------
	 *         
	 *         Input Size (N)		Running Time (secs)		Count
	 *         1000					0.356					41582
	 *         2000					2.741					3927
	 *         4000					21.173					31783
	 *         5000					42.636					61322
	 *         
	 *         
	 *         Assuming running time is in form aN^b ---------------------------- 
	 *         - Estimate of b and a (using spreadsheet from notes)
	 *         a = 2.966
	 *         b = -31.055
	 *         2^b = 4.48 * 10^-10
	 *         
	 *         - Predict running time for N = 5000
	 *         T(N) = (2^b) * N^a
	 *         T(5000) = 41.9429
	 *         
	 *         - Error of prediction
	 *         Error = ((Actual time) - (Predicted time)) * 100 / (Predicted time)
	 *         Error = 1.652%
	 *	
	 * 
	 *         Order of Growth --------------------------------------------------
	 *
	 *         Calculate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of growth: N^3
	 *
	 *         Explanation: Three nested linear for-loops (each iteration of each 
	 *         	for-loop reduces the boundaries by a constant (1)), therefore N*N*N = N^3.
	 *     
	 */
	static int countCollinear(int[] a1, int[] a2, int[] a3) {
		int count = 0;
		int y1 = 1, y2 = 2, y3 = 3;

		for (int i = 0; i < a1.length; i++)
			for (int j = 0; j < a2.length; j++)
				for (int k = 0; k < a3.length; k++)
					if ((a1[i] * (y2 - y3) + a2[j] * (y3 - y1) + a3[k] * (y1 - y2)) == 0)
						count++;

		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-horizontal lines that go through 3 points in
	 * arrays a1, a2, a3. This method is static, thus it can be called as
	 * Collinear.countCollinearFast(a1,a2,a3)
	 * 
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the
	 *            point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the
	 *            point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the
	 *            point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a
	 *         horizontal line.
	 *
	 *         In this implementation you should make non-trivial use of
	 *         InsertionSort and Binary Search. The performance of this method
	 *         should be much better than that of the above method.
	 *         
	 *         ------------------------------------------------------------------
	 *         
	 *         Experimental Performance -----------------------------------------
	 *         
	 *         Input Size (N)		Running Time (secs)		Count
	 *         1000					0.058					41582
	 *         2000					0.2						3927
	 *         4000					0.846					31783
	 *         5000					1.377					61322
	 *         
	 *         
	 *         Speedup -----------------------------------------------------------
	 *         
	 *         Speedup = (time of countCollinear) / (time of countCollinearFast)
	 *         
	 *         Input size (N)			Speedup
	 *         1000						6.138
	 *         2000						13.705
	 *         4000						25.027
	 *         5000						30.962
	 *         
	 *         
	 *         Order of Growth ---------------------------------------------------
	 *
	 *         Calculate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of Growth: N^2logN
	 *
	 *         Explanation: Insertion sort at the beginning (N^2 - two linear for-loops),
	 *         	then followed by two nested linear for-loops (N^2) with the inner most 
	 *         	implementing binary sort (logN - each iteration of the loop divides 
	 *         	the boundaries by a constant).
	 *         	This gives N^2 + N^2logN BUT we only count highest power, therefore
	 *         	order of growth is N^2logN
	 *
	 */
	static int countCollinearFast(int[] a1, int[] a2, int[] a3) {
		int count = 0;
		int y1 = 1, y2 = 2, y3 = 3;
		int x;

		Collinear.sort(a3);

		for (int i = 0; i < a1.length; i++) {
			for (int j = 0; j < a2.length; j++) {
				x = (-1 * (a1[i] * (y2 - y3) + a2[j] * (y3 - y1))) / (y1 - y2);
				if (Collinear.binarySearch(a3, x))
					count++;
			}
		}
		return count;
	}

	// ----------------------------------------------------------
	/**
	 * Sorts an array of integers according to InsertionSort. This method is static,
	 * thus it can be called as Collinear.sort(a)
	 * 
	 * @param a: An UNSORTED array of integers.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 *
	 *         ----------------------------------------------------------
	 *
	 *         Order of Growth ------------------------------------------
	 *
	 *         Calculate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of Growth: N^2
	 *
	 *         Explanation: Two linear for-loops (each iteration reduces the
	 *        	 boundaries by a constant (1)).
	 *
	 */
	static void sort(int[] a) {
		for (int j = 1; j < a.length; j++) {
			int i = j - 1;
			while (i >= 0 && a[i] > a[i + 1]) {
				int temp = a[i];
				a[i] = a[i + 1];
				a[i + 1] = temp;
				i--;
			}
		}
	}

	// ----------------------------------------------------------
	/**
	 * Searches for an integer inside an array of integers. This method is static,
	 * thus it can be called as Collinear.binarySearch(a,x)
	 * 
	 * @param a: A array of integers SORTED in ascending order.
	 * @param x: An integer.
	 * @return true if 'x' is contained in 'a'; false otherwise.
	 *
	 *         ----------------------------------------------------------
	 *
	 *         Order of Growth ------------------------------------------
	 *
	 *         Calculate and write down the order of growth of your algorithm. You
	 *         can use the asymptotic notation. You should adequately explain your
	 *         answer. Answers without adequate explanation will not be counted.
	 *
	 *         Order of Growth: logN
	 *
	 *         Explanation: Each iteration of the while loop divides the boundaries
	 *         	(by roughly a half) therefore is logarithmic and we assume contents of 
	 *         	while loop have a constant order of growth.
	 *
	 */
	static boolean binarySearch(int[] a, int x) {
		int lo = 0, hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (x < a[mid])
				hi = mid - 1;
			else if (x > a[mid])
				lo = mid + 1;
			else
				return true;
		}
		return false;
	}

}