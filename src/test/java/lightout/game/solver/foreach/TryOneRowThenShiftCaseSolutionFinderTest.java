package lightout.game.solver.foreach;

import org.junit.Test;

import lightout.game.delta.NeighberhoodDelta;
import lightout.game.solver.SolutionFinder;
import lightout.game.solver.onerow.TryOneRowThenShiftCaseSolutionFinder;
import lightout.graph.Graph;
import lightout.graph.array2d.Array2DGraph;

public class TryOneRowThenShiftCaseSolutionFinderTest {

	@Test
	public void test3x3() {
		int width = 3;
		int height = 3;
		
		Array2DGraph initCase = new Array2DGraph(width, height);
		initCase.reset();
		initCase.set(new int[][] {
			{1, 0, 1},
			{0, 0, 0},
			{1, 0, 0}
		});
		
		Graph finalCase = new Array2DGraph(width, height);
		finalCase.reset(0);
		
		SolutionFinder f = new TryOneRowThenShiftCaseSolutionFinder(
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

	@Test
	public void test4x4() {
		int width = 4;
		int height = 4;
		
		Array2DGraph initCase = new Array2DGraph(width, height);
		initCase.reset();
		initCase.set(new int[][] {
			{0, 1, 0, 0},
			{1, 1, 0, 0},
			{0, 1, 1, 1},
			{1, 1, 0, 0}
		});
//		initCase.set(new int[][] {
//			{1, 1, 0, 0},
//			{1, 0, 0, 0},
//			{0, 0, 0, 0},
//			{0, 0, 0, 0}
//		});
		
		Graph finalCase = new Array2DGraph(width, height);
		finalCase.reset(0);
		
		SolutionFinder f = new TryOneRowThenShiftCaseSolutionFinder(
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

	@Test
	public void test5x5() {
		int width = 5;
		int height = 5;
		
		Array2DGraph initCase = new Array2DGraph(width, height);
		initCase.reset();
		initCase.set(new int[][] {
			{0, 0, 1, 0, 0},
			{1, 0, 1, 1, 1},
			{0, 1, 0, 0, 0},
			{1, 1, 0, 0, 0},
			{0, 0, 1, 1, 0}
		});
		
		Graph finalCase = new Array2DGraph(width, height);
		finalCase.reset(0);
		
		SolutionFinder f = new TryOneRowThenShiftCaseSolutionFinder(
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

	@Test
	public void test6x6() {
		int width = 6;
		int height = 6;
		
		Array2DGraph initCase = new Array2DGraph(width, height);
		initCase.reset();
		initCase.set(new int[][] {
			{1, 0, 1, 1, 0, 1},
			{0, 1, 0, 1, 1, 0},
			{0, 1, 1, 1, 0, 0},
			{0, 1, 0, 1, 0, 0},
			{1, 0, 0, 1, 1, 1},
			{0, 0, 1, 1, 0, 0}
		});
		
		Graph finalCase = new Array2DGraph(width, height);
		finalCase.reset(0);
		
		SolutionFinder f = new TryOneRowThenShiftCaseSolutionFinder(
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
