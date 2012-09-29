package package1;

import java.awt.Point;
import java.util.Stack;

public class SuperTicTacToeGame {

	private Cell[][] board;
	private int player;
	private int size;

	private Stack<Point> moves = new Stack<Point>();

	public Stack<Point> getMoves() {
		return moves;
	}

	public SuperTicTacToeGame(int player, int size) {
		board = new Cell[size][size];
		this.player = player;
		this.size = size;
		reset();

	}

	public void select(int row, int col) {
		if (isvalidmove(row, col)) {
			moves.push(new Point(row, col));

			if (player == 0) {
				board[row][col] = Cell.O;
			} else {
				board[row][col] = Cell.X;
			}
			nextPlayer();
		}

	}

	public boolean isvalidmove(int row, int col) {
		return board[row][col] == Cell.EMPTY;
	}

	public void undo() {
		if (undoIsValid()) {
			Point move = moves.pop();
			board[move.x][move.y] = Cell.EMPTY;
			nextPlayer();
		}
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
		Cell pCell;
		int row = moves.lastElement().x;
		int col = moves.lastElement().y;
		if (player == 0) {
			pCell = Cell.X;
		} else {
			pCell = Cell.O;

		}
		if (isHorizontal(pCell, row, col) || isVertical(pCell, row, col)
				|| isDiagonal(pCell, row, col)) {
			if (pCell == Cell.O) {
				return GameStatus.O_WON;
			} else {
				return GameStatus.X_WON;
			}
		} else if (boardIsFull()) {
			return GameStatus.CATS;

		} else {
			return GameStatus.IN_PROGRESS;
		}
	}

	private boolean isDiagonal(Cell pCell, int row, int col) {
		if (isDiagonalUpperLeft(pCell, row, col)
				|| isDiagonalUpperRight(pCell, row, col)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isDiagonalUpperLeft(Cell pCell, int row, int col) {
		int count = 1;
		while (true) {
			row--;
			col--;
			if (row > -1 && col > -1 && board[row][col] == pCell) {
				count++;
			} else {
				break;
			}
		}
		row += count;
		col += count;
		while (true) {
			row++;
			col++;
			if (row < size && col < size && board[row][col] == pCell) {
				count++;
			} else {
				break;
			}
		}
		if (count > 2) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isDiagonalUpperRight(Cell pCell, int row, int col) {
		int count = 1;
		while (true) {
			row++;
			col--;
			if (row < size && col > -1 && board[row][col] == pCell) {
				count++;
			} else {
				break;
			}
		}
		row -= count;
		col += count;
		while (true) {
			row--;
			col++;
			if (row > -1 && col < size && board[row][col] == pCell) {
				count++;
			} else {
				break;
			}
		}
		if (count > 2) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isVertical(Cell pCell, int row, int col) {
		int count = 1;
		while (true) {
			row--;
			if (row > -1 && board[row][col] == pCell) {
				count++;
			} else {
				break;
			}
		}
		row += count;
		while (true) {
			row++;
			if (row < size && board[row][col] == pCell) {
				count++;
			} else {
				break;
			}
		}
		if (count > 2) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isHorizontal(Cell pCell, int row, int col) {
		int count = 1;
		while (true) {
			col--;
			if (col > -1 && board[row][col] == pCell) {
				count++;
			} else {
				break;
			}
		}
		col += count;
		while (true) {
			col++;
			if (col < size && board[row][col] == pCell) {
				count++;
			} else {
				break;
			}
		}
		if (count > 2) {
			return true;
		} else {
			return false;
		}
	}

	private boolean boardIsFull() {
		for (Cell[] row : board) {
			for (Cell cell : row) {
				if (cell == Cell.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public Cell[][] getBoard() {
		return board;
	}

}
