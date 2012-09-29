package package1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SuperTicTacTest {

	@Test
	public void test() {
		SuperTicTacToeGame g = new SuperTicTacToeGame(0, 3);
		g.select(0, 0);
		// assertTrue(g.getGameStatus() == GameStatus.IN_PROGRESS);
		g.select(1, 0);
		// assertTrue(g.getGameStatus() == GameStatus.IN_PROGRESS);
		g.select(0, 1);
		// assertTrue(g.getGameStatus() == GameStatus.IN_PROGRESS);
		g.select(2, 0);
		// assertTrue(g.getGameStatus() == GameStatus.IN_PROGRESS);
		g.select(0, 2);
		assertEquals(g.getGameStatus(), GameStatus.O_WON);
		g = new SuperTicTacToeGame(0, 3);
		g.select(0, 0);
		g.select(0, 1);
		g.select(1, 0);
		g.select(0, 2);
		g.select(2, 0);
		assertEquals(g.getGameStatus(), GameStatus.O_WON);
		g = new SuperTicTacToeGame(0, 3);
		g.select(2, 0);
		g.select(0, 1);
		g.select(1, 1);
		g.select(2, 2);
		g.select(0, 2);
		assertEquals(g.getGameStatus(), GameStatus.O_WON);
		g = new SuperTicTacToeGame(0, 3);
		g.select(0, 0);
		g.select(0, 1);
		g.select(1, 1);
		g.select(0, 2);
		g.select(2, 2);
		assertEquals(g.getGameStatus(), GameStatus.O_WON);
	}

}
