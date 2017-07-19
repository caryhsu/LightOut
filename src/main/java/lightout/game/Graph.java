package lightout.game;

import java.util.function.Consumer;
import java.util.function.Predicate;

import lightout.game.array2d.Array2DPosition;

public interface Graph {

	default public void reset() {
		reset(0);
	};
	
	public void reset(int value);

	public boolean checkAllPositions(Predicate<Integer> predicate);

	public int get(Position position);
	
	public void set(Position position, int value);

	default public void increase(Position position) {
		increase(position, 1);
	}
	
	default public void increase(Position position, int value) {
		set(position, get(position) + value);
	}
	
	public int[][] getValues();
	
	public Position[] getPositions();
	
	public Vertex[] getVertexes();

	public Vertex getVertex(Position position);
	
	public void forEachPosition(Consumer<Position> action);
	public void forEachVertex(Consumer<Vertex> action);
	
	public Position[] getNeighberhood(Position position);

	public Position move(Position position, char direction);

	
}
