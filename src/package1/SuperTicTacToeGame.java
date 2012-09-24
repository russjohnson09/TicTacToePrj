package package1;

import java.awt.Point;
import java.util.Stack;

public class SuperTicTacToeGame {

	private Cell[][] board;
	private int player;
	private int size;

	// private ArrayList<Point> moves;
	private Stack<Point> moves = new Stack();

	// public SuperTicTacToeGame() {
	// status = GameStatus.IN_PROGRESS;
	// board = new Cell[3][3];
	// player = 0;
	// this.size = 3;
	// reset();
	// }
	//
	// public SuperTicTacToeGame(int player) {
	// status = GameStatus.IN_PROGRESS;
	// board = new Cell[3][3];
	// this.player = player;
	// this.size = 3;
	// reset();
	//
	// }

	public SuperTicTacToeGame(int player, int size) {
		board = new Cell[size][size];
		this.player = player;
		this.size = size;
		reset();

	}

	public boolean select(int row, int col) {
		boolean isvalid;
		if (board[row][col] == Cell.EMPTY) {

			moves.push(new Point(row, col));

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

	public void undo() {
		Point move = moves.pop();
		board[move.x][move.y] = Cell.EMPTY;
	}

	public boolean undoIsValid() {
		return !(moves.isEmpty());
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
