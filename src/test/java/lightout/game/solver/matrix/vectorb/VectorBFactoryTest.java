package lightout.game.solver.matrix.vectorb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import lightout.game.array2d.Array2DGraph;
import lightout.game.delta.NeighberhoodDelta;

public class VectorBFactoryTest {

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
		VectorBFactory factory = new VectorBFactoryImpl1(delta);
		int[] B = factory.newInstance();
		
		System.out.println(Arrays.toString(B));
	
		int[] expB = new int[] {1, 2, 1, 2, 2, 2, 1, 2, 2};
		System.out.println(Arrays.toString(expB));
		
		assertThat(B, is(expB));
	}
}
