import java.util.Random;
public class randomiseOrder {

	public static void randomiseOrder( int[] array )

	{

	  if (array!=null)

	  {

	    Random generator = new Random();

	    for (int index=0; index<array.length; index++ )

	    {

	      int otherIndex = generator.nextInt(array.length);

	      int temp = array[index];

	      array[index] = array[otherIndex];

	      array[otherIndex] = temp;

	    }

	  }

	}

}

// String[] MONTHS = {"January", ...};
// int[] numbers = new int[5];

// CONSTANTS
// CHECK IF NULL

//int[] array = {3, 14, 25};
//int[] tempArray = new int[array.length+1];
//System.arraycopy(array, 0, tempArray, 0, array.length);
//tempArray[tempArray.length-1] = 46;
//array = tempArray;

// char seventh = myString.charAt(6);

// String [][] SEASONS = {
//{"Dec", "Jan", "Feb"},
//{}
//{}
//{}
//} 4x3 array

// int[][] matrix = new int[4][3];
