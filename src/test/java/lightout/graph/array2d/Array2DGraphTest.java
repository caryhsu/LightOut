package lightout.graph.array2d;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Test;

import lightout.graph.Graph;
import lightout.graph.Position;
import lightout.graph.Rectangle;
import lightout.graph.Vertex;
import lightout.graph.array2d.Array2DGraph;
import lightout.graph.array2d.Array2DPosition;

public class Array2DGraphTest {

	@Test
	public void testSize() {
		Rectangle g = new Array2DGraph(4, 5);
		assertThat(g.getWidth(), equalTo(4));
		assertThat(g.getHeight(), equalTo(5));
	}
	
	@Test
	public void testGetPositions() {
		Graph g = new Array2DGraph(4, 5);
		assertThat(g.getPositions().size(), equalTo(20));
		assertThat(g.getVertexes().size(), equalTo(20));
	}
		
	@Test
	public void testReset1() {
		Graph g = new Array2DGraph(4, 5);
		g.reset();
		List<Vertex> vertexes = g.getVertexes();
		List<Vertex> vertexes1 = vertexes.stream()
			.filter(v -> v.getValue() == 0)
			.collect(Collectors.toList());
		assertThat(vertexes1.size(), equalTo(4*5));
	}
	
	@Test
	public void testReset2() {
		Graph g = new Array2DGraph(4, 5);
		g.reset(3);
		List<Vertex> vertexes = g.getVertexes();
		List<Vertex> vertexes1 = vertexes.stream()
			.filter(v -> v.getValue() == 3)
			.collect(Collectors.toList());
		assertThat(vertexes1.size(), equalTo(4*5));
	}

	@Test
	public void testVertexGetPositionIndex() {
		Graph g = new Array2DGraph(4, 5);
		List<Integer> positionIndexes = g.getVertexes().stream()
				.map(Vertex::getPositionIndex)
				.collect(Collectors.toList());
//		System.out.println(positionIndexes);
		assertThat(positionIndexes.size(), equalTo(4*5));
		for(int i = 0; i < positionIndexes.size(); i++) {
			assertThat(positionIndexes.get(i), equalTo(i));
		}
	}
	
	@Test
	public void testGetSet() {
		Graph g = new Array2DGraph(4, 5);
		g.reset(3);
		Position p1 = new Array2DPosition(1, 1);
		g.set(p1, 10);
		assertThat(g.get(p1), equalTo(10));
		
		List<Vertex> vertexes = g.getVertexes();
		List<Vertex> vertexes1 = vertexes.stream()
			.filter(v -> v.getValue() == 3)
			.collect(Collectors.toList());
		assertThat(vertexes1.size(), equalTo(4*5-1));
		List<Vertex> vertexes2 = vertexes.stream()
				.filter(v -> v.getValue() == 10)
				.collect(Collectors.toList());
		assertThat(vertexes2.size(), equalTo(1));
	}

	@Test
	public void testGetSetWithModularNumber() {
		Graph g = new Array2DGraph(4, 5);
		assertNull(g.getModularNumber());
		g.setModularNumber(7);
		assertThat(g.getModularNumber(), equalTo(7));

		g.set(new Array2DPosition(2, 1), 6);
		assertThat(g.get(new Array2DPosition(2, 1)), equalTo(6));

		g.set(new Array2DPosition(2, 3), 8);
		assertThat(g.get(new Array2DPosition(2, 3)), equalTo(1));
	}
	
	@Test
	public void testMovePosition() {
		Array2DGraph g = new Array2DGraph(4, 5);
		Array2DPosition position = new Array2DPosition(1, 2);
		{
			Array2DPosition pos2 = new Array2DPosition(1, 3);
			assertThat(g.moveDown(position), equalTo(pos2));
		}
		{
			Array2DPosition pos2 = new Array2DPosition(1, 4);
			assertThat(g.moveDown(position, 2), equalTo(pos2));
		}
		{
			assertNull(g.moveDown(position, 3));
		}
	}

