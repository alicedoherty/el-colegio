
public abstract class ConnectPlayer {
	public String playerCounter;
	
	ConnectPlayer() {
	}
	
	public void setPlayerCounter(String playerCounter) {
		this.playerCounter = playerCounter;
	}
	
	public abstract boolean playerMove(Connect4Grid2DArray grid, ConnectPlayer player);
}
