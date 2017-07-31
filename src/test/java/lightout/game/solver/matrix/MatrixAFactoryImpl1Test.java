package lightout.game.solver.matrix;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import lightout.field.Matrix;
import lightout.game.array2d.Array2DGraph;
import lightout.game.delta.NeighberhoodDelta;

public class MatrixAFactoryImpl1Test {

	@Test
	public void test1() {
		Array2DGraph g1 = new Array2DGraph(3, 3);
		g1.set(new int[][] {
			{1, 0, 1},
			{0, 0, 0},
			{1, 0, 0}
		});
		NeighberhoodDelta delta = new NeighberhoodDelta(g1);
		MatrixAFactory factory = new MatrixAFactoryImpl1(delta);
		Matrix<Integer> A = factory.newInstance();
	
		int[][] expectValue = new int[][] {
			{1, 1, 0, 1, 0, 0, 0, 0, 0},
			{1, 1, 1, 0, 1, 0, 0, 0, 0},
			{0, 1, 1, 0, 0, 1, 0, 0, 0},
			{1, 0, 0, 1, 1, 0, 1, 0, 0},
			{0, 1, 0, 1, 1, 1, 0, 1, 0},
			{0, 0, 1, 0, 1, 1, 0, 0, 1},
			{0, 0, 0, 1, 0, 0, 1, 1, 0},
			{0, 0, 0, 0, 1, 0, 1, 1, 1},
			{0, 0, 0, 0, 0, 1, 0, 1, 1}
		};
		assertThat(A.getValues(), is(expectValue));
	}
}
