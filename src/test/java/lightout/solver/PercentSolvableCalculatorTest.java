package lightout.solver;

import org.junit.Test;

import lightout.game.CrossDelta;

public class PercentSolvableCalculatorTest {

	@Test
	public void calculate() {
		CrossDelta delta = new CrossDelta(5, 5);
		Solver solver = new Solver(5, 5, 2, delta);
		solver.setBVector(new int[5 * 5]);
		solver.RowReduce();
		PercentSolvableCalculator calculator = new PercentSolvableCalculator(solver);
		System.out.println(calculator.calculate());
	}

}
