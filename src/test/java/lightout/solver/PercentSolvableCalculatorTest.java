package lightout.solver;

import org.junit.Test;

public class PercentSolvableCalculatorTest {

	@Test
	public void calculate() {
		Solver solver = new Solver(5,5,2);
		solver.setBVector(new int[5 * 5]);
		solver.RowReduce();
		PercentSolvableCalculator calculator = new PercentSolvableCalculator(solver);
		System.out.println(calculator.calculate());
	}

}
