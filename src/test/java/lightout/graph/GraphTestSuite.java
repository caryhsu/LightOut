package lightout.graph;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import lightout.game.solver.foreach.GraphListTest;
import lightout.graph.array2d.Array2DGraphTest;
import lightout.graph.array2d.Array2DPositionTest;
import lightout.graph.array2d.NeighberhoodDeltaTest;
import lightout.graph.array2d.SelfDeltaTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	Array2DGraphTest.class, 
	Array2DPositionTest.class, 
	NeighberhoodDeltaTest.class,
	SelfDeltaTest.class,
	GraphListTest.class
					})
public class GraphTestSuite {
  //nothing
}