import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer {
	C4HumanPlayer() {
		super();
	}
	
	public boolean playerMove(Connect4Grid2DArray grid, ConnectPlayer player) {
		Scanner input = new Scanner(System.in);
		boolean quit = false;
		int column;
		
		while(!quit) {
			System.out.println("What column do you want to drop your piece in? (1-7)");
			if(input.hasNextInt()) {
				column = input.nextInt();
				if(grid.isValidColumn(column) && !grid.isColumnFull(column)) {
					quit = true;
					grid.dropPiece(player, column);
					System.out.println(grid.toString());
					if(grid.didLastPieceConnect4()) {
						System.out.println("You win :)");
						return true;
					}
					if(grid.isGridFull()) {
						System.out.println("Grid full, no one wins :(");
						return true;
					}
				}
				else {
					System.out.println("Column is not valid, please enter another one.");
				}
			}
			else {
				System.out.println("Please only enter numbers between 1-7.");
				input.next();
			}	
		}
		return false;
	}
}
