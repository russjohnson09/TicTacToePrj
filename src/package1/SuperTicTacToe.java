package package1;

import javax.swing.JFrame;

public class SuperTicTacToe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Super TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new SuperTicTacToePanel());
		frame.pack();
		frame.setVisible(true);

	}

}
