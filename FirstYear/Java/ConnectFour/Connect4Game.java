/* SELF ASSESSMENT

Connect4Game class (35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment: Class creates appropriate reference to C4 grid and players and asks all specified questions, carrying out appropriate checks.

Connect4Grid interface (10 marks)
I define all 7 methods within this interface.
Comment: All methods are defined in interface.

Connect4Grid2DArray class (25 marks) 
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment: Connect4Grid carries out all the methods mentioned.

ConnectPlayer abstract class (10 marks)
My class provides at lest one non-abstract method and at least one abstract method. 
Comment: setPlayerCounter is non-abstract and playerMove is an abstract method.

C4HumanPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides the Human player functionality.
Comment: It extends ConnectPlayer and overrides the abstract methods. It allows human to play through playerMove()

C4RandomAIPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides AI player functionality. 
Comment: It extends ConnectPlayer and overrides the abstract methods. It makes AI move through playerMove()

Total Marks out of 100: 100

*/

import java.util.Scanner;

public class Connect4Game {
	public static final int ROW_SIZE = 6;
	public static final int COLUMN_SIZE = 7;
	public static final String YELLOW = "[Y]";
	public static final String RED = "[R]";

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean finished = false;
		boolean valid = true;
		
		while(!finished) {
			System.out.println("Do you want to play a game of Connect Four? (enter 'yes' or 'quit')");
			String userChoice = input.nextLine();
			
			if(userChoice.equalsIgnoreCase("yes")) {
				Connect4Grid2DArray grid = new Connect4Grid2DArray(ROW_SIZE, COLUMN_SIZE);
				grid.emptyGrid();
				
				do {
					System.out.println("Do you want to play against the computer or another human? (enter 'computer' or 'human')");
					String userInput = input.nextLine();
					
					if(userInput.equalsIgnoreCase("computer")) {
						valid = true;
						C4HumanPlayer player = new C4HumanPlayer();
						C4RandomAIPlayer AI = new C4RandomAIPlayer();
						do {
							System.out.println("Do you want to be yellow or red? (enter 'Y' or 'R')");
							String userColourChoice = input.nextLine();
							if(userColourChoice.equalsIgnoreCase("Y")) {
								valid = true;
								player.setPlayerCounter(YELLOW);
								AI.setPlayerCounter(RED);
								playGame(grid, player, AI);
							}
							else if(userColourChoice.equalsIgnoreCase("R")) {
								valid = true;
								player.setPlayerCounter(RED);
								AI.setPlayerCounter(YELLOW);
								playGame(grid, player, AI);
							}
							else {
								valid = false;
								System.out.println("Input not valid.");
							}
						}while(!valid);
					}
					else if(userInput.equalsIgnoreCase("human")) {
						valid = true;
						C4HumanPlayer player1 = new C4HumanPlayer();
						C4HumanPlayer player2 = new C4HumanPlayer();
						do {
							System.out.println("Does Player 1 want to be yellow or red? (enter 'Y' or 'R')");
							String userColourChoice = input.nextLine();
							if(userColourChoice.equalsIgnoreCase("Y")) {
								valid = true;
								player1.setPlayerCounter(YELLOW);
								player2.setPlayerCounter(RED);
								playGame(grid, player1, player2);
							}
							else if(userColourChoice.equalsIgnoreCase("R")) {
								valid = true;
								player1.setPlayerCounter(RED);
								player2.setPlayerCounter(YELLOW);
								playGame(grid, player1, player2);
							}
							else {
								valid = false;
								System.out.println("Input not valid.");
							}
						}while(!valid);
					}
					else {
						System.out.println("Input not valid.");
						valid = false;
					}
				} while(!valid);
				
			}
			else if(userChoice.equalsIgnoreCase("quit")) {
				finished = true;
			}
			else {
				System.out.println("Invalid input.");	
			}
		}
	}
	
	public static void playGame(Connect4Grid2DArray grid, ConnectPlayer player1, ConnectPlayer player2) {
		boolean gameOver = false;

		System.out.println(grid.toString());
		while(!gameOver) {

			gameOver = player1.playerMove(grid, player1);
			if(!gameOver)
				gameOver = player2.playerMove(grid, player2);		
		}
	}
}
