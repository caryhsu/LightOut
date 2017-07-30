package lightout.game.solver.foreach;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import lightout.game.Graph;
import lightout.game.Position;
import lightout.game.array2d.Array2DPosition;
import lombok.Getter;
import lombok.Setter;

public class GraphList implements Iterable<Graph> {

	@Getter private Graph first;
	@Getter @Setter private Predicate<Graph> endPreidcate;
	
	public GraphList(Graph first) {
		this(first, g -> {
			return g.getVertexes().stream().allMatch(v->v.getValue()==(g.getModularNumber()-1));
		});
	}
	
	public GraphList(Graph first, Predicate<Graph> endPreidcate) {
		this.first = first;
		this.endPreidcate = endPreidcate;
		
	}
	
	@Override
	public Iterator<Graph> iterator() {
		return new GraphIterator(first, this.endPreidcate);
	}
	
	public static class GraphIterator implements Iterator<Graph> {

		private Graph first;
		private Graph current;
		private boolean hasNext;
		private Predicate<Graph> endPreidcate;
		@Getter @Setter private boolean reverse = false;
		
		public GraphIterator(Graph first) {
			this(first, g -> {
				return g.getVertexes().stream().allMatch(v->v.getValue()==(g.getModularNumber()-1));
			});
		}
		
		public GraphIterator(Graph first, Predicate<Graph> endPreidcate) {
			this.first = first;
			this.current = null;
			this.hasNext = true;
			this.endPreidcate = endPreidcate;
		}
		
		@Override
		public Graph next() {
			if (this.current == null) {
				this.current = this.first;
				this.hasNext = true;
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
			while(carried && position != null) {
				int value = g.get(position) + 1;
				boolean nextCarried = (value >= g.getModularNumber());
				g.set(position, value % g.getModularNumber());
				carried = nextCarried;
				position = next(g, position);
				this.hasNext = !endPreidcate.test(g);
			}
		}

//		public boolean hasNext(Graph g) {
//			return !(g.getVertexes().stream().allMatch(v->v.getValue()==(g.getModularNumber()-1)));
//		}

		private Position next(Graph g, Position position) {
			List<Position> positions = g.getPositions();
			int index = positions.indexOf(position) + 1;
			return index < positions.size() ? positions.get(index) : null;
		}

		@Override
		public boolean hasNext() {
			return this.hasNext;
		}
	}
	
}
