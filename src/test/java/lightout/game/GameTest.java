package lightout.game;

import org.junit.Test;

import lightout.game.array2d.Array2DPosition;

import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {

	@Test
	public void testSize() {
		Game game = new Game(5, 6, 3);
		assertThat(game.getWidth(), is(5));
		assertThat(game.getHeight(), is(6));
		assertThat(game.getState(), is(3));
		assertThat(game.isEditMode(), is(false));
		game.setEditMode(true);
		assertThat(game.isEditMode(), is(true));
		game.setEditMode(false);
	}
	
	@Test
	public void testDelta() {
		Game game = new Game(5, 6, 3);
		assertNotNull(game.getDelta());
		assertNotNull(game.getDeltaForEditMode());
	}
	
	@Test
	public void testCursor() {
		Game game = new Game(5, 6, 3);
		assertNull(game.getCursor());
		Array2DPosition position = new Array2DPosition(1, 1);
		game.setCursor(position);
		assertNotNull(game.getCursor());
		assertEquals(game.getCursor(), position);
		game.clearCursor();
		assertNull(game.getCursor());
	}
}
