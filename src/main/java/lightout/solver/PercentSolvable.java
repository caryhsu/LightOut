package lightout.solver;

public class PercentSolvable {

	private int based;
	private int exponent;
	
	public PercentSolvable(int based, int exponent) {
		this.based = based;
		this.exponent = exponent;
	}
	
	public double doubleValue() {
		return 1 / Math.pow(this.based, this.exponent);
	}

	@Override
	public String toString() {
		return "1 / (" + this.based + "^" + this.exponent + ")";
	}
	
}
