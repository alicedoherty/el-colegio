import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer{

	C4RandomAIPlayer() {
		super();
	}

	public boolean playerMove(Connect4Grid2DArray grid, ConnectPlayer player) {
		Random columnGenerator = new Random();
		int column = columnGenerator.nextInt(grid.columnSize)+1;

		grid.dropPiece(player, column);
		System.out.println(grid.toString());
		if(grid.didLastPieceConnect4()) {
			System.out.println("Computer wins :(");
			return true;
		}
		if(grid.isGridFull()) {
			System.out.println("Grid full, no one wins :(");
			return true;
		}
	return false;
	}
}