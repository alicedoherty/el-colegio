/* SELF ASSESSMENT 
   1.  createSequence:
Did I use the correct method definition?
Mark out of 5: 5
Comment: Method defined properly
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5: 5
Comment: createSequence() creates an array and initialises it will all values up to n
Did I return the correct item?
Mark out of 5: 5
Comment: Correct item returned
   2.  crossOutMultiples
Did I use the correct method definition?
Mark out of 5: 5
Comment: Method defined properly
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2: 2
Comment: Parameters are not null and variable called multipleNumber is used
Did I loop through the array using the correct multiple?
Mark out of 5: 5
Comment: Loop is used in the sieve() method which calls the crossOutMultiples method
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3: 3
Comment: Correct numbers are crossed out
   3.  sieve   
Did I have the correct function definition?
Mark out of 5:5
Comment: Functions defined correctly
Did I make calls to other methods?
Mark out of 5: 5 
Comment:  Calls the crossOutMultiple() function   
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2: 2
Comment: Sieve returns array with all non-prime numbers crossed out
   4.  sequenceTostring  
Did I have the correct function definition?
Mark out of 5: 5
Comment: Function defined correctly
Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment: Sequence passed through is not null
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets? 
Mark out of 10: 10
Comment:   Loop updates String to put crossed numbers into brackets
   5.  nonCrossedOutSubseqToString  
Did I have the correct function definition
Mark out of 5:5
Comment: Function defined correctly
Did I ensure the parameter to be used is not null?  
Mark out of 3:3
Comment: Parameter is not null
Did I loop through the array updating the String variable with just the non-crossed out numbers? 
Mark out of 5: 5
Comment: String is updated with only non-crossed out numbers
   6.  main  
Did I ask  the user for input n and handles input errors?  
Mark out of 5: 5 
Comments: Makes sure input is 2 or more
Did I make calls to other methods (at least one)?
Mark out of 5: 5
Comment:  Calls other methods in the main
Did I print the output as shown in the question?  
Mark out of 5: 5
Comment:  List of numbers, crossed out numbers and only prime numbers printed
   7.  Overall
Is my code indented correctly?
Mark out of 4:4
Comments:Indented correctly
Do my variable names make sense?
Mark out of 4: 4
Comments: Clear variable names
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4: 4
Comments: Follows coding standard
      Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;
import java.util.Arrays;

public class SieveOfEratosthenes {

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a positive integer (value must be at least 2): ");
		
		if (input.hasNextInt())
		{
			int number = input.nextInt();
			
			if (number >= 2)
			{
				int[][] sequence = createSequence(number);
				
				int[] row = createSequence(number)[0];
				
				String printSequence = Arrays.toString(row).replace("[", "").replace("]", "");
				System.out.println(printSequence);
				
				int[][] crossedOutSequence = sieve(number, sequence);
				System.out.println(sequenceToString(crossedOutSequence));
				System.out.println(nonCrossedOutSubseqToString(crossedOutSequence));
			}
			else
			{
				System.out.println("Please only enter integers equal to or greater than 2.");
			}
		}
		else
		{
			System.out.println("Input not valid.");
		}
		
		input.close();
	}

	public static int[][] createSequence(int number)
	{
		int[][] sequence = new int[2][number-1];
		for (int index = 0; index < sequence[0].length; index++)
		{
			sequence[0][index] = index + 2;
			sequence[1][index] = 0;
		}
		return sequence;	
	}
	
	public static int[][] crossOutHigherMultiples(int[][] sequence, int multipleNumber)
	{
		for (int index = 0; index < sequence[0].length; index++)
		{
			if (sequence[0][index]/multipleNumber != 1)
			{
				if (sequence[0][index] % multipleNumber == 0)
				{
						sequence[1][index] = 1;
				}
			}
		}
		int[][] crossedOutHigherMultiples = sequence;
		return crossedOutHigherMultiples;
	}
	
	public static int[][] sieve(int number, int[][] crossedOutHigherMultiples) 
	{
		int[][] tempCrossedOutSequence;
		for (int multipleNumber = 2; multipleNumber <= Math.sqrt(number); multipleNumber++)
		{
			if (crossedOutHigherMultiples[1][multipleNumber - 2] == 0)
			{
				tempCrossedOutSequence = crossOutHigherMultiples(crossedOutHigherMultiples, multipleNumber);
			}
		}
	
		int[][] crossedOutSequence = crossedOutHigherMultiples;	
		return crossedOutSequence;
	}
	
	public static String sequenceToString(int[][] sequence) 
	{ 	
		String sequenceAsString = "";
		for (int index = 0; index < sequence[0].length; index++)
		{
			if (sequence[1][index] == 1)
			{
				sequenceAsString += "[" + sequence[0][index] + "]" + ((index != sequence[0].length - 1)? ", ": "");
			}
			else
			{
				sequenceAsString += sequence[0][index] + ((index != sequence[0].length - 1)? ", ": "");
			}
		}
		return sequenceAsString; 
	}
	 
	public static String nonCrossedOutSubseqToString(int[][] sequence) 
	{
		String nonCrossedOutString = "";
		for (int index = 0; index < sequence[0].length; index++)
		{
			if (sequence[1][index] == 0)
			{
				nonCrossedOutString += sequence[0][index] + ((index != sequence[0].length - 1)? ", ": "");
			}
		}
		return nonCrossedOutString;
	}	
}
