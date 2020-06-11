
public class Connect4Grid2DArray implements Connect4Grid{
	protected int rowSize;
	protected int columnSize;
	protected String[][] grid;

	public Connect4Grid2DArray(int rowSize, int columnSize) {
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.grid = new String[rowSize][columnSize];
	}

	public void emptyGrid() {
		for(int i=0; i<rowSize; i++) {
			for(int j=0; j<columnSize; j++) {
				grid[i][j] = "[ ]";
			}
		}
	}

	@Override
	public String toString() {
		String gameString = "";
		for(int i = 0; i < rowSize; i++) {
			for(int j = 0; j <columnSize; j++) {
				gameString += grid[i][j] + " ";
			}
			gameString += "\n";
		}
		return gameString;	
	}

	public boolean isValidColumn(int column) {
		if(column > 0 && column <= columnSize)
			return true;
		else
			return false;
	}

	public boolean isColumnFull(int column) {
		for(int i = 0; i < rowSize; i++) {
			if(grid[i][column-1].equals("[ ]"))
				return false;
		}
		return true;
	}

	public void dropPiece(ConnectPlayer player, int column) {
		boolean finished = false;
		int i = rowSize - 1;
		while(!finished && i >= 0) {
			if(grid[i][column-1].equals("[ ]")) {
				grid[i][column-1] = player.playerCounter;
				finished = true;
			}
			i--;
		}
	}

	public boolean didLastPieceConnect4() {
		for(int i = 0; i < rowSize; i++) {
			for(int j = 0; j < columnSize; j++) {
				if(grid[i][j].equals("[R]") || grid[i][j].equals("[Y]")) {
					// check vertical
					if(i + 3 < rowSize) {
						if((grid[i+1][j].equals(grid[i][j])) && (grid[i+2][j].equals(grid[i][j])) && (grid[i+3][j].equals(grid[i][j])))
							return true;
					}
					//check horizontal
					else if(j + 3 < columnSize) {
						if((grid[i][j+1].equals(grid[i][j])) && (grid[i][j+2].equals(grid[i][j])) && (grid[i][j+3].equals(grid[i][j])))
							return true;
					}
					// check diagonal
					else if((i + 3 < rowSize) && (j + 3 < columnSize)) {
						if((grid[i+1][j+1].equals(grid[i][j])) && (grid[i+2][j+2].equals(grid[i][j])) && (grid[i+3][j+3].equals(grid[i][j])))
							return true;
					}
					else if((i + 3 < rowSize) && (j - 3 < columnSize)) {
						if((grid[i+1][j-1].equals(grid[i][j])) && (grid[i+2][j-2].equals(grid[i][j])) && (grid[i+3][j-3].equals(grid[i][j])))
							return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isGridFull() {
		for(int i = 0; i < rowSize; i++) {
			for(int j = 0; j < columnSize; j++) {
				if(grid[i][j].equals("[ ]"))
					return false;
			}
		}
		return true;
	}

}
