package lightout.solver.codegen;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import lightout.game.array2d.Array2DGraph;
import lightout.game.delta.NeighberhoodDelta;
import lightout.game.solver.matrix.Solver;
import lombok.AllArgsConstructor;
import lombok.Data;

public class SolverGetZeroRowCountTest {
	
	// Code generator
	public static Map<BoardConfig, Integer> ZERO_ROW_COUNT = new HashMap<>();
	
	@Test
	public void generateGetZeroCount() {
		for(int size = 3; size < 30; size++) {
			for(int state = 2; state < 7; state++) {
				Array2DGraph g = new Array2DGraph(size, size);
				g.setModularNumber(state);
				NeighberhoodDelta delta = new NeighberhoodDelta(g);
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
