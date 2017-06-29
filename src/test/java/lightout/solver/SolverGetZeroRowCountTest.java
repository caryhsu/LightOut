package lightout.solver;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SolverGetZeroRowCountTest {

	@Test
	public void zeroRowCount() {
		testGetZeroRowCount(3, 2, 0);
		testGetZeroRowCount(3, 3, 0);
		testGetZeroRowCount(3, 4, 0);
		testGetZeroRowCount(3, 5, 0);
		testGetZeroRowCount(3, 6, 0);
		testGetZeroRowCount(4, 2, 4);
		testGetZeroRowCount(4, 3, 2);
		testGetZeroRowCount(4, 4, 0);
		testGetZeroRowCount(4, 5, 2);
		testGetZeroRowCount(4, 6, 0);
		testGetZeroRowCount(5, 2, 2);
		testGetZeroRowCount(5, 3, 3);
		testGetZeroRowCount(5, 4, 0);
		testGetZeroRowCount(5, 5, 2);
		testGetZeroRowCount(5, 6, 0);
		testGetZeroRowCount(6, 2, 0);
		testGetZeroRowCount(6, 3, 0);
		testGetZeroRowCount(6, 4, 0);
		testGetZeroRowCount(6, 5, 0);
		testGetZeroRowCount(6, 6, 0);
		testGetZeroRowCount(7, 2, 0);
		testGetZeroRowCount(7, 3, 0);
		testGetZeroRowCount(7, 4, 0);
		testGetZeroRowCount(7, 5, 0);
		testGetZeroRowCount(7, 6, 0);
		testGetZeroRowCount(8, 2, 0);
		testGetZeroRowCount(8, 3, 4);
		testGetZeroRowCount(8, 4, 0);
		testGetZeroRowCount(8, 5, 0);
		testGetZeroRowCount(8, 6, 0);
		testGetZeroRowCount(9, 2, 8);
		testGetZeroRowCount(9, 3, 2);
		testGetZeroRowCount(9, 4, 0);
		testGetZeroRowCount(9, 5, 2);
		testGetZeroRowCount(9, 6, 0);
		testGetZeroRowCount(10, 2, 0);
		testGetZeroRowCount(10, 3, 0);
		testGetZeroRowCount(10, 4, 0);
		testGetZeroRowCount(10, 5, 0);
		testGetZeroRowCount(10, 6, 0);
		testGetZeroRowCount(11, 2, 6);
		testGetZeroRowCount(11, 3, 3);
		testGetZeroRowCount(11, 4, 0);
		testGetZeroRowCount(11, 5, 4);
		testGetZeroRowCount(11, 6, 0);
		testGetZeroRowCount(12, 2, 0);
		testGetZeroRowCount(12, 3, 6);
		testGetZeroRowCount(12, 4, 0);
		testGetZeroRowCount(12, 5, 6);
		testGetZeroRowCount(12, 6, 0);
		testGetZeroRowCount(13, 2, 0);
		testGetZeroRowCount(13, 3, 6);
		testGetZeroRowCount(13, 4, 0);
		testGetZeroRowCount(13, 5, 0);
		testGetZeroRowCount(13, 6, 0);
		testGetZeroRowCount(14, 2, 4);
		testGetZeroRowCount(14, 3, 7);
		testGetZeroRowCount(14, 4, 0);
		testGetZeroRowCount(14, 5, 6);
		testGetZeroRowCount(14, 6, 0);
		testGetZeroRowCount(15, 2, 0);
		testGetZeroRowCount(15, 3, 0);
		testGetZeroRowCount(15, 4, 0);
		testGetZeroRowCount(15, 5, 0);
		testGetZeroRowCount(15, 6, 0);
		testGetZeroRowCount(16, 2, 8);
		testGetZeroRowCount(16, 3, 0);
		testGetZeroRowCount(16, 4, 0);
		testGetZeroRowCount(16, 5, 0);
		testGetZeroRowCount(16, 6, 0);
		testGetZeroRowCount(17, 2, 2);
		testGetZeroRowCount(17, 3, 12);
		testGetZeroRowCount(17, 4, 0);
		testGetZeroRowCount(17, 5, 2);
		testGetZeroRowCount(17, 6, 0);
		testGetZeroRowCount(18, 2, 0);
		testGetZeroRowCount(18, 3, 0);
		testGetZeroRowCount(18, 4, 0);
		testGetZeroRowCount(18, 5, 0);
		testGetZeroRowCount(18, 6, 0);
		testGetZeroRowCount(19, 2, 16);
		testGetZeroRowCount(19, 3, 6);
		testGetZeroRowCount(19, 4, 0);
		testGetZeroRowCount(19, 5, 2);
		testGetZeroRowCount(19, 6, 0);
		testGetZeroRowCount(20, 2, 0);
		testGetZeroRowCount(20, 3, 1);
		testGetZeroRowCount(20, 4, 0);
		testGetZeroRowCount(20, 5, 0);
		testGetZeroRowCount(20, 6, 0);
		testGetZeroRowCount(21, 2, 0);
		testGetZeroRowCount(21, 3, 0);
		testGetZeroRowCount(21, 4, 0);
		testGetZeroRowCount(21, 5, 0);
		testGetZeroRowCount(21, 6, 0);
		testGetZeroRowCount(22, 2, 0);
		testGetZeroRowCount(22, 3, 0);
		testGetZeroRowCount(22, 4, 0);
		testGetZeroRowCount(22, 5, 0);
		testGetZeroRowCount(22, 6, 0);
		testGetZeroRowCount(23, 2, 14);
		testGetZeroRowCount(23, 3, 3);
		testGetZeroRowCount(23, 4, 0);
		testGetZeroRowCount(23, 5, 4);
		testGetZeroRowCount(23, 6, 0);
		testGetZeroRowCount(24, 2, 4);
		testGetZeroRowCount(24, 3, 2);
		testGetZeroRowCount(24, 4, 0);
		testGetZeroRowCount(24, 5, 12);
		testGetZeroRowCount(24, 6, 0);
		testGetZeroRowCount(25, 2, 0);
		testGetZeroRowCount(25, 3, 6);
		testGetZeroRowCount(25, 4, 0);
		testGetZeroRowCount(25, 5, 6);
		testGetZeroRowCount(25, 6, 0);
		testGetZeroRowCount(26, 2, 0);
		testGetZeroRowCount(26, 3, 13);
		testGetZeroRowCount(26, 4, 0);
		testGetZeroRowCount(26, 5, 0);
		testGetZeroRowCount(26, 6, 0);
		testGetZeroRowCount(27, 2, 0);
		testGetZeroRowCount(27, 3, 6);
		testGetZeroRowCount(27, 4, 0);
		testGetZeroRowCount(27, 5, 0);
		testGetZeroRowCount(27, 6, 0);
		testGetZeroRowCount(28, 2, 0);
		testGetZeroRowCount(28, 3, 0);
		testGetZeroRowCount(28, 4, 0);
		testGetZeroRowCount(28, 5, 0);
		testGetZeroRowCount(28, 6, 0);
		testGetZeroRowCount(29, 2, 10);
		testGetZeroRowCount(29, 3, 9);
		testGetZeroRowCount(29, 4, 0);
		testGetZeroRowCount(29, 5, 16);
		testGetZeroRowCount(29, 6, 0);
	}
	
	public void testGetZeroRowCount(int size, int state, int assetResult) {
		Solver percentSolvableCalculator = new Solver(size, size, state);
		percentSolvableCalculator.setBVector(new int[size * size]);
		percentSolvableCalculator.RowReduce();
		int zeroRowCount = percentSolvableCalculator.zeroRowCount();
		Assert.assertEquals(zeroRowCount, assetResult);
	}

	@Test
	public void test1() {
		int size = 5; int state = 2;
		Solver percentSolvableCalculator = new Solver(size, size, state);
		percentSolvableCalculator.setBVector(new int[size * size]);
		System.out.println(percentSolvableCalculator);
		percentSolvableCalculator.RowReduce();
		System.out.println(percentSolvableCalculator);

	}

	// Code generator
	public static Map<BoardConfig, Integer> ZERO_ROW_COUNT = new HashMap<>();
	
	@Test
	public void codeGenerator() {
		for(int size = 3; size < 30; size++) {
			for(int state = 2; state < 7; state++) {
				Solver percentSolvableCalculator = new Solver(size, size, state);
				percentSolvableCalculator.setBVector(new int[size * size]);
				percentSolvableCalculator.RowReduce();
				int zeroRowCount = percentSolvableCalculator.zeroRowCount();
				System.out.println("testGetZeroRowCount(" + size + ", " + state + ", " + zeroRowCount + ");");
				ZERO_ROW_COUNT.put(new BoardConfig(size, state), zeroRowCount);
			}
		}
	}
	
	@AllArgsConstructor
	@Data
	public static class BoardConfig {
		private int size;
		private int state;
	}

}
