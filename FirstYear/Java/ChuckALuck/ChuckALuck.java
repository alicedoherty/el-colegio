/* SELF ASSESSMENT 

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7: 7].
Comment: ResolveBet takes in the correct parameters and has void return type
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8].
Comment: Prints out how much cash in the wallet and then asks how much they want to bet
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5].
Comment: If bet amount is greater than what is in the wallet it will ask for another bet amount.
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15].
Comment: Three dice object created and rolled and variable called rollTotal adds values together.
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
Comment: Depending on bet placed it will check certain conditions and return a boolean.
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10].
Comment: Tells you if you have won/lost and bet money is taken, or winnings are added depending on outcome.

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15]
Comment: Asks user for cash, creates wallet and puts in cash.
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5]
Comment: Program continuously asks user for bets until they quit or don't have any money.
I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
Comment: User is asked for this input.
My program calls resolveBet for each bet type entered [Mark out of 5: 5].
Comment: If appropriate input is given, resolveBet is called.
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5]
Comment: After quitting, or when no money is left, the initial amount and final amount is printed.

 Total Mark out of 100 (Add all the previous marks): 100
*/

import java.util.Scanner;

public class ChuckALuck {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		double initialCash = 0;
		boolean quit = false;
		while(!quit) {
			System.out.println("Enter the amount of cash you have: ");
			if (userInput.hasNextDouble())
			{
				initialCash = userInput.nextDouble();
				quit = true;
			}
			else
			{
				System.out.println("Please only enter numbers.");
				userInput.next();
			}
		}
		
		Wallet testWallet = new Wallet();
		testWallet.put(initialCash);

		boolean finished = false;
		Scanner input = new Scanner(System.in);
		
		while (!finished) {	
			if (testWallet.check() == 0)
			{
				finished = true;
				System.out.println("You have no money in your wallet. ");
			}
			
			else
			{
				System.out.println("Place your bet (triple/field/high/low) or 'quit'. ");
				
				if (input.hasNext())
				{
					String betType = input.nextLine();
					
					if ((betType.equalsIgnoreCase("triple")) || (betType.equalsIgnoreCase("field"))
							|| (betType.equalsIgnoreCase("high")) || (betType.equalsIgnoreCase("low"))) 
						ResolveBet(betType, testWallet);
					
					else if (betType.equalsIgnoreCase("quit"))
						finished = true;
					
					else
						System.out.println("Please only place the bets 'triple', 'field', 'high' or'low'");
				}
			}
		}

		if (finished == true)
			System.out.println("Curent cash: " + testWallet.check() + "\nInitial cash: " + initialCash);
	}

	public static void ResolveBet(String betType, Wallet testWallet) {
		System.out.println(testWallet.toString());

		boolean finished = false;
		double betAmount = 0;
		double winnings = 0;

		Scanner input = new Scanner(System.in);

		while (!finished) 
		{
			System.out.println("Enter amount you want to bet: ");
			betAmount = input.nextDouble();
			
			if (testWallet.get(betAmount) == false)
				System.out.println("The bet amount is greater than the cash in the wallet, "
						+ "please enter another amount. ");
			else
				finished = true;
		}

		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		Dice dice3 = new Dice();

		int roll1 = dice1.roll();
		int roll2 = dice2.roll();
		int roll3 = dice3.roll();

		System.out.println("You rolled: " + dice1.topFace() + ", " + dice2.topFace() + ", " + dice3.topFace());

		int rollTotal = roll1 + roll2 + roll3;

		boolean correctBet = false;

		if (betType.equalsIgnoreCase("triple")) 
		{
			if ((roll1 == 1 && roll2 == 1 && roll3 == 1) || (roll1 == 6 && roll2 == 6 && roll3 == 6))
				correctBet = false;
			
			else if (roll1 == roll2 && roll2 == roll3)
			{
				correctBet = true;
				winnings = betAmount * 30;
			}
		}

		if (betType.equalsIgnoreCase("field")) 
		{
			if (rollTotal < 8 || rollTotal > 12)
			{
				correctBet = true;
				winnings = betAmount;
			}
		}

		if (betType.equalsIgnoreCase("high"))
		{
			if ((roll1 == 4 && roll2 == 4 && roll3 == 4) || (roll1 == 5 && roll2 == 5 && roll3 == 5)
					|| (roll1 == 6 && roll2 == 6 && roll3 == 6))
				correctBet = false;
			
			else if (rollTotal > 10)
			{
				correctBet = true;
				winnings = betAmount;
			}
		}

		if (betType.equalsIgnoreCase("low"))
		{
			if ((roll1 == 1 && roll2 == 1 && roll3 == 1) || (roll1 == 2 && roll2 == 2 && roll3 == 2)
					|| (roll1 == 3 && roll2 == 3 && roll3 == 3))
				correctBet = false;
			
			else if (rollTotal < 11)
			{
				correctBet = true;
				winnings = betAmount;
			}
		}

		if (correctBet)
		{
			System.out.println("Well done, you win.");
			testWallet.put(winnings + betAmount);
		}
		
		else
			System.out.println("Hard luck, you've lost.");
	}
}
