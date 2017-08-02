package lightout.field;
public class Zn implements FieldOperators<Integer> {

	public int n;

	public Zn(int n) {
		this.n = n;
	}

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
		int result = (x + y) % n;
		if (result < 0) result += n;
		return result;
	}

	@Override
	public Integer multiply(Integer x, Integer y) {
		int result = (x * y) % n;
		if (result < 0) result += n;
		return result;
	}

	/**
	 *  加法的相反數
	 */
	@Override
	public Integer negate(Integer x) {
		return multiply(x, n - 1);
	}

	/**
	 *  乘法的相反數
	 */
	@Override
	public Integer reciprocal(Integer x) throws IllegalArgumentException {
		for (int i = n - 1; i >= 0; i--) {
			if ((x * i) % n == 1) {
				return i;
			}
		}
		throw new IllegalArgumentException("Multiplicative inverse does not exist for " + x + " on the field Z{" + n + "}");
	}

	@Override
	public boolean equals(Integer x, Integer y) {
		x = x % n;
		if (x < 0) x += n;
		y = y % n;
		if (y < 0) y += n;
		
		return x == y;
	}

	@Override
	public Integer productEqsY(Integer x, Integer y) {
		for (int z = 0; z < n; z++) {
			if (equals(multiply(x, z), y))
				return z;
		}
		throw new IllegalArgumentException("No solution, z, exists for " + x +" * z = " + y);
	}

}
