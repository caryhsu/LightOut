package lightout.solver;
import org.junit.Assert;
import org.junit.Test;

import lightout.game.array2d.CrossDelta;

public class SolverTest {
	@Test
	public void test1() {
		
		int size = 5; int state = 2;
		CrossDelta delta = new CrossDelta(5, 5);
		Solver solver = new Solver(size, size, state, delta);
		System.out.println(solver);
//		solver.setBVector(new int[size * size]);
//		System.out.println(solver);
//		solver.RowReduce();
//		System.out.println(solver);

	}	
}