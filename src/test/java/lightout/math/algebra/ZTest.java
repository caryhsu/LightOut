package lightout.math.algebra;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ZTest {

	@Test
	public void testBasicOperator() {
		Z z = new Z();
		assertThat(z.zero(), equalTo(0));
		assertThat(z.one(), equalTo(1));
		
		assertThat(z.add(3, 8), equalTo(11));
		assertThat(z.add(3, -8), equalTo(-5));
		
		assertThat(z.multiply(3, 8), equalTo(24));
		
		assertThat(z.negate(3), equalTo(-3));
		assertThat(z.negate(4), equalTo(-4));
		assertThat(z.negate(7), equalTo(-7));
		
		assertThat(z.reciprocal(3), equalTo(null));
		assertThat(z.reciprocal(7), equalTo(null));
		
//		assertTrue(z.equals(3, 3));
		
	}
}
