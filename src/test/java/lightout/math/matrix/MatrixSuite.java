package lightout.math.matrix;

import org.junit.runners.Suite;

import lightout.game.solver.foreach.GraphListTest;
import lightout.graph.array2d.Array2DGraphTest;
import lightout.graph.array2d.Array2DPositionTest;
import lightout.graph.array2d.NeighberhoodDeltaTest;
import lightout.graph.array2d.SelfDeltaTest;
import lightout.math.matrix.MatrixTest;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GaussianEliminationTest.class,
	MatrixTest.class,
					})
public class MatrixSuite {
  //nothing
}