package lightout.solver.codegen;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import lightout.game.Delta;
import lightout.solver.Solver;
import lombok.AllArgsConstructor;
import lombok.Data;

public class SolverGetZeroRowCountTest {
	
	// Code generator
	public static Map<BoardConfig, Integer> ZERO_ROW_COUNT = new HashMap<>();
	
	@Test
	public void generateGetZeroCount() {
		for(int size = 3; size < 30; size++) {
			for(int state = 2; state < 7; state++) {
				Delta delta = new Delta(size, size);
				Solver percentSolvableCalculator = new Solver(size, size, state, delta);
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
