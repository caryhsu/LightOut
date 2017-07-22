package lightout.game.solver.foreach;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lightout.game.Graph;
import lightout.game.Position;
import lightout.game.array2d.Array2DPosition;

public class GraphIterator implements Iterator<Graph> {

	private Graph first;
	private Graph current;
	private boolean hasNext;
	
	public GraphIterator(Graph first) {
		this.first = first;
		this.current = null;
	}
	
	@Override
	public Graph next() {
		if (this.current == null) {
			this.current = this.first;
		}
		else {
			Graph next = (Graph) current.clone();
			increase(next);
			this.current = next;
		}
		return this.current;
	}

	private void increase(Graph g) {
		Position position = new Array2DPosition(0, 0);
		boolean carried = true;
		while(carried) {
			int value = g.get(position) + 1;
			boolean nextCarried = (value >= g.getModularNumber());
			g.set(position, value % g.getModularNumber());
			carried = nextCarried;
			position = next(g, position);
		}
	}

	private Position next(Graph g, Position position) {
		List<Position> positions = Arrays.asList(g.getPositions());
		int index = positions.indexOf(position) + 1;
		this.hasNext = index >= positions.size();
		return this.hasNext ? positions.get(index) : null;
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}
}
