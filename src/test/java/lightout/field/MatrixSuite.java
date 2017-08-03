package lightout.field;

import org.junit.runners.Suite;

import lightout.game.array2d.Array2DGraphTest;
import lightout.game.array2d.Array2DPositionTest;
import lightout.game.array2d.NeighberhoodDeltaTest;
import lightout.game.array2d.SelfDeltaTest;
import lightout.game.solver.foreach.GraphListTest;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MatrixTest.class,
	ZnTest.class,
	ZTest.class,
	ExpressionTest.class
					})
public class MatrixSuite {
  //nothing
}