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

		moves.push(new Point(row, col));

		if (player == 0) {
			board[row][col] = Cell.O;
		} else {
			board[row][col] = Cell.X;
		}
		nextPlayer();

	}

	public void undo() {
		Point move = moves.pop();
		board[move.x][move.y] = Cell.EMPTY;
		nextPlayer();
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

		Cell c;
		if (player == 0) {
			c = Cell.O;
		} else {
			c = Cell.X;
		}

		if (horizontalCheck(moves.lastElement(), c)) {
			if (player == 0) {
				return GameStatus.O_WON;

			} else {
				return GameStatus.X_WON;
			}
		}

		if (verticalCheck(moves.lastElement(), c)) {
			if (player == 0) {
				return GameStatus.O_WON;

			} else {
				return GameStatus.X_WON;
			}

		}

		if (diagonalCheck(moves.lastElement(), c)) {
			if (player == 0) {
				return GameStatus.O_WON;

			} else {
				return GameStatus.X_WON;
			}
		}

		if (boardIsFull()) {
			return GameStatus.CATS;
		}

		return GameStatus.IN_PROGRESS;
	}

	private boolean diagonalCheck(Point p, Cell c) {
		int row = p.x;
		int col = p.y;

		int x = 1;

		int count = 1;

		// checks down and left diagonal
		while (true) {
			try {
				if (board[row - x][col + x] == c) {
					count++;
					x++;

					if (count == 3) {
						return true;
					}
				} else {
					x = 1;
					break;
				}
			}

			catch (Throwable e) {
				x = 1;
				break;
			}
		}

		while (true) {
			try {
				if (board[row + x][col - x] == c) {
					count++;
					x++;

					if (count == 3) {
						return true;
					}
				} else {
					x = 1;
					break;
				}
			} catch (Throwable e) {
				x = 1;
				break;
			}
		}

		// checks down and right
		while (true) {
			try {
				if (board[row + x][col + x] == c) {
					count++;
					x++;

					if (count == 3) {
						return true;
					}
				} else {
					x = 1;
					break;
				}
			}

			catch (Throwable e) {
				x = 1;
				break;
			}
		}

		while (true) {
			try {
				if (board[row - x][col - x] == c) {
					count++;
					x++;

					if (count == 3) {
						return true;
					}
				} else {
					x = 1;
					break;
				}
			} catch (Throwable e) {
				x = 1;
				break;
			}
		}

		return false;
	}

	private boolean verticalCheck(Point p, Cell c) {
		int row = p.x;
		int col = p.y;

		int count = 1;

		int x = 1;

		while (true) {
			try {
				if (board[row + x][col] == c) {
					count++;
					x++;
					if (count == 3) {
						return true;
					}
				} else {
					x = 1;
					break;
				}

			} catch (Throwable e) {
				x = 1;
				break;
			}
		}

		while (true) {
			try {
				if (board[row - x][col] == c) {
					count++;
					x++;
					if (count == 3) {
						return true;
					}
				} else {
					break;
				}

			} catch (Throwable e) {
				break;
			}
		}

		// if (col - 2 > 0) {
		// for (int co = row - 2; co < size; co++) {
		// if (board[row][co] == c) {
		// count++;
		// if (count == 3) {
		// return true;
		// }
		// } else {
		// count = 0;
		// }
		//
		// }
		//
		// }

		return false;
	}

	private boolean horizontalCheck(Point p, Cell c) {
		int row = p.x;
		int col = p.y;

		int count = 0;

		if (row - 2 > 0) {
			for (int r = row - 2; r < size; r++) {
				if (board[r][col] == c) {
					count++;
					if (count == 3) {
						return true;
					}
				} else {
					count = 0;
				}

			}
		} else {
			for (int r = 0; r < size; r++) {
				if (board[r][col] == c) {
					count++;
					if (count == 3) {
						return true;
					}
				} else {
					count = 0;
				}

			}
		}

		return false;
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

	public boolean isvalid(int row, int col) {
		return board[row][col] == Cell.EMPTY;
	}

}
