package lightout.field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ZnTest {

	@Test
	public void testBasicOperator() {
		Zn zn = new Zn(10);
		assertThat(zn.zero(), is(0));
		assertThat(zn.one(), is(1));
		
		assertThat(zn.add(3, 8), is(1));
		assertThat(zn.add(3, -8), is(5));
		
		assertThat(zn.multiply(3, 8), is(4));
		
		assertThat(zn.negate(3), is(7));
		assertThat(zn.negate(4), is(6));
		assertThat(zn.negate(7), is(3));
		
		assertThat(zn.reciprocal(3), is(7));
		assertThat(zn.reciprocal(7), is(3));
		
		assertTrue(zn.equals(3, 3));
		assertFalse(zn.equals(4, 13));
		
	}
}
