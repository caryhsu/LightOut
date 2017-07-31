package lightout.game.solver.grouping;

import lightout.game.delta.NeighberhoodDelta;
import lightout.game.solver.matrix.Solver;

public class PercentSolvableCalculator {

	private Solver solver;
	private int zeroRowCount;
	
	public PercentSolvableCalculator(int size, int state, NeighberhoodDelta delta) {
		this.solver = new Solver(size, size, state, delta);
		solver.setBVector(new int[size * size]);
		solver.RowReduce();
		
		this.zeroRowCount = solver.zeroRowCount();
	}

	public PercentSolvableCalculator(Solver solver) {
		this.solver = solver;
		this.zeroRowCount = solver.zeroRowCount();
	}
	
	public PercentSolvableValue calculate() {
		int state = this.solver.getState();
		return new PercentSolvableValue(state, zeroRowCount);
	}

	public int getCol() {
		return this.solver.getBoardCol();
	}
	
	public int getRow() {
		return this.solver.getBoardRow();
	}
}
