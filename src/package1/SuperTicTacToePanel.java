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
	private int size;

	private JButton quitButton;
	private JButton undo;
	private JButton load;
	private JButton save;

	private ImageIcon xIcon = new ImageIcon("x.png");
	private ImageIcon oIcon = new ImageIcon("o.png");
	private ImageIcon emptyIcon;

	private SuperTicTacToeGame game;
	private Cell[][] iBoard;

	private JPanel top;
	private JPanel bottom;

	SuperTicTacToePanel() {

		String sizeinput = JOptionPane.showInputDialog(null,
				"Enter in the size of the board: (Between 3 and 9 inclusive.");

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

		top = new JPanel();
		top.setLayout(new GridLayout(1, 4));
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(size, size));

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

		board = new JButton[size][size];

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = new JButton("");
				bottom.add(board[row][col]);
				board[row][col].addActionListener(listener);
			}
		}

		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, top);
		this.add(BorderLayout.CENTER, bottom);

		game = new SuperTicTacToeGame(playerint, size);
		iBoard = game.getBoard();

	}

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
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			JComponent comp = (JComponent) event.getSource();

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

			displayBoard();
		}

	}

}
