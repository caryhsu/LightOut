package lightout.solver;

import lightout.game.array2d.CrossDelta;

public class PercentSolvableCalculator {

	private Solver solver;
	private int zeroRowCount;
	
	public PercentSolvableCalculator(int size, int state, CrossDelta delta) {
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
		int state = this.solver.state;
		return new PercentSolvableValue(state, zeroRowCount);
	}

	public int getCol() {
		return this.solver.boardCol;
	}
	
	public int getRow() {
		return this.solver.boardRow;
	}
}
