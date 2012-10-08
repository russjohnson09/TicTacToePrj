/*****************************************************************
 * Contains all of the GUI components need to run the SuperTicTacToeGame.
 * 
 * @author Russ Johnson
 * @version 10.10.2012
 *****************************************************************/

package package1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SuperTicTacToePanel extends JPanel {

	/** game's board made up of a 2D array of JButtons */
	private JButton[][] board;

	/** holds the size of the board */
	private int size;

	/** JLabel for scoreboard */
	private JLabel xwins;

	/** JLabel for scoreboard */
	private JLabel owins;

	/** JButton to quit gui */
	private JButton quitButton;

	/** JButton to undo move or moves */
	private JButton undo;

	/** JButton to load game */
	private JButton load;

	/** JButton to save game */
	private JButton save;

	/** JButton for computer to make move */
	private JButton ai;

	/** Holds an ImageIcon for X's move that is latter resized */
	private ImageIcon xIconOriginal = new ImageIcon("x.png");

	/** Holds an ImageIcon for O's move that is latter resized */
	private ImageIcon oIconOriginal = new ImageIcon("o.png");

	/** Holds the resized ImageIcon for X that can be used in the GUI. */
	private ImageIcon xIcon;

	/** Holds the resized ImageIcon for O that can be used in the GUI. */
	private ImageIcon oIcon;

	/** Empty ImageIcon for available spaces on board. */
	private ImageIcon emptyIcon = new ImageIcon();

	/** Instance of SuperTicTacToeGame used for the logic of the game. */
	private SuperTicTacToeGame game;

	/** 2D array of Cells that represent moves taken. */
	private Cell[][] iBoard;

	/** JPanel that contains the JButtons quitbutton, load, save, undo, and ai. */
	private JPanel top;

	/** JPanel that contains the 2D Array of JButtons board. */
	private JPanel bottom;

	/** JPanel that contains the two JLabels xwins and owins. */
	private JPanel scoreboard;

	/** Instance of inner class that listens for buttons. */
	ButtonListener listener = new ButtonListener();

	/*****************************************************************
	 * Constructor for the SuperTicTacToePanel.
	 *****************************************************************/
	SuperTicTacToePanel() {

		/** Asks user for size of board. 3 <= board <= 9. */
		String sizeinput = JOptionPane.showInputDialog(null,
				"Enter in the size of the board: (Between 3 and 9 inclusive.");

		/**
		 * Tries to parse integer provided by user. If not an integer or not in
		 * the range 3 to 9.
		 */
		try {
			size = Integer.parseInt(sizeinput);
			if (!(2 < size && size < 10)) {
				throw new Throwable();
			}
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"Invalid input. Default 3 will be used.");
			size = 3;
		}

		/** Asks who should move first. */
		String player = JOptionPane.showInputDialog("Who moves first? X or O");

		/** Holds 0 if O moves first and 1 if X moves first. */
		int playerint;

		/**
		 * Tries to check input for X or O. If cancel is pressed or invalid
		 * input, defaults to X.
		 */
		try {
			if (player.equalsIgnoreCase("X")) {
				playerint = 1;
			} else if (player.equalsIgnoreCase("O")) {
				playerint = 0;
			} else {
				JOptionPane.showMessageDialog(null,
						"Invalid input. Default X will be used.");
				playerint = 1;
			}
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"Invalid input. Default X will be used.");
			playerint = 1;
		}

		/** Starts up the JPanels */
		top = new JPanel();
		top.setLayout(new GridLayout(1, 5));
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(size, size));
		scoreboard = new JPanel();
		scoreboard.setLayout(new FlowLayout());

		/** Sets scoreboard to zero. */
		xwins = new JLabel("X: 0");
		owins = new JLabel("O: 0");

		/** Add xwins and owins to scoreboard. */
		scoreboard.add(xwins);
		scoreboard.add(owins);

		/**
		 * Sets up and quitButton, undo, load, save, and ai and adds them to top
		 * JPanel.
		 */
		quitButton = new JButton("Quit");
		top.add(quitButton);
		undo = new JButton("Undo");
		top.add(undo);
		load = new JButton("Load");
		top.add(load);
		save = new JButton("Save");
		top.add(save);
		ai = new JButton("AI");
		top.add(ai);

		/** Adds the listeners to the JButtons. */
		quitButton.addActionListener(listener);
		undo.addActionListener(listener);
		load.addActionListener(listener);
		save.addActionListener(listener);
		ai.addActionListener(listener);

		/** Instantiates the board using size. */
		board = new JButton[size][size];

		/** Instantiates each JButton in board and adds listener. */
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = new JButton();
				bottom.add(board[row][col]);
				board[row][col].addActionListener(listener);
			}
		}

		/**
		 * Adds JPanels to SuperTicTacToePanel. this.setLayout() etc. is
		 * implied.
		 */
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, top);
		add(BorderLayout.CENTER, bottom);
		add(BorderLayout.SOUTH, scoreboard);

		/** Sets up board with "playerint" starting and having size "size". */
		game = new SuperTicTacToeGame(playerint, size);

		/** Sets up iBoard */
		iBoard = game.getBoard();

		/** Instantiates xIcon and oIcon of correct size. */
		xIcon = new ImageIcon(xIconOriginal.getImage().getScaledInstance(
				600 / size, 600 / size, java.awt.Image.SCALE_SMOOTH));

		oIcon = new ImageIcon(oIconOriginal.getImage().getScaledInstance(
				600 / size, 600 / size, java.awt.Image.SCALE_SMOOTH));

	}

	/*****************************************************************
	 * Sets up the iBoard.
	 * 
	 * @return none
	 *****************************************************************/
	private void displayBoard() {
		iBoard = game.getBoard();

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (iBoard[row][col] == Cell.O) {
					board[row][col].setIcon(oIcon);
				} else if (iBoard[row][col] == Cell.X) {
					board[row][col].setIcon(xIcon);
				} else {
					board[row][col].setIcon(emptyIcon);
				}
			}
		}
	}

	/*****************************************************************
	 * Resets the iBoard and game.
	 * 
	 * @return none
	 *****************************************************************/
	private void reset() {
		String player = JOptionPane.showInputDialog("Who moves first? X or O");

		int playerint;

		try {
			if (player.equalsIgnoreCase("X")) {
				playerint = 1;
			} else if (player.equalsIgnoreCase("O")) {
				playerint = 0;
			} else {
				JOptionPane.showMessageDialog(null,
						"Invalid input. Default X will be used.");
				playerint = 1;
			}
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"Invalid input. Default X will be used.");
			playerint = 1;
		}
		game = new SuperTicTacToeGame(playerint, size);

		xwins.setText("X: " + SuperTicTacToeGame.getXwins());
		owins.setText("O: " + SuperTicTacToeGame.getOwins());

		displayBoard();
	}

	/*****************************************************************
	 * Loads a new game and sets up the GUI based on this game.
	 * 
	 * @return none
	 *****************************************************************/
	private void reload(SuperTicTacToeGame game1) {
		game = game1;
		SuperTicTacToeGame.setOwins(game.getOwin());
		SuperTicTacToeGame.setXwins(game.getXwin());

		size = game.getSize();
		bottom.removeAll();

		board = new JButton[size][size];
		bottom.setLayout(new GridLayout(size, size));

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = new JButton("");
				bottom.add(board[row][col]);
				board[row][col].addActionListener(listener);
			}
		}

		bottom.validate();

		iBoard = game.getBoard();

		xIcon = new ImageIcon(xIconOriginal.getImage().getScaledInstance(
				600 / size, 600 / size, java.awt.Image.SCALE_SMOOTH));

		oIcon = new ImageIcon(oIconOriginal.getImage().getScaledInstance(
				600 / size, 600 / size, java.awt.Image.SCALE_SMOOTH));

		xwins.setText("X: " + SuperTicTacToeGame.getXwins());
		owins.setText("O: " + SuperTicTacToeGame.getOwins());

	}

	/*****************************************************************
	 * Inner listener class.
	 *****************************************************************/
	private class ButtonListener implements ActionListener {

		/*****************************************************************
		 * Takes appropriate action based on the JButton pressed.
		 * 
		 * @return none
		 *****************************************************************/
		public void actionPerformed(ActionEvent event) {

			JComponent comp = (JComponent) event.getSource();

			/** Checks all of the JBottons in board. */
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if (board[row][col] == comp) {
						game.select(row, col);
					}
				}

			}

			if (comp == undo) {
				game.undo();
			}

			if (comp == ai) {
				game.select(game.computersmove());
			}

			if (comp == load) {
				try {

					JFileChooser chooser = new JFileChooser();

					int status = chooser.showOpenDialog(null);

					if (status != JFileChooser.APPROVE_OPTION)
						System.out.println("No File Chosen");
					else {
						File file = chooser.getSelectedFile();
						FileInputStream fileIn = new FileInputStream(file);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						SuperTicTacToeGame game1 = (SuperTicTacToeGame) in
								.readObject();
						in.close();
						fileIn.close();
						reload(game1);
					}

				} catch (IOException i) {
					i.printStackTrace();
				} catch (ClassNotFoundException c) {

				}
			}

			/**
			 * Board is displayed after all of board's buttons and teh undo, ai,
			 * and load buttons have been checked.
			 */
			displayBoard();

			if (comp == quitButton
					&& JOptionPane.showConfirmDialog(null, "Are you sure?") == 0) {
				System.exit(1);

			}

			if (comp == save) {
				try {
					PrintWriter out = null;
					JFileChooser chooser = new JFileChooser();
					int status = chooser.showOpenDialog(null);

					if (status != JFileChooser.APPROVE_OPTION)
						System.out.println("No File Chosen");
					else {
						File file = chooser.getSelectedFile();
						FileOutputStream fileOut = new FileOutputStream(file);
						ObjectOutputStream output = new ObjectOutputStream(
								fileOut);
						output.writeObject(game);
						output.close();
						fileOut.close();
					}
				} catch (IOException i) {
					i.printStackTrace();
				}
			}

			/** GameStatus is checked last after the move has been made. */
			GameStatus g = game.getGameStatus();

			if (g == GameStatus.X_WON) {
				JOptionPane.showMessageDialog(null,
						"X won.\nThe game will reset");
			}
			if (g == GameStatus.O_WON) {
				JOptionPane.showMessageDialog(null,
						"O won.\nThe game will reset");
			}
			if (g == GameStatus.CATS) {
				JOptionPane.showMessageDialog(null,
						"Both X and O lost.\nThe game will reset");
			}

			/** Finally if the game is not in progress it is reset. */
			if (g != GameStatus.IN_PROGRESS) {
				reset();
			}
		}

	}
}
