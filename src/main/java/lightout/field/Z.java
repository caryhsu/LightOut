package lightout.field;
public class Z implements FieldOperators<Integer> {

	@Override
	public Integer zero() {
		return 0;
	}

	@Override
	public Integer one() {
		return 1;
	}

	@Override
	public Integer add(Integer x, Integer y) {
		return x + y;
	}

	@Override
	public Integer multiply(Integer x, Integer y) {
		return x * y;
	}

	@Override
	public Integer negate(Integer x) {
		return -x;
	}

	@Override
	public Integer reciprocal(Integer x) throws IllegalArgumentException {
		if (Math.abs(x) == 1) return x;
		throw new IllegalArgumentException("Multiplicative inverse does not exist for " + x + " on the field Z");
	}

	@Override
	public boolean equals(Integer x, Integer y) {
		return x == y;
	}

	@Override
	public Integer productEqsY(Integer x, Integer y) { // y/x
		if (x != 0 && y % x == 0) return y/x;
		throw new IllegalArgumentException("No solution, z, exists for " + x +" * z = " + y);
	}

}
