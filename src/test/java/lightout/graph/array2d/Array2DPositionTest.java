package lightout.graph.array2d;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Array2DPositionTest {

	@Test
	public void test1() {
		Array2DPosition p1 = new Array2DPosition(10, 20);
		assertThat(p1.getX(), equalTo(10));
		assertThat(p1.getY(), equalTo(20));
		
		Array2DPosition p2 = new Array2DPosition(10, 20);
		assertThat(p1, equalTo(p2));
		
		Array2DPosition p3 = new Array2DPosition(10, 21);
		assertThat(p1, not(equalTo(p3)));
		
		assertThat(p1.toString().replaceAll(" ", ""), equalTo("[10,20]"));
	}
}
