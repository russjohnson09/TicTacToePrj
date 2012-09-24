package package1;

import javax.swing.JFrame;

public class SuperTicTacToe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JMenuItem gameItem = new JMenuItem("gameItem");
		// JMenuItem quitItem = new JMenuItem("quitItem");

		frame.getContentPane().add(new SuperTicTacToePanel());
		frame.pack();
		frame.setVisible(true);

	}

}