	@Test
	public void testMovePosition2() {
		Array2DGraph g = new Array2DGraph(4, 5);
		Array2DPosition position = new Array2DPosition(1, 2);
		{
			Position p = position;
			p = g.moveUp(p);
			p = g.moveDown(p);
			assertThat(p, equalTo(position));
		}
		{
			Position p = position;
			p = g.moveLeft(p);
			p = g.moveRight(p);
			assertThat(p, equalTo(position));
		}
		{
			Position p = position;
			p = g.moveUp(p);
			p = g.moveLeft(p);
			p = g.moveDown(p);
			p = g.moveRight(p);
			assertThat(p, equalTo(position));
		}
	}
	
	@Test
	public void testMovePosition3() {
		Array2DGraph g = new Array2DGraph(4, 5);
		Array2DPosition position = new Array2DPosition(1, 2);
		{
			Position p1 = g.move(position, "up");
			Position p2 = g.moveUp(position);
			assertTrue(Objects.equals(p1, p2));
		}
		{
			Position p1 = g.move(position, "down");
			Position p2 = g.moveDown(position);
			assertTrue(Objects.equals(p1, p2));
		}
		{
			Position p1 = g.move(position, "left");
			Position p2 = g.moveLeft(position);
			assertTrue(Objects.equals(p1, p2));
		}
		{
			Position p1 = g.move(position, "right");
			Position p2 = g.moveRight(position);
			assertTrue(Objects.equals(p1, p2));
		}
	}

	@Test
	public void testNeighborhood() {
		Graph g = new Array2DGraph(4, 5);
		{
			Array2DPosition p = new Array2DPosition(0, 0);
			Position[] neighborhoods = g.getNeighberhood(p);
			assertThat(neighborhoods.length, equalTo(2));
		}
		{
			Array2DPosition p = new Array2DPosition(0, 1);
			Position[] neighborhoods = g.getNeighberhood(p);
			assertThat(neighborhoods.length, equalTo(3));
		}
		{
			Array2DPosition p = new Array2DPosition(1, 1);
			Position[] neighborhoods = g.getNeighberhood(p);
			assertThat(neighborhoods.length, equalTo(4));
		}
	}
	
	@Test
	public void testVertexGetSet() {
		Graph g = new Array2DGraph(4, 5);
		g.reset(3);
		Position p1 = new Array2DPosition(1, 1);
		Vertex v = g.getVertex(p1);
		v.setValue(10);
		assertThat(v.getValue(), equalTo(10));
		assertThat(g.get(p1), equalTo(10));
	}
	
//	@Test
//	public void testMod() {
//		Array2DGraph g = new Array2DGraph(4, 5);
//		assertThat(g.mod(-1), equalTo(-1));
//		g.setModularNumber(10);
//		assertThat(g.mod(-1), equalTo(9));
//
//		for(int i = -100; i <=100; i++) {
//			assertTrue(g.mod(i) >= 0);
//			assertTrue(g.mod(i) < 10);
//		}
//	}
//	
//	@Test
//	public void testClone() {
//		Graph g = new Array2DGraph(4, 5);
//		g.reset();
//		Position position = new Array2DPosition(1,1);
//		g.set(position, 10);
//		Graph g2 = g.clone();
//		assertTrue(Objects.equals(g, g2));
//		assertThat(g2.get(position), equalTo(10));
//		
//		g.set(position, 11);
//		assertFalse(Objects.equals(g, g2));
//		assertThat(g.get(position), equalTo(11));
//		assertThat(g2.get(position), equalTo(10));
//	}
//	

	@Test
	public void testGetValuesAs1DArray() {
		Array2DGraph g = new Array2DGraph(3, 2);
		g.set(new int[][] {{1, 2}, {4, 5}, {7, 8}});
//		System.out.println(g);
//		System.out.println(Arrays.toString(g.getValuesAs1DArray()));
		assertThat(g.getValuesAs1DArray(), equalTo(new int[] {1, 2, 4, 5, 7, 8}));
	}
}
