/*****************************************************************
 * Controls the games logic. Implements java.io.Serializable so that it can
 * easily be saved.
 * 
 * @author Russ Johnson
 * @version 10.10.2012
 *****************************************************************/

package package1;

import java.awt.Point;
import java.util.Stack;

public class SuperTicTacToeGame implements java.io.Serializable {

	/** A 2D array of Cells. Represents game's board. */
	private Cell[][] board;

	/** static variable that represents total wins for X */
	private static int xwins = 0;

	/** static variable that represents total wins for O */
	private static int owins = 0;

	/** stores the total wins for X when an instance of the class is saved */
	private int xwin;

	/** stores the total wins for O when an instance of the class is saved */
	private int owin;

	/** player's move (0 for O and 1 for X) */
	private int player;

	/** size of board */
	private int size;

	/** keeps track of each move made */
	private Stack<Point> moves = new Stack<Point>();

	/*****************************************************************
	 * Constructor for SuperTicTacToeGame
	 *****************************************************************/
	public SuperTicTacToeGame(int player, int size) {
		board = new Cell[size][size];
		this.player = player;
		this.size = size;
		xwin = xwins;
		owin = owins;
		reset();

	}

	/*****************************************************************
	 * Get method for player.
	 * 
	 * @return player player for game
	 *****************************************************************/
	public int getPlayer() {
		return player;
	}

	/*****************************************************************
	 * Get method for size.
	 * 
	 * @return size the size of board
	 *****************************************************************/
	public int getSize() {
		return size;
	}

	/*****************************************************************
	 * Get method for moves.
	 * 
	 * @return moves this contains all moves taken
	 *****************************************************************/
	public Stack<Point> getMoves() {
		return moves;
	}

	/*****************************************************************
	 * Get method for xwin.
	 * 
	 * @return xwin this mirrors the static variable xwins
	 *****************************************************************/
	public int getXwin() {
		return xwin;
	}

	/*****************************************************************
	 * Get method for owin.
	 * 
	 * @return owin this mirrors the static varibale owins
	 *****************************************************************/
	public int getOwin() {
		return owin;
	}

	/*****************************************************************
	 * Get method for xwins.
	 * 
	 * @return xwins total wins for X
	 *****************************************************************/
	public static int getXwins() {
		return xwins;
	}

	/*****************************************************************
	 * Get method for owins.
	 * 
	 * @return owins total wins for O
	 *****************************************************************/
	public static int getOwins() {
		return owins;
	}

	/*****************************************************************
	 * Set method for xwins.
	 * 
	 * @param xwins
	 *            set total wins for X (xwins)
	 * 
	 * @return none
	 *****************************************************************/
	public static void setXwins(int xwins) {
		SuperTicTacToeGame.xwins = xwins;
	}

	/*****************************************************************
	 * Set method for owins.
	 * 
	 * @param set
	 *            total wins for O (owins)
	 * 
	 * @return none
	 *****************************************************************/
	public static void setOwins(int owins) {
		SuperTicTacToeGame.owins = owins;
	}

	/*****************************************************************
	 * Selects a space on the board to move and sets it if valid.
	 * 
	 * @param row
	 *            select row of board
	 * 
	 * @param col
	 *            select column of board
	 * 
	 * @return none
	 *****************************************************************/
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

	/*****************************************************************
	 * Selects a space on the board to move and sets it if valid.
	 * 
	 * @param p
	 *            selects point of board
	 * 
	 * @return none
	 *****************************************************************/
	public void select(Point p) {
		int row = p.x;
		int col = p.y;
		if (isvalidmove(row, col)) {
			moves.push(p);

			if (player == 0) {
				board[row][col] = Cell.O;
			} else {
				board[row][col] = Cell.X;
			}
			nextPlayer();
		}

	}

	/*****************************************************************
	 * Returns a boolean for whether or not a move is valid.
	 * 
	 * @param row
	 *            selects row of board
	 * 
	 * @param col
	 *            selects column of board
	 * 
	 * @return boolean returns True if valid move else otherwise
	 *****************************************************************/
	public boolean isvalidmove(int row, int col) {
		return board[row][col] == Cell.EMPTY;
	}

	/*****************************************************************
	 * Undoes a move.
	 * 
	 * @return none
	 *****************************************************************/
	public void undo() {
		if (undoIsValid()) {
			Point move = moves.pop();
			board[move.x][move.y] = Cell.EMPTY;
			nextPlayer();
		}
	}

