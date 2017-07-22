package lightout.game;

import org.junit.runners.Suite;

import lightout.game.array2d.Array2DGraphTest;
import lightout.game.array2d.Array2DPositionTest;
import lightout.game.array2d.NeighberhoodDeltaTest;
import lightout.game.array2d.SelfDeltaTest;
import lightout.game.solver.foreach.GraphListTest;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GameTest.class,
	Array2DGraphTest.class, 
	Array2DPositionTest.class, 
	NeighberhoodDeltaTest.class,
	SelfDeltaTest.class,
	GraphListTest.class
					})
public class TestSuite {
  //nothing
}