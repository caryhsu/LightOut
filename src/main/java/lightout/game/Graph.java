package lightout.game;

import java.util.function.Consumer;
import java.util.function.Predicate;

import lightout.game.array2d.Array2DPosition;

public interface Graph {

	default void reset() {
		reset(0);
	};
	
	void reset(int value);

	boolean checkAllPositions(Predicate<Integer> predicate);

	int get(Position position);
	
	void set(Position position, int value);

	default void increase(Position position) {
		increase(position, 1);
	}
	
	default void increase(Position position, int value) {
		set(position, get(position) + value);
	}
	
	int[][] getValues();
	
	Position[] getPositions();
	
	Vertex[] getVertexes();
	Vertex getVertex(Position position);
	
	void forEachPosition(Consumer<Position> action);
	void forEachVertex(Consumer<Vertex> action);
	
	Position[] getNeighberhood(Position position);

	Position move(Position position, String direction);

	boolean inScope(Position position);
	
}
