package lightout.math.matrix;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import lightout.math.algebra.Z;

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
		
		assertThat(m.getCoefficients(), equalTo(new int[][] {
			{ 1, 1, -1},
			{ 0, 1,  3},
			{ 0, 1, -3}
		}));
		assertThat(m.getConstants(), equalTo(new int[] {9, 3, 11}));
	}
}
