/*****************************************************************
 * Contains the main method that runs SuperTicTacToePanel.
 * 
 * @author Russ Johnson
 * @version 10.10.2012
 *****************************************************************/

package package1;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SuperTicTacToe {

	/*****************************************************************
	 * The main method starts up the GUI application.
	 * 
	 * @param args
	 *            arguments that main takes
	 * 
	 * @return none
	 *****************************************************************/
	public static void main(String[] args) {

		int size;

		// Asks user for size of board. 3 <= board <= 9.
		String sizeinput = JOptionPane.showInputDialog(null,
				"Enter in the size of the board: (Between 3 and 9 inclusive.");

		// Tries to parse integer provided by user. If not an integer or not in
		// the range 3 to 9.
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

		// Asks who should move first.
		String player = JOptionPane.showInputDialog("Who moves first? X or O");

		// Holds 0 if O moves first and 1 if X moves first.
		int playerint;

		// Tries to check input for X or O. If cancel is pressed or invalid
		// input, defaults to X.
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

		JFrame frame = new JFrame("Super TicTacToe");

		frame.setPreferredSize(new Dimension(600, 600));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new SuperTicTacToePanel(size, playerint));
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
