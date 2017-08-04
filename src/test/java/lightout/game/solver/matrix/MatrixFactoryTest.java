package lightout.game.solver.matrix;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import lightout.game.delta.NeighberhoodDelta;
import lightout.game.solver.matrix.matrixa.MatrixFactory;
import lightout.graph.array2d.Array2DGraph;
import lightout.math.matrix.Matrix;

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
		assertThat(m.getCoefficients(), equalTo(expectValue));
		
		int[] expectContants = new int[] {1, 0, 1, 0, 0, 0, 1, 0, 0};
		assertThat(m.getConstants(), equalTo(expectContants));
	}
}