	/*****************************************************************
	 * Returns a boolean for whether or not undo is valid.
	 * 
	 * @return boolean returns true if valid undo false otherwise
	 *****************************************************************/
	public boolean undoIsValid() {
		return !(moves.isEmpty());
	}

	/*****************************************************************
	 * Resets the board.
	 * 
	 * @return none
	 *****************************************************************/
	public void reset() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = Cell.EMPTY;
			}
		}

	}

	/*****************************************************************
	 * Sets the player to the next player.
	 * 
	 * @return none
	 *****************************************************************/
	private void nextPlayer() {
		player = (player + 1) % 2;
	}

	/*****************************************************************
	 * Returns the status of the game and increment owins or xwins if either
	 * wins.
	 * 
	 * @return GameStatus Status of the game.
	 *****************************************************************/
	public GameStatus getGameStatus() {
		if (moves.size() > 4) {
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
					owins++;
					return GameStatus.O_WON;
				} else {
					xwins++;
					return GameStatus.X_WON;
				}
			} else if (boardIsFull()) {
				return GameStatus.CATS;

			} else {
				return GameStatus.IN_PROGRESS;
			}
		}
		return GameStatus.IN_PROGRESS;
	}

	/*****************************************************************
	 * Checks for a three in a row diagonally.
	 * 
	 * @return boolean true if diagonal is found false otherwise
	 *****************************************************************/
	private boolean isDiagonal(Cell pCell, int row, int col) {
		return (isDiagonalUpperLeft(pCell, row, col) || isDiagonalUpperRight(
				pCell, row, col));
	}

	/*****************************************************************
	 * Checks for a three in a row diagonally from the upper left.
	 * 
	 * @return boolean true if diagonal is found false otherwise
	 *****************************************************************/
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

	/*****************************************************************
	 * Checks for a three in a row diagonally from the upper right.
	 * 
	 * @return boolean true if diagonal is found false otherwise
	 *****************************************************************/
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

	/*****************************************************************
	 * Checks for a three in a row vertically.
	 * 
	 * @return boolean true if vertical is found false otherwise
	 *****************************************************************/
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

	/*****************************************************************
	 * Checks for a three in a row horizontally.
	 * 
	 * @return boolean true if horizontal is found false otherwise
	 *****************************************************************/
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

	/*****************************************************************
	 * Checks for the board being full.
	 * 
	 * @return boolean true if board is full false otherwise
	 *****************************************************************/
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

	/*****************************************************************
	 * Get method for board.
	 * 
	 * @return board board for game
	 *****************************************************************/
	public Cell[][] getBoard() {
		return board;
	}

	/*****************************************************************
	 * The computer makes a move.
	 * 
	 * @return Point point on the board
	 *****************************************************************/
	public Point computersmove() {
		Point move;
		move = winningmove();
		if (move.x != -1) {
			return move;
		}
		move = blockingmove();
		if (move.x != -1) {
			return move;
		} else {
			return dumbmove();
		}

	}

	/*****************************************************************
	 * Returns a point that is the first available move.
	 * 
	 * @return Point point on the board
	 *****************************************************************/
	private Point dumbmove() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (isvalidmove(row, col)) {
					select(row, col);
					return new Point(row, col);
				}
			}
		}
		return new Point(-1, -1);
	}

	/*****************************************************************
	 * Finds a winning move.
	 * 
	 * @return Point point on the board
	 *****************************************************************/
	private Point winningmove() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (isvalidmove(row, col)) {
					select(row, col);
					if (getGameStatusNoInc() != GameStatus.IN_PROGRESS) {
						undo();
						return new Point(row, col);
					} else {
						undo();
					}
				}
			}
		}

		return new Point(-1, -1);
	}

	/*****************************************************************
	 * Finds a blocking move. This is a move that if not taken could cause the
	 * opponent to win in their next move.
	 * 
	 * @return Point point on the board
	 *****************************************************************/
	private Point blockingmove() {
		nextPlayer();
		Point move = winningmove();
		nextPlayer();
		return move;

	}

	/*****************************************************************
	 * Returns the status of the game. Same as getGameStatus but does not
	 * increment the wins. Needed for computersmove.
	 * 
	 * @return GameStatus status of the game
	 *****************************************************************/
	public GameStatus getGameStatusNoInc() {
		if (moves.size() > 4) {
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
		return GameStatus.IN_PROGRESS;
	}
}
