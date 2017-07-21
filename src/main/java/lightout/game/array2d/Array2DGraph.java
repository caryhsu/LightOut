package lightout.game.array2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import lightout.game.Graph;
import lightout.game.Position;
import lightout.game.Rectangle;
import lightout.game.Vertex;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class Array2DGraph implements Graph, Rectangle, Cloneable {

	@Getter private int width;
	@Getter private int height;
	private int[][] values;
	@Getter @Setter private Integer modularNumber = null;
	
	public Array2DGraph(int width, int height) {
		this.width = width;
		this.height = height;
		this.values = new int[this.width][this.height];
		this.modularNumber = null;
	}

	@Override
	public void setSize(int width, int height) {
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
		return this.modularNumber == null? this.values[x][y] : mod(this.values[x][y]);
	}

	public int mod(int n) {
		if (this.modularNumber != null) {
			n %= this.modularNumber;
			if (n < 0) {
				n += this.modularNumber;
			}
		}
		return n;
	}

	@Override
	public void set(Position position, int value) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY();
		if (this.modularNumber != null) 
			value = mod(value);
		this.values[x][y] = value;
	}
	
	@Override
	public Position[] getNeighberhood(Position position) {  
		List<Position> positions = new ArrayList<>();
		{
			Position newPos = moveLeft(position);
			if (newPos != null) positions.add(newPos);
		}
		{
			Position newPos = moveRight(position);
			if (newPos != null) positions.add(newPos);
		}
		{
			Position newPos = moveDown(position);
			if (newPos != null) positions.add(newPos);
		}
		{
			Position newPos = moveUp(position);
			if (newPos != null) positions.add(newPos);
		}
		return positions.toArray(new Position[] {});
	}

	@Override
	public Position move(Position position, String direction) {
		String[] ds = direction.toLowerCase().split(",");
		for(String d : ds) {
			switch(d.trim()) {
			case "":
				break;
			case "left":
				position = moveLeft(position); break;
			case "right":
				position = moveRight(position); break;
			case "up":
				position = moveUp(position); break;
			case "down":
				position = moveDown(position); break;
			default:
				throw new RuntimeException("illegal direction:" + direction.toLowerCase());
			}
		}
		return position;
	}
	
	@Override
	public boolean inScope(Position position) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY();
		return inScope(x, y);
	}
	
	public boolean inScope(int x, int y) {
		return x >= 0 && y >= 0 && x < this.width && y < this.height;
	}

	public Position moveLeft(Position position) {
		return moveLeft(position, 1);
	}
		
	public Position moveLeft(Position position, int steps) {
		int x = ((Array2DPosition) position).getX() - steps;
		int y = ((Array2DPosition) position).getY();
		return !inScope(x, y) ? null : new Array2DPosition(x, y);
	}

	public Position moveRight(Position position) {
		return moveRight(position, 1);
	}
		
	public Position moveRight(Position position, int steps) {
		int x = ((Array2DPosition) position).getX() + steps;
		int y = ((Array2DPosition) position).getY();
		return !inScope(x, y) ? null : new Array2DPosition(x, y);
	}

	public Position moveUp(Position position) {
		return moveUp(position, 1);
	}
	
	public Position moveUp(Position position, int steps) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY() - steps;
		return !inScope(x, y) ? null : new Array2DPosition(x, y);
	}

	public Position moveDown(Position position) {
		return moveDown(position, 1);
	}
	
	public Position moveDown(Position position, int steps) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY() + steps;
		return !inScope(x, y) ? null : new Array2DPosition(x, y);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				sb.append(this.get(new Array2DPosition(x, y))).append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public int[][] getValues() {
		return this.values;
	}

	@SneakyThrows
	public Graph clone() {
		Array2DGraph other = (Array2DGraph) super.clone();
		other.values = new int[this.width][this.height];
		for (int i = 0; i < this.values.length; i++)
		     other.values[i] = Arrays.copyOf(this.values[i], this.values[i].length);
		return other;
	}
	
}