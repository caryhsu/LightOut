package lightout.solver;

public class PercentSolvableCalculator {

	private int state;
	private int zeroRowCount;

	public PercentSolvableCalculator(Solver solver) {
		this.state = solver.state;
		this.zeroRowCount = solver.zeroRowCount();
	}

	public PercentSolvableCalculator(int state, int zeroRowCount) {
		this.state = state;
		this.zeroRowCount = zeroRowCount;
	}
	
	public PercentSolvable calculate() {
		return new PercentSolvable(state, zeroRowCount);
	}

}
