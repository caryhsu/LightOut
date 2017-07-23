package lightout.game;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.array2d.NeighberhoodDelta;

public class GraphApplyerTest {

	@Test
	public void test1() {
		Graph target = new Array2DGraph(3, 3);
		target.setModularNumber(2);
		target.reset();
		Graph pattern = new Array2DGraph(3, 3);
		pattern.set(new Array2DPosition(0, 0), 1);
		pattern.set(new Array2DPosition(0, 1), 1);
		pattern.set(new Array2DPosition(1, 0), 1);
		Delta delta = new NeighberhoodDelta();
		
		GraphApplyer applyer = new GraphApplyer(target, delta, 2);
		Graph t = applyer.apply(pattern);
		
		assertThat(t.get(new Array2DPosition(0, 0)), is(1));
		assertThat(t.get(new Array2DPosition(0, 2)), is(1));
		assertThat(t.get(new Array2DPosition(2, 0)), is(1));

		long c0 = Arrays.asList(t.getVertexes()).stream()
				.filter(v->v.getValue() == 0)
				.count();
		assertThat(c0, is(6L));
		
		System.out.println(t);
	}
}
