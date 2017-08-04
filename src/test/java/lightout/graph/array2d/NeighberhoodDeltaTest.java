package lightout.graph.array2d;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import lightout.game.delta.NeighberhoodDelta;
import lightout.graph.Vertex;

public class NeighberhoodDeltaTest {

	@Test
	public void test1() {
		Array2DGraph g = new Array2DGraph(4, 5);
		g.setModularNumber(2);
		g.reset();
		Array2DPosition p = new Array2DPosition(0, 0);
		NeighberhoodDelta delta = new NeighberhoodDelta(g);
		List<Vertex> vertexes = g.getVertexes();
		{
			long c0 = vertexes.stream()
				.filter(v->v.getValue() == 0)
				.count();
			assertThat(c0, equalTo(20L));
			long c1 = vertexes.stream()
					.filter(v->v.getValue() == 1)
					.count();
				assertThat(c1, equalTo(0L));
		}
		
		System.out.println(g);
		delta.apply(p);
		{
			long c0 = vertexes.stream()
				.filter(v->v.getValue() == 0)
				.count();
			assertThat(c0, equalTo(17L));
			long c1 = vertexes.stream()
					.filter(v->v.getValue() == 1)
					.count();
				assertThat(c1, equalTo(3L));
		}
		System.out.println(g);
		delta.apply(p);
		{
			long c0 = vertexes.stream()
				.filter(v->v.getValue() == 0)
				.count();
			assertThat(c0, equalTo(20L));
			long c1 = vertexes.stream()
					.filter(v->v.getValue() == 1)
					.count();
				assertThat(c1, equalTo(0L));
		}
		System.out.println(g);
	}
}
