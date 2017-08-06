package lightout.math.algebra;

import java.util.Arrays;

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
	public Integer divide(Integer x, Integer y) {
		if (x == null || y == null) return null;
		if (reciprocal(y) != null) {
			return multiply(x, reciprocal(y));
		}
		else {
			if (x % y == 0) {
				return x / y;
			}
			else {
				return null;
			}
		}
	}
	
	@Override
	public Integer[][] convertFromIntArray(int[][] data) {
		Integer[][] result = new Integer[data.length][];
		for(int i = 0; i < data.length; i++) {
			result[i] = convertFromIntArray(data[i]);
		}
		return result;
	}
	
	@Override 
	public Integer[] convertFromIntArray(int[] data) {
		return Arrays.stream( data ).boxed().toArray( Integer[]::new );
	}

	@Override
	public int convertToInt(Integer value) {
		return value.intValue();
	}

	@Override
	public Integer convertFromInt(int value) {
		return Integer.valueOf(value);
	}
	
}
