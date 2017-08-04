package lightout.math.algebra;

import java.util.stream.IntStream;

import lombok.Getter;

public class Zn implements FieldOperators<Integer> {

	@Getter private int n;
	
	@Getter private Integer[][] addLookupTable;
	@Getter private Integer[][] subtractLookupTable;
	@Getter private Integer[][] multiplyLookupTable;
	@Getter private Integer[][] divideLookupTable;
	@Getter private Integer[]   nativeLookupTable;
	@Getter private Integer[]   reciprocalLookupTable;
	
	public Zn(int n) {
		this.n = n;
		init();
	}

	private void init() {
		this.addLookupTable = new Integer[n][n];
		this.subtractLookupTable = new Integer[n][n];
		this.multiplyLookupTable = new Integer[n][n];
		this.divideLookupTable = new Integer[n][n];
		this.nativeLookupTable = new Integer[n];
		this.reciprocalLookupTable = new Integer[n];
		IntStream.range(0, n).forEach(x->{
			IntStream.range(0, n).forEach(y->{
				Integer r1 = this._add(x, y);
				this.addLookupTable[x][y] = r1;
				this.subtractLookupTable[r1][x] = y;
				this.subtractLookupTable[r1][y] = x;
				if (equals(r1, zero())) {
					this.nativeLookupTable[x] = y;
					this.nativeLookupTable[y] = x;
				}
				
				Integer r2 = this.multiply(x, y);
				this.multiplyLookupTable[x][y] = r2;
				this.divideLookupTable[r2][x] = y;
				this.divideLookupTable[r2][y] = x;
				if (equals(r2, one())) {
					this.reciprocalLookupTable[x] = y;
					this.reciprocalLookupTable[y] = x;
				}
			});
		});
	}
	
	@Override
	public Integer zero() {
		return 0;
	}

	@Override
	public Integer one() {
		return 1;
	}

	private Integer _add(Integer x, Integer y) {
		return (x + y) % n;
	}
	
	@Override
	public Integer add(Integer x, Integer y) {
//		if (x == null || y == null) return null;
		x %= n;
		if (x < 0) x+= n;
		y %= n;
		if (y < 0) y+= n;
//		System.out.println("add:" + addLookupTable[x][y]);
//		return addLookupTable[x][y];
		int result = (x + y) % n;
		if (result < 0) result += n;
		return result;
	}

	@Override
	public Integer multiply(Integer x, Integer y) {
		if (x == null || y == null) return null;
		int result = (x * y) % n;
		if (result < 0) result += n;
		return result;
	}
	
	@Override
	public Integer subtract(Integer x, Integer y) {
		if (x == null || y == null) return null;
		return add(x, negate(y));
	}
	
//	@Override
//	public integer divide(Integer x, Integer y) {
//		if (x == null || y == null) return null;
//		return multiply(x, reciprocal(y));
//	}
	

	@Override
	public Integer negate(Integer x) {
		if (x == null) return null;
		return multiply(x, n - 1);
	}

	@Override
	public Integer reciprocal(Integer x) {
		if (x == null) return null;
		for (int i = n - 1; i >= 0; i--) {
			if ((x * i) % n == 1) {
				return i;
			}
		}
		return null;
	}

	@Override
	public boolean equals(Integer x, Integer y) {
		int px = x.intValue() % n;
		if (px < 0) px += n;
		int py = y.intValue() % n;
		if (py < 0) py += n;
		
		return px == py;
	}

	// return y/x
	@Override
	public Integer productEqsY(Integer x, Integer y) {
		if (x == null || y == null) return null;
		for (int z = 0; z < n; z++) {
			if (equals(multiply(x, z), y))
				return z;
		}
		return null;
	}

}
