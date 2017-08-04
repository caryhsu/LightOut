package lightout.math.algebra;

import java.util.Objects;

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
		if (x == null || y == null) return null;
		return x + y;
	}

	@Override
	public Integer multiply(Integer x, Integer y) {
		if (x == null || y == null) return null;
		return x * y;
	}

	@Override
	public Integer negate(Integer x) {
		if (x == null) return null;
		return -x;
	}

	@Override
	public Integer reciprocal(Integer x) {
		if (x == null) return null;
		if (Math.abs(x) == 1) return x;
		return null;
	}

	@Override
	public boolean equals(Integer x, Integer y) {
		return Objects.equals(x, y);
	}

	@Override
	public Integer productEqsY(Integer x, Integer y) { // y/x
		if (x == null || y == null) return null;
		if (x != 0 && y % x == 0) return y/x;
		return null;
	}

}
