package package1;

public class SuperTicTacToeGame {

	private Cell[][] board;
	private GameStatus status;
	private int player;

	public SuperTicTacToeGame() {
		status = GameStatus.IN_PROGRESS;
		board = new Cell[3][3];
		player = 0;
		reset();
	}

	public SuperTicTacToeGame(int player) {
		this.player = player;

	}

	public void select(int row, int col) {
		board[row][col] = Cell.O;
	}

	public void reset() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = Cell.EMPTY;
			}
		}
	}

	public int nextPlayer() {
		player = (player + 1) % 2;
		return player;
	}

	public int getCurrentPlayer() {
		return player;
	}

	public GameStatus getGameStatus() {
		return GameStatus.X_WON;
		return GameStatus.O_WON;
		return GameStatus.CATS;
		return GameStatus.IN_PROGRESS;
	}

	public Cell[][] getBoard() {
		return board;
	}

}
