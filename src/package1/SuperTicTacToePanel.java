package package1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SuperTicTacToePanel extends JPanel {
	private JButton[][] board;
	private Cell[][] iBoard;
	private ImageIcon xIcon;
	private ImageIcon oIcon;

	private SuperTicTacToeGame game;

	//

	private JPanel top;
	private JPanel bottom;

	private JButton quitButton;
	private JButton undo;
	private JButton load;
	private JButton save;

	SuperTicTacToePanel() {

		String size = JOptionPane.showInputDialog(null,
				"Enter in the size of the board: (Between 3 and 9 inclusive.");

		int sizeint;

		try {
			sizeint = Integer.parseInt(size);
			assert (2 < sizeint && sizeint < 10);
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null,
					"Invalid input. Default 3 will be used.");
			sizeint = 3;
		}

		String player = JOptionPane.showInputDialog("Who moves first? X or O");

		int playerint;

		if (player.equalsIgnoreCase("X")) {
			playerint = 1;
		}
		if (player.equalsIgnoreCase("O")) {
			playerint = 0;
		} else {
			JOptionPane.showMessageDialog(null,
					"Invalid input. Default X will be used.");
			playerint = 1;

		}

		xIcon = new ImageIcon("x.png");
		oIcon = new ImageIcon("o.png");

		top = new JPanel();
		top.setLayout(new GridLayout(1, 4));
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(3, 3));

		quitButton = new JButton("Quit");
		top.add(quitButton);
		undo = new JButton("Undo");
		top.add(undo);
		load = new JButton("Load");
		top.add(load);
		save = new JButton("Save");
		top.add(save);

		ButtonListener listener = new ButtonListener();
		quitButton.addActionListener(listener);
		undo.addActionListener(listener);
		load.addActionListener(listener);
		save.addActionListener(listener);

		board = new JButton[3][3];

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = new JButton("");
				bottom.add(board[row][col]);
				board[row][col].addActionListener(listener);
			}
		}

		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, top);
		this.add(BorderLayout.CENTER, bottom);

		game = new SuperTicTacToeGame(playerint, sizeint);
		iBoard = game.getBoard();

	}

	private void displayBoard() {
		iBoard = game.getBoard();

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (iBoard[row][col] == Cell.O) {
					board[row][col].setIcon(oIcon);
				} else if (iBoard[row][col] == Cell.X) {
					board[row][col].setIcon(xIcon);
				}
			}
		}
	}

	private class ButtonListener implements ActionListener {
		// --------------------------------------------------------------
		// Updates the counter and label when the button is pushed.
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {

			JComponent comp = (JComponent) event.getSource();

			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (board[row][col] == comp) {
						if (!game.select(row, col)) {
							JOptionPane.showMessageDialog(null,
									"Not a valid move.");
						}

					}
				}
			}

			// if (game.getGameStatus() == GameStatus.X_WON) {
			// JOptionPane.showMessageDialog(null,
			// "X won.\nThe game will reset");
			// } else if (game.getGameStatus() == GameStatus.O_WON) {
			// JOptionPane.showMessageDialog(null,
			// "O won.\nThe game will reset");
			//
			// } else if (game.getGameStatus() == GameStatus.CATS) {
			// JOptionPane.showMessageDialog(null,
			// "Both X and O lost.\nThe game will reset");
			//
			// }

			if (quitButton == comp) {
				int option = JOptionPane.showConfirmDialog(null,
						"Are you sure?");
				if (option == 0) {
					System.exit(1);
				}
			}
			if (undo == comp) {
				if (game.undoIsValid()) {
					game.undo();
				} else {
					JOptionPane.showMessageDialog(null, "Cannot undo.");
				}

			}
			if (load == comp) {
			}
			if (save == comp) {
			}

			displayBoard();
		}

	}

}
