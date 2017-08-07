package lightout.math.matrix;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import lightout.math.algebra.Z;
import lightout.math.algebra.ZnElement;

public class GaussianEliminationTest {

	@Test
	public void test1() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Z());
		m.setCoefficients(new int[][] {
			{ 1, 1, -1},
			{ 0, 1,  3},
			{-1, 0, -2}
		});
		m.setConstants(new int[] {9, 3, 6});
		
		System.out.println(m);
		GaussianElimination<Integer> g = new GaussianElimination<Integer>(m);
		g.setAuditor(new StandOutputAuditor<>(g.getMatrix()));
		g.reduceByColumn1(0);
		g.reduceByColumn1(1);
		g.reduceByColumn1(2);
		
		g.reduceByColumn2(2);
		g.reduceByColumn2(1);
		g.reduceByColumn2(0);
		System.out.println(m);
		
		assertThat(m.getCoefficients(), equalTo(new int[][] {
			{ 1, 0, 0},
			{ 0, 1, 0},
			{ 0, 0, 1}
		}));
		assertThat(m.getConstants(), equalTo(new int[] {-2, 9, -2}));
	}

	// https://www.youtube.com/watch?v=CsTOUbeMPUo
	@Test
	public void test2() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Z());
		m.setCoefficients(new int[][] {
			{ 1,  3,  1},
			{ 1, -2, -1},
			{ 2,  1,  2}
		});
		m.setConstants(new int[] {10, -6, 10});
		
		System.out.println(m);
		GaussianElimination<Integer> g = new GaussianElimination<Integer>(m);
		g.setAuditor(new StandOutputAuditor<>(g.getMatrix()));
		g.reduceByColumn1(0);
		g.reduceByColumn1(1);
		g.reduceByColumn1(2);
		
		assertThat(m.getCoefficients(), equalTo(new int[][] {
			{ 1, 3, 1},
			{ 0, 1, 0},
			{ 0, 0, 1}
		}));
		assertThat(m.getConstants(), equalTo(new int[] {10, 2, 3}));
	}

	// https://www.youtube.com/watch?v=CsTOUbeMPUo
	@Test
	public void test3() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Z());
		m.setCoefficients(new int[][] {
			{ 1,  3,  1},
			{ 1, -2, -1},
			{ 2,  1,  2}
		});
		m.setConstants(new int[] {10, -6, 10});
		
		System.out.println(m);
		GaussianElimination<Integer> g = new GaussianElimination<Integer>(m);
		g.setAuditor(new StandOutputAuditor<>(g.getMatrix()));
		g.reduceByColumn1(0);
		g.reduceByColumn1(1);
		g.reduceByColumn1(2);
		
		System.out.println(m);
		g.reduceByColumn2(2);
		g.reduceByColumn2(1);
		System.out.println(m);
		
		assertThat(m.getCoefficients(), equalTo(new int[][] {
			{ 1, 0, 0},
			{ 0, 1, 0},
			{ 0, 0, 1}
		}));
		assertThat(m.getConstants(), equalTo(new int[] {1, 2, 3}));
	}

	// https://www.youtube.com/watch?v=xCIXkm3-ocQ
	@Test
	public void test4() {
		Matrix<Integer> m = new Matrix<Integer>(4, 4, new Z());
		m.setCoefficients(new int[][] {
			{ 1, -1, -1,  1},
			{ 2,  0,  2,  0},
			{ 0, -1, -2,  0},
			{ 3, -3, -2,  4},
		});
		m.setConstants(new int[] {0, 8, -8, 7});
		
		System.out.println(m);
		GaussianElimination<Integer> g = new GaussianElimination<Integer>(m);
		g.setAuditor(new StandOutputAuditor<>(g.getMatrix()));
		g.reduceByColumn1(0);
		g.reduceByColumn1(1);
		g.reduceByColumn1(2);
		g.reduceByColumn1(3);
		
		System.out.println(m);
		g.reduceByColumn2(3);
		g.reduceByColumn2(2);
		g.reduceByColumn2(1);
		
		System.out.println(m);
		
//		assertThat(m.getCoefficients(), equalTo(new int[][] {
//			{ 1, 0, 0},
//			{ 0, 1, 0},
//			{ 0, 0, 1}
//		}));
//		assertThat(m.getConstants(), equalTo(new int[] {1, 2, 3}));
	}
}
