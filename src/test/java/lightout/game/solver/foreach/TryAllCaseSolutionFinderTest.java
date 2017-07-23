package lightout.game.solver.foreach;

import org.junit.Test;

import lightout.game.Graph;
import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.array2d.NeighberhoodDelta;
import lightout.game.solver.SolutionFinder;

public class TryAllCaseSolutionFinderTest {

	@Test
	public void test1() {
		int width = 3;
		int height = 3;
		
		Graph initCase = new Array2DGraph(width, height);
		initCase.reset();
		initCase.set(new Array2DPosition(0, 0), 1);
		initCase.set(new Array2DPosition(2, 0), 1);
		initCase.set(new Array2DPosition(0, 2), 1);
		
		Graph finalCase = new Array2DGraph(width, height);
		finalCase.reset(0);
		
		SolutionFinder f = new TryAllCaseSolutionFinder(
				Array2DGraph.getGraphList(width, height, 2), 
				new NeighberhoodDelta(),
				initCase,
				finalCase, 2);
		
		Graph[] solutions = f.find();
		
		if (solutions.length == 0) {
			System.out.println("no solution!");
		}
		else {
			System.out.println("solution:" + solutions.length);
			for(Graph s : solutions) {
				System.out.println(s);
			}
		}
	}
}
