package package1;

import java.util.Stack;

public class Driver {

	public static void main(String[] args) {
		SuperTicTacToeGame g = new SuperTicTacToeGame(0, 3);
		g.select(0, 0);
		System.out.println(g.getMoves());
		g.select(1, 1);
		// System.out.println(g.getMoves());
		// Point move = g.getMoves().pop();
		// System.out.println(move.x);
		System.out.println(g.getMoves());
		System.out.println(g.getMoves().lastElement().x);
		Stack m = new Stack();
	}

}
