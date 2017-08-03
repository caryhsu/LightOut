package lightout.field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GaussianEliminationTest {

	@Test
	public void test1() {
		Matrix m = new Matrix(3, 3, new Z());
		m.setCoefficients(new int[][] {
			{ 1, 1, -1},
			{ 0, 1,  3},
			{-1, 0, -2}
		});
		m.setContants(new int[] {9, 3, 2});
		
		System.out.println(m);
		GaussianElimination g = new GaussianElimination(m);
		g.reduceByColumn(0);
		g.reduceByColumn(1);
		System.out.println(m);
		
		assertThat(m.getCoefficients(), is(new int[][] {
			{ 1, 1, -1},
			{ 0, 1,  3},
			{ 0, 1, -3}
		}));
		assertThat(m.getConstants(), is(new int[] {9, 3, 11}));
	}
}
