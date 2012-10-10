/*****************************************************************
 * Contains the main method that runs SuperTicTacToePanel.
 * 
 * @author Russ Johnson
 * @version 10.10.2012
 *****************************************************************/

package package1;

import java.awt.Dimension;

import javax.swing.JFrame;

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

		JFrame frame = new JFrame("Super TicTacToe");

		frame.setPreferredSize(new Dimension(600, 600));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new SuperTicTacToePanel());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
