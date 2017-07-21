package lightout.game.solver.foreach;

import lightout.game.Graph;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GraphIterator {

	private Graph current;
	
	public Graph next() {
		Graph next = (Graph) current.clone();
		
		this.current = next;
		return next;
	}
}
