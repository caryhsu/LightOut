package lightout.game.solver.foreach;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Test;

import lightout.game.Graph;
import lightout.game.array2d.Array2DGraph;
import lightout.game.solver.foreach.GraphList.GraphIterator;
import lombok.Data;

public class GraphListTest {

	@Test
	public void testIterator() {
		long n = 0;
		Array2DGraph g = new Array2DGraph(2, 3);
		g.setModularNumber(2);
		
		for(Iterator<Graph> it = new GraphIterator(g); it.hasNext(); ) {
			Graph g2 = it.next();
			System.out.println(g2);
			n++;
		}
		assertThat(n, is((long) Math.pow(2, 6)));
	}

	@Test
	public void testList() {
		Array2DGraph g = new Array2DGraph(2, 3);
		g.setModularNumber(2);
		
		new GraphList(g).forEach(System.out::println);
	}
	
	@Test
	public void testListCount() {
		Array2DGraph g = new Array2DGraph(2, 3);
		g.setModularNumber(2);
		
		N n = new N();
		new GraphList(g).forEach(gg->n.increase());
		assertThat(n.n, is((long) Math.pow(2, 6)));
	}
	
	@Data
	class N {
		private long n = 0;
		public void increase() {this.n++;}
	}
}