package lightout.game;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import lightout.game.array2d.Array2DPosition;

public class Array2DPositionTest {

	@Test
	public void test1() {
		Array2DPosition p1 = new Array2DPosition(10, 20);
		assertThat(p1.getX(), is(10));
		assertThat(p1.getY(), is(20));
		//assertThat(p1.toString(), is("[10, 20]"));
	}
}
