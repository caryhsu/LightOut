package lightout.game.solver.matrix.matrixa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import lightout.field.Matrix;
import lightout.game.array2d.Array2DGraph;
import lightout.game.delta.NeighberhoodDelta;

public class MatrixAFactoryTest {

	@Test
	public void test1() {
		Array2DGraph g1 = new Array2DGraph(3, 3);
		g1.setModularNumber(2);
		g1.set(new int[][] {
			{1, 0, 1},
			{0, 0, 0},
			{1, 0, 0}
		});
		NeighberhoodDelta delta = new NeighberhoodDelta(g1);
		MatrixAFactory factory = new MatrixAFactory(delta);
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
		assertThat(A.getCoefficients(), is(expectValue));
	}
}
