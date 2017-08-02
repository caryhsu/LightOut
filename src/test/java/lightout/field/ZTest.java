package lightout.field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ZTest {

	@Test
	public void testBasicOperator() {
		Z z = new Z();
		assertThat(z.zero(), is(0));
		assertThat(z.one(), is(1));
		
		assertThat(z.add(3, 8), is(11));
		assertThat(z.add(3, -8), is(-5));
		
		assertThat(z.multiply(3, 8), is(24));
		
		assertThat(z.negate(3), is(-3));
		assertThat(z.negate(4), is(-4));
		assertThat(z.negate(7), is(-7));
		
//		assertThat(z.reciprocal(3), is(7));
//		assertThat(z.reciprocal(7), is(3));
		
		assertTrue(z.equals(3, 3));
		
	}
}
