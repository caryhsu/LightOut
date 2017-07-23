package lightout.game;

import java.util.function.Consumer;

public interface Graph extends Cloneable {

	default void reset() {
		reset(0);
	};
	
	void reset(int value);

	int get(Position position);
	
	void set(Position position, int value);
	
	int[][] getValues();
	
	Position[] getPositions();
	
	Vertex[] getVertexes();
	Vertex getVertex(Position position);
	
	void forEachPosition(Consumer<Position> action);
	void forEachVertex(Consumer<Vertex> action);
	
	Position[] getNeighberhood(Position position);

	Position move(Position position, String direction);

	boolean inScope(Position position);

	Integer getModularNumber();
	void setModularNumber(Integer n);
		
	Graph clone();
}
