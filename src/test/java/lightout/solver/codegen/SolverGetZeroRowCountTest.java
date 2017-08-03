package lightout.solver.codegen;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Test;

import lightout.game.array2d.Array2DGraph;
import lightout.game.delta.NeighberhoodDelta;
import lightout.game.solver.matrix.Solver;
import lightout.solver.codegen.SolverGetZeroRowCountTest.BoardConfig;
import lombok.AllArgsConstructor;
import lombok.Data;

public class SolverGetZeroRowCountTest {
	
	// Code generator
	public static Map<BoardConfig, Integer> ZERO_ROW_COUNT = new HashMap<>();
	
	@Test
	public void generateGetZeroCount() {
		IntStream.range(3, 29).forEach(size -> {
			IntStream.range(2, 6).forEach(state -> {
				Array2DGraph g = new Array2DGraph(size, size);
				g.setModularNumber(state);
				NeighberhoodDelta delta = new NeighberhoodDelta(g);
				Solver percentSolvableCalculator = new Solver(size, size, state, delta);
//				percentSolvableCalculator.setConstants(new int[size * size]);
				percentSolvableCalculator.RowReduce();
				int zeroRowCount = percentSolvableCalculator.zeroRowCount();
				System.out.println("testGetZeroRowCount(" + size + ", " + state + ", " + zeroRowCount + ");");
				ZERO_ROW_COUNT.put(new BoardConfig(size, state), zeroRowCount);
			});
		});
	}
	
	@AllArgsConstructor
	@Data
	public static class BoardConfig {
		private int size;
		private int state;
	}

}
