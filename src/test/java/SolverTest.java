import org.junit.Test;

public class SolverTest {

	@Test
	public void test1() {
		int size = 5; int state = 2;
		Solver percentSolvableCalculator = new Solver(size, size, state);
		percentSolvableCalculator.setBVector(new int[size * size]);
		System.out.println(percentSolvableCalculator);
		percentSolvableCalculator.RowReduce();
		System.out.println(percentSolvableCalculator);

	}
}
