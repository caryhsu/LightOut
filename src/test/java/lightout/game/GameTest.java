package lightout.game;

import org.junit.Test;

import lightout.game.array2d.Array2DPosition;

import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {

	@Test
	public void testSize() {
		Rectangle game = new RectangleGame(5, 6, 3);
		assertThat(game.getWidth(), is(5));
		assertThat(game.getHeight(), is(6));
		
		game.setSize(7, 8);
		assertThat(game.getWidth(), is(7));
		assertThat(game.getHeight(), is(8));
		
	}

	@Test
	public void testState() {
		Game game = new RectangleGame(5, 6, 3);
		assertThat(game.getState(), is(3));
			
		game.setState(10);
		assertThat(game.getState(), is(10));
	}

	@Test
	public void testEditMode() {
		Game game = new RectangleGame(5, 6, 3);
		assertThat(game.isEditMode(), is(false));
		game.setEditMode(true);
		assertThat(game.isEditMode(), is(true));
		game.setEditMode(false);
		assertThat(game.isEditMode(), is(false));
		
		game.setEditMode(true);
		game.reset();
		assertThat(game.isEditMode(), is(false));
	}
	
	@Test
	public void testDelta() {
		RectangleGame game = new RectangleGame(5, 6, 3);
		assertNotNull(game.getDelta());
		assertNotNull(game.getDeltaForEditMode());
	}
	
	@Test
	public void testCursor() {
		Game game = new RectangleGame(5, 6, 3);
		assertNull(game.getCursor());
		Array2DPosition position = new Array2DPosition(1, 1);
		game.setCursor(position);
		assertNotNull(game.getCursor());
		assertEquals(game.getCursor(), position);
		game.clearCursor();
		assertNull(game.getCursor());
	}

	@Test
	public void testSelect() {
		Game game = new RectangleGame(5, 6, 3);
		Array2DPosition position = new Array2DPosition(1, 1);
		game.select(position);
		System.out.println(game);
	}
}
