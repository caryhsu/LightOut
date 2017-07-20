package lightout.game.array2d;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Array2DPositionTest {

	@Test
	public void test1() {
		Array2DPosition p1 = new Array2DPosition(10, 20);
		assertThat(p1.getX(), is(10));
		assertThat(p1.getY(), is(20));
		
		Array2DPosition p2 = new Array2DPosition(10, 20);
		assertThat(p1, is(p2));
		
		Array2DPosition p3 = new Array2DPosition(10, 21);
		assertThat(p1, not(is(p3)));
		
		assertThat(p1.toString().replaceAll(" ", ""), is("[10,20]"));
	}
}
