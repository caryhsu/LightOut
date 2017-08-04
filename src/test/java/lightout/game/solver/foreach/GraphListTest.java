package lightout.game.solver.foreach;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.Test;

import lightout.game.solver.foreach.GraphList.GraphIterator;
import lightout.graph.Graph;
import lightout.graph.array2d.Array2DGraph;
import lombok.Data;

public class GraphListTest {

	@Test
	public void testIterator() {
		long n = 0;
		Array2DGraph g = new Array2DGraph(2, 3);
		g.setModularNumber(2);
		
		GraphIterator graphIterator = new GraphIterator(g);
		for(Iterator<Graph> it = graphIterator; it.hasNext(); ) {
			Graph g2 = it.next();
			System.out.println(g2);
			n++;
		}
		assertThat(n, equalTo((long) Math.pow(2, 6)));
	}

	@Test
	public void testIterator2() {
		long n = 0;
		Array2DGraph gr = new Array2DGraph(5, 5);
		gr.setModularNumber(2);
		
		Predicate<Graph> endPredicate = (g) -> {
			return ((Array2DGraph) g).getVertexesForRow(0).stream().allMatch(v->v.getValue()==(g.getModularNumber()-1));
		};
		GraphIterator graphIterator = new GraphIterator(gr, endPredicate);
		for(Iterator<Graph> it = graphIterator; it.hasNext(); ) {
			Graph g2 = it.next();
			System.out.println(g2);
			n++;
		}
		assertThat(n, equalTo((long) Math.pow(2, 5)));
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
		assertThat(n.n, equalTo((long) Math.pow(2, 6)));
	}
	
	@Data
	class N {
		private long n = 0;
		public void increase() {this.n++;}
	}
}
