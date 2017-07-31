package lightout.game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lightout.game.array1d.Array1DGraph.VertexImpl;

public interface Graph {

	default void reset() {
		reset(0);
	};
	
	default void reset(int value) {
		this.getVertexes().forEach(v -> v.setValue(value));
	}

	int get(Position position);
	
	void set(Position position, int value);
		
	List<Position> getPositions();
	
	default List<Vertex> getVertexes() {
		final List<Vertex> vertexes = new ArrayList<>();
		this.getPositions().forEach(position -> {
			vertexes.add(getVertex(position));
		});
		return vertexes;
	}
	Vertex getVertex(Position position);
	
	Position[] getNeighberhood(Position position);

	Position move(Position position, String direction);

	boolean inScope(Position position);

	Integer getModularNumber();
	void setModularNumber(Integer n);
	
	Graph clone();

	int[] getValuesAs1DArray();

	int getSize();
	
}
