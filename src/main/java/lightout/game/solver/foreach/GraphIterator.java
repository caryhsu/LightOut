package lightout.game.solver.foreach;

import java.util.Arrays;
import java.util.Iterator;

import lightout.game.Graph;
import lightout.game.array2d.Array2DPosition;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GraphIterator implements Iterator<Graph> {

	private Graph current;
	
	@Override
	public Graph next() {
		Graph next = (Graph) current.clone();

		increase(next);
		this.current = next;
		return next;
	}

	private void increase(Graph g) {
		Array2DPosition position = new Array2DPosition(0, 0);
		x
	}

	@Override
	public boolean hasNext() {
		int n = this.current.getModularNumber();
		return Arrays.asList(this.current.getVertexes()).stream().allMatch(v->v.getValue() == n);
	}
}
