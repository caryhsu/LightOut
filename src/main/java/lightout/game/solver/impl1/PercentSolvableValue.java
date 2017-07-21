package lightout.game.solver.impl1;

import lombok.Getter;

public class PercentSolvableValue {

	@Getter private int based;
	@Getter private int exponent;
	
	public PercentSolvableValue(int based, int exponent) {
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
