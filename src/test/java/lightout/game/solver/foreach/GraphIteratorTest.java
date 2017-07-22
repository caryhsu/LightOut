package lightout.game.solver.foreach;

import java.util.Iterator;

import org.junit.Test;

import lightout.game.array2d.Array2DGraph;
import lightout.game.Graph;

public class GraphIteratorTest {

	@Test
	public void test1() {
		Array2DGraph g = new Array2DGraph(4, 5);
		
		for(Iterator<Graph> it = new GraphIterator(g); it.hasNext(); ) {
			it.next();
		}
	}
}
