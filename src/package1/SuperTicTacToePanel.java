package package1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SuperTicTacToePanel extends JPanel {
	private JButton[][] board;
	private Cell[][] iBoard;
	private ImageIcon xIcon;
	private ImageIcon oIcon;
	private ImageIcon emptyIcon;

	JMenuItem gameItem;
	JMenuItem quitItem;

	private SuperTicTacToeGame game;

	//

	private JPanel top;
	private JPanel bottom;

	private JButton quitButton;
	private JButton undo;
	private JButton load;
	private JButton save;

	SuperTicTacToePanel() {

		String player;
		player = JOptionPane
				.showInputDialog("Please enter player to move first. (X or O)");
		if (player.equalsIgnoreCase("X")) {
			int playerint = 1;
		}
		if (player.equalsIgnoreCase("O")) {
			int playerint = 0;
		} else {
			int playerint = 0;

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

		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, top);
		add(BorderLayout.CENTER, bottom);

	}

	private class ButtonListener implements ActionListener {
		// --------------------------------------------------------------
		// Updates the counter and label when the button is pushed.
		// --------------------------------------------------------------
		public void actionPerformed(ActionEvent event) {

		}

	}
}
