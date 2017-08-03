package lightout.game.solver.matrix.matrixa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import lightout.field.Matrix;
import lightout.game.array2d.Array2DGraph;
import lightout.game.delta.NeighberhoodDelta;

public class MatrixFactoryTest {

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
		MatrixFactory factory = new MatrixFactory(delta);
		Matrix m = factory.newInstance();
	
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
		assertThat(m.getCoefficients(), is(expectValue));
		
		int[] expectContants = new int[] {1, 2, 1, 2, 2, 2, 1, 2, 2};
		assertThat(m.getConstants(), is(expectContants));
	}
}
