package lightout.graph.array1d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lightout.game.Vertex;
import lightout.game.solver.foreach.GraphList;
import lightout.graph.AbstractGraph;
import lightout.graph.Graph;
import lightout.graph.Position;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@EqualsAndHashCode(callSuper=false, exclude={})
public class Array1DGraph extends AbstractGraph implements Graph  {

	@Getter private int length;
	private int[] values;
	@Getter @Setter private boolean cycled = false;
	
	public Array1DGraph(int length) {
		this.length = length;
		this.values = new int[this.length];
		super.setModularNumber(null);
	}

	public void setSize(int length) {
		this.length = length;
		this.values = new int[this.length];
	}
	
	@Override
	public int getSize() {
		return this.length;
	}
		
	@Override
	public List<Position> getPositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = 0; i < this.length; i++) {
			positions.add(new Array1DPosition(i));
		}
		return positions;
	}
	
	@Override
	public Vertex getVertex(Position position) {
		return new VertexImpl((Array1DPosition) position);
	}
	
	public class VertexImpl implements Vertex {
		@Getter private Array1DPosition position;
		public VertexImpl(Array1DPosition position) {
			this.position = position;
		}
		@Override
		public int getValue() {
			return Array1DGraph.this.get((Array1DPosition) this.position);
		}
		@Override
		public void setValue(int value) {
			Array1DGraph.this.set((Array1DPosition) this.position, value);
		}
		@Override
		public int getPositionIndex() {
			return this.position.getX();
		}
		@Override
		public String toString() {
			return this.getPosition().toString() + ":" + this.getValue();
		}
	}
	
	@Override
	public int get(Position position) {
		int x = ((Array1DPosition) position).getX();
		return get(x);
	}

	public int get(int x) {
		return this.modularNumber == null? this.values[x] : mod(this.values[x]);
	}


	@Override
	public void set(Position position, int value) {
		int x = ((Array1DPosition) position).getX();
		set(x, value);
	}

	public void set(int x, int value) {
		if (this.modularNumber != null) 
			value = mod(value);
		this.values[x] = value;
	}

	public void set(int[] values) {
		for(int x = 0; x < values.length; x++) {
			this.set(x, values[x]);
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
			default:
				throw new RuntimeException("illegal direction:" + direction.toLowerCase());
			}
		}
		return position;
	}
	
	@Override
	public boolean inScope(Position position) {
		int x = ((Array1DPosition) position).getX();
		return inScope(x);
	}
	
	public boolean inScope(int x) {
		return x >= 0 && x < this.length;
	}

	public Position moveLeft(Position position) {
		return moveLeft(position, 1);
	}
		
	public Position moveLeft(Position position, int steps) {
		int x = ((Array1DPosition) position).getX() - steps;
		if (this.cycled) {
			x %= this.length;
			if (x < 0) x += this.length;
		}
		return !inScope(x) ? null : new Array1DPosition(x);
	}

	public Position moveRight(Position position) {
		return moveRight(position, 1);
	}
		
	public Position moveRight(Position position, int steps) {
		int x = ((Array1DPosition) position).getX() + steps;
		if (this.cycled) {
			x %= this.length;
			if (x < 0) x += this.length;
		}
		return !inScope(x) ? null : new Array1DPosition(x);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int x = 0; x < this.length; x++) {
			sb.append(this.get(new Array1DPosition(x))).append(' ');
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public int[] getValues() {
		return this.values;
	}

	@Override
	public int[] getValuesAs1DArray() {
		return this.values;
	}

	@SneakyThrows
	public Graph clone() {
		Array1DGraph other = (Array1DGraph) super.clone();
	    other.values = Arrays.copyOf(this.values, this.values.length);
		return other;
	}
	
	public static GraphList getGraphList(int length, int moduleNumber) {
		Graph g0 = new Array1DGraph(length);
		g0.setModularNumber(moduleNumber);
		return new GraphList(g0);
	}

}