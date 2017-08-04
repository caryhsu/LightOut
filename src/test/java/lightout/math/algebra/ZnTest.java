package lightout.math.algebra;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

public class ZnTest {

	@Test
	public void testBasicOperator() {
		Zn zn = new Zn(10);
		assertThat(zn.zero(), equalTo(0));
		assertThat(zn.one(), equalTo(1));
		
		assertThat(zn.add(3, 8), equalTo(1));
		assertThat(zn.add(3, -8), equalTo(5));
		
		assertThat(zn.multiply(3, 8), equalTo(4));
		
		assertThat(zn.negate(3), equalTo(7));
		assertThat(zn.negate(4), equalTo(6));
		assertThat(zn.negate(7), equalTo(3));
		
		assertThat(zn.reciprocal(3), equalTo(7));
		assertThat(zn.reciprocal(7), equalTo(3));
		
		assertTrue(zn.equals(3, 3));
		assertFalse(zn.equals(4, 13));
		
	}

	@Test
	public void test2() {
		Zn zn = new Zn(10);
		assertThat(zn.getAddLookupTable(), equalTo(new int[][] {
			{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
			{2, 3, 4, 5, 6, 7, 8, 9, 0, 1},
			{3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
			{4, 5, 6, 7, 8, 9, 0, 1, 2, 3},
			{5, 6, 7, 8, 9, 0, 1, 2, 3, 4},
			{6, 7, 8, 9, 0, 1, 2, 3, 4, 5},
			{7, 8, 9, 0, 1, 2, 3, 4, 5, 6},
			{8, 9, 0, 1, 2, 3, 4, 5, 6, 7},
			{9, 0, 1, 2, 3, 4, 5, 6, 7, 8},
		}));
		outJavaArray(zn.getAddLookupTable());
	}

	private void outJavaArray(Integer[][] table) {
		IntStream.range(0, 10).forEach(i->{
			String text = Arrays.toString(table[i]);
			text = text.replace("[", "{");
			text = text.replace("]", "}");
			System.out.println(text + ",");
		});
	}

}
