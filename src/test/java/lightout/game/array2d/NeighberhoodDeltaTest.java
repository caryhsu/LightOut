package lightout.game.array2d;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

import lightout.game.Vertex;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class NeighberhoodDeltaTest {

	@Test
	public void test1() {
		Array2DGraph g = new Array2DGraph(4, 5);
		g.setModularNumber(2);
		g.reset();
		Array2DPosition p = new Array2DPosition(0, 0);
		NeighberhoodDelta delta = new NeighberhoodDelta(g);
		List<Vertex> vertexes = Arrays.asList(g.getVertexes());
		{
			long c0 = vertexes.stream()
				.filter(v->v.getValue() == 0)
				.count();
			assertThat(c0, is(20L));
			long c1 = vertexes.stream()
					.filter(v->v.getValue() == 1)
					.count();
				assertThat(c1, is(0L));
		}
		
		System.out.println(g);
		delta.apply(p);
		{
			long c0 = vertexes.stream()
				.filter(v->v.getValue() == 0)
				.count();
			assertThat(c0, is(17L));
			long c1 = vertexes.stream()
					.filter(v->v.getValue() == 1)
					.count();
				assertThat(c1, is(3L));
		}
		System.out.println(g);
	}
}
