package lightout.field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GaussianEliminationTest {

	@Test
	public void test1() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Z());
		m.setCoefficients(new Integer[][] {
			{ 1, 1, -1},
			{ 0, 1,  3},
			{-1, 0, -2}
		});
		m.setContants(new Integer[] {9, 3, 2});
		
		System.out.println(m);
		new GaussianElimination<Integer>(m).reduceByColumn(0);
		System.out.println(m);
		
		assertThat(m.getCoefficients(), is(new Integer[][] {
			{ 1, 1, -1},
			{ 0, 1,  3},
			{ 0, 1, -3}
		}));
		assertThat(m.getConstants(), is(new Integer[] {9, 3, 11}));
	}
}
