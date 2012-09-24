package package1;

public class SuperTicTacToeGame {

	private Cell[][] board;
	private GameStatus status;
	private int player;
	private int size;

	public SuperTicTacToeGame() {
		status = GameStatus.IN_PROGRESS;
		board = new Cell[3][3];
		player = 0;
		this.size = 3;
		reset();
	}

	public SuperTicTacToeGame(int player) {
		status = GameStatus.IN_PROGRESS;
		board = new Cell[3][3];
		this.player = player;
		this.size = 3;
		reset();

	}

	public SuperTicTacToeGame(int player, int size) {
		status = GameStatus.IN_PROGRESS;
		board = new Cell[size][size];
		this.player = player;
		this.size = size;
		reset();

	}

	public boolean select(int row, int col) {
		boolean isvalid;
		if (board[row][col] == Cell.EMPTY) {
			if (player == 0) {
				board[row][col] = Cell.O;
			} else {
				board[row][col] = Cell.X;
			}

			isvalid = true;
			nextPlayer();
		}

		else {
			isvalid = false;

		}
		return isvalid;

	}

	public void reset() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = Cell.EMPTY;
			}
		}
	}

	private void nextPlayer() {
		player = (player + 1) % 2;
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
