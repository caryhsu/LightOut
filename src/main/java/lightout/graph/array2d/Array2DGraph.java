package lightout.graph.array2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import lightout.game.solver.foreach.GraphList;
import lightout.graph.AbstractGraph;
import lightout.graph.Graph;
import lightout.graph.Position;
import lightout.graph.Rectangle;
import lightout.graph.Vertex;
import lightout.math.algebra.ZnElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@EqualsAndHashCode(callSuper=false, exclude={})
public class Array2DGraph extends AbstractGraph implements Graph, Rectangle, Cloneable {

	@Getter private int width;
	@Getter private int height;
	private ZnElement[][] values;
	@Getter @Setter private boolean cycled = false;
	
	public Array2DGraph(int width, int height) {
		this.width = width;
		this.height = height;
		this.values = new ZnElement[this.width][this.height];
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				this.values[i][j] = 
			}
		}
		this.modularNumber = null;
	}

	@Override
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.values = new ZnElement[this.width][this.height];
	}

	@Override
	public int getSize() {
		return this.width * this.height;
	}
	
	public void reset() {
		reset(0);
	}
	
	public List<Position> getPositionsForRow(int rowIndex) {
		List<Position> positions = new ArrayList<>();
		for (int j = 0; j < this.height; j++) {
			positions.add(new Array2DPosition(rowIndex, j));
		}
		return positions;
	}
	
	@Override
	public List<Position> getPositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				positions.add(new Array2DPosition(i, j));
			}
		}
		return positions;
	}

	public List<Vertex<ZnElement>> getVertexesForRow(int rowIndex) {
		final List<Vertex<ZnElement>> vertexes = new ArrayList<>();
		this.getPositionsForRow(rowIndex).forEach(position -> {
			vertexes.add(new VertexImpl((Array2DPosition) position));
		});
		return vertexes;
	}
		
	@Override
	public Vertex<ZnElement> getVertex(Position position) {
		return new VertexImpl((Array2DPosition) position);
	}
	
	public class VertexImpl implements Vertex<ZnElement> {
		@Getter private Array2DPosition position;
		public VertexImpl(Array2DPosition position) {
			this.position = position;
		}
		@Override
		public ZnElement getValue() {
			return Array2DGraph.this.get(this.position);
		}
		@Override
		public void setValue(ZnElement value) {
			Array2DGraph.this.set((Array2DPosition) this.position, value);
		}
		@Override
		public int getPositionIndex() {
			return this.position.getX() * Array2DGraph.this.height + this.position.getY();
		}
		@Override
		public String toString() {
			return this.getPosition().toString() + ":" + this.getValue();
		}
	}
	
	@Override
	public ZnElement get(Position position) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY();
		return get(x, y);
	}

	public ZnElement get(int x, int y) {
		return this.modularNumber == null? this.values[x][y] : mod(this.values[x][y]);
	}

	@Override
	public void set(Position position, int value) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY();
		set(x, y, value);
	}

	public void set(int x, int y, int value) {
		if (this.modularNumber != null) 
			value = mod(value);
		this.values[x][y] = value;
	}

	public void set(int[][] values) {
		for(int x = 0; x < values.length; x++) {
			for(int y = 0; y < values[x].length; y++) {
				this.set(x, y, values[x][y]);
			}
		}
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
		if (this.cycled) {
			x %= this.width;
			if (x < 0) x += this.width;
		}
		int y = ((Array2DPosition) position).getY();
		return !inScope(x, y) ? null : new Array2DPosition(x, y);
	}

	public Position moveRight(Position position) {
		return moveRight(position, 1);
	}
		
	public Position moveRight(Position position, int steps) {
		int x = ((Array2DPosition) position).getX() + steps;
		if (this.cycled) {
			x %= this.width;
			if (x < 0) x += this.width;
		}
		int y = ((Array2DPosition) position).getY();
		return !inScope(x, y) ? null : new Array2DPosition(x, y);
	}

	public Position moveUp(Position position) {
		return moveUp(position, 1);
	}
	
	public Position moveUp(Position position, int steps) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY() - steps;
		if (this.cycled) {
			y %= this.height;
			if (y < 0) y += this.height;
		}
		return !inScope(x, y) ? null : new Array2DPosition(x, y);
	}

	public Position moveDown(Position position) {
		return moveDown(position, 1);
	}
	
	public Position moveDown(Position position, int steps) {
		int x = ((Array2DPosition) position).getX();
		int y = ((Array2DPosition) position).getY() + steps;
		if (this.cycled) {
			y %= this.height;
			if (y < 0) y += this.height;
		}
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
	
	@Override
	public int[] getValuesAs1DArray() {
		int[] result = new int[this.width * this.height];
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				int index = i * this.height + j;
				result[index] = this.values[i][j];
			}
		}
		return result;
	}

	@SneakyThrows
	public Graph clone() {
		Array2DGraph other = (Array2DGraph) super.clone();
		other.values = new int[this.width][this.height];
		for (int i = 0; i < this.values.length; i++)
		     other.values[i] = Arrays.copyOf(this.values[i], this.values[i].length);
		return other;
	}
	
	public static GraphList getGraphList(int width, int height, int moduleNumber) {
		Graph g0 = new Array2DGraph(width, height);
		g0.setModularNumber(moduleNumber);
		return new GraphList(g0);
	}

	public static GraphList getGraphListForFirstRow(int width, int height, int moduleNumber) {
		Array2DGraph g0 = new Array2DGraph(width, height);
		g0.setModularNumber(moduleNumber);
		Predicate<Graph> endPreidcate = (g) -> {
			return ((Array2DGraph) g).getVertexesForRow(0).stream().allMatch(
					v->{return v.getValue() == (moduleNumber-1);});
		};
		return new GraphList(g0, endPreidcate);
	}
}