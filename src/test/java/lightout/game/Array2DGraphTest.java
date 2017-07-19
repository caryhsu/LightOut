package lightout.game;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;

public class Array2DGraphTest {

	@Test
	public void testSize() {
		Array2DGraph g = new Array2DGraph(4, 5);
		assertThat(g.getWidth(), is(4));
		assertThat(g.getHeight(), is(5));
		assertThat(g.getPositions().length, is(20));
		assertThat(g.getVertexes().length, is(20));
	}
	
	@Test
	public void testReset1() {
		Array2DGraph g = new Array2DGraph(4, 5);
		g.reset();
		List<Vertex> vertexes = Arrays.<Vertex>asList(g.getVertexes());
		List<Vertex> vertexes1 = vertexes.stream()
			.filter(v -> v.getValue() == 0)
			.collect(Collectors.toList());
		assertThat(vertexes1.size(), is(4*5));
	}
	
	@Test
	public void testReset2() {
		Array2DGraph g = new Array2DGraph(4, 5);
		g.reset(3);
		List<Vertex> vertexes = Arrays.<Vertex>asList(g.getVertexes());
		List<Vertex> vertexes1 = vertexes.stream()
			.filter(v -> v.getValue() == 3)
			.collect(Collectors.toList());
		assertThat(vertexes1.size(), is(4*5));
	}

	@Test
	public void testGetSet() {
		Array2DGraph g = new Array2DGraph(4, 5);
		g.reset(3);
		Position p1 = new Array2DPosition(1, 1);
		g.set(p1, 10);
		assertThat(g.get(p1), is(10));
		
		List<Vertex> vertexes = Arrays.<Vertex>asList(g.getVertexes());
		List<Vertex> vertexes1 = vertexes.stream()
			.filter(v -> v.getValue() == 3)
			.collect(Collectors.toList());
		assertThat(vertexes1.size(), is(4*5-1));
		List<Vertex> vertexes2 = vertexes.stream()
				.filter(v -> v.getValue() == 10)
				.collect(Collectors.toList());
		assertThat(vertexes2.size(), is(1));
	}

	@Test
	public void testVertexGetSet() {
		Array2DGraph g = new Array2DGraph(4, 5);
		g.reset(3);
		Position p1 = new Array2DPosition(1, 1);
		Vertex v = g.getVertex(p1);
		v.setValue(10);
		assertThat(v.getValue(), is(10));
		assertThat(g.get(p1), is(10));
	}
}
