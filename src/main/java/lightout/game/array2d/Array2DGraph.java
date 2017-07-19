package lightout.game.array2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.google.common.collect.Iterables;

import lightout.game.Graph;
import lightout.game.Position;
import lightout.game.Vertex;
import lombok.Getter;

public class Array2DGraph implements Graph {

	@Getter private int width;
	@Getter private int height;
	private int[][] values;
	
	public Array2DGraph(int width, int height) {
		this.width = width;
		this.height = height;
		this.values = new int[this.width][this.height];
	}

	public void reset() {
		reset(0);
	}
	
	public void reset(int value) {
		this.forEachVertex(v -> v.setValue(value));
	}
	
	public void forEachPosition(Consumer<Position> action) {
		Objects.requireNonNull(action);
        for (Position position : this.getPositions()) {
            action.accept(position);
        }
	}
	
	public void forEachVertex(Consumer<Vertex> action) {
		Objects.requireNonNull(action);
        for (Vertex vertex : this.getVertexes()) {
            action.accept(vertex);
        }
	}
	
	@Override
	public Position[] getPositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				positions.add(new Array2DPosition(i, j));
			}
		}
		return positions.toArray(new Position[] {});
	}

	@Override
	public Vertex[] getVertexes() {
		final List<Vertex> vertexes = new ArrayList<>();
		forEachPosition(position -> {
			vertexes.add(new VertexImpl(position));
		});
		return vertexes.toArray(new Vertex[] {});
	}

	@Override
	public Vertex getVertex(Position position) {
		return new VertexImpl(position);
	}
	
	public class VertexImpl implements Vertex {
		@Getter private Position position;
		public VertexImpl(Position position) {
			this.position = position;
		}
		@Override
		public int getValue() {
			return Array2DGraph.this.get((Array2DPosition) this.position);
		}
		@Override
		public void setValue(int value) {
			Array2DGraph.this.set((Array2DPosition) this.position, value);
		}
		@Override
		public String toString() {
			return this.getPosition().toString() + ":" + this.getValue();
		}
	}
	
	@Override
	public int get(Position position) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY();
		return this.values[x][y];
	}

	@Override
	public void set(Position position, int value) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY();
		this.values[x][y] = value;
	}

	public boolean checkAllPositions(Predicate<Integer> predicate) {
		for(Position position : this.getPositions()) {
			int value = this.get(position);
			if (!predicate.test(value)) 
				return false;
		}
		return true;
	}
	
	@Override
	public Position[] getNeighberhood(Position position) {  
		char[] directions = new char[] {'L', 'R', 'U', 'D'};
		List<Position> positions = new ArrayList<>();
		for(char direction : directions) {
			Position newPos = move(position, direction);
			if (newPos != null)
				positions.add(newPos);
		}
		return positions.toArray(new Position[] {});
	}

	@Override
	public Position move(Position position, char direction) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY();
		if (direction == '←' || direction == 'L') {
			x = x - 1;
		}
		else if (direction == '→' || direction == 'R') {
			x = x + 1;
		}
		else if (direction == '↑' || direction == 'U') {
			y = y - 1;
		}
		else if (direction == '↓' || direction == 'D') {
			y = y + 1;
		}
		if (x < 0 || x >= this.width || y < 0 || y <= this.height) {
			return null;
		}
		return new Array2DPosition(x, y);
	}
	
	public int[][] getValues() {
		return this.values;
	}

		
}
