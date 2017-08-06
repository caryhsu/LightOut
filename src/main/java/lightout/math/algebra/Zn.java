package lightout.math.algebra;

import java.util.stream.IntStream;

import com.google.common.base.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of= {"n"})
public class Zn implements FieldOperators<ZnElement> {

	@Getter private int n;
	
	@Getter private ZnElement[]   elements;
	@Getter private ZnElement[][] addLookupTable;
	@Getter private ZnElement[][] subtractLookupTable;
	@Getter private ZnElement[][] multiplyLookupTable;
	@Getter private ZnElement[][] divideLookupTable;
	@Getter private ZnElement[]   nativeLookupTable;
	@Getter private ZnElement[]   reciprocalLookupTable;
	
	public Zn(int n) {
		this.n = n;
		IntStream.range(0, n).forEach(i->this.elements[i]=new ZnElement(this, i));
		init();
	}

	private void init() {
		this.addLookupTable = new ZnElement[n][n];
		this.subtractLookupTable = new ZnElement[n][n];
		this.multiplyLookupTable = new ZnElement[n][n];
		this.divideLookupTable = new ZnElement[n][n];
		this.nativeLookupTable = new ZnElement[n];
		this.reciprocalLookupTable = new ZnElement[n];
		IntStream.range(0, n).forEach(x->{
			IntStream.range(0, n).forEach(y->{
				ZnElement r1 = this.add(x, y);
				this.addLookupTable[x][y] = r1;
				this.subtractLookupTable[r1.value][x] = this.elements[y];
				this.subtractLookupTable[r1.value][y] = this.elements[x];
				if (Objects.equal(r1, zero())) {
					this.nativeLookupTable[x] = this.elements[y];
					this.nativeLookupTable[y] = this.elements[x];
				}
				
				ZnElement r2 = this.multiply(x, y);
				this.multiplyLookupTable[x][y] = r2;
				this.divideLookupTable[r2.value][x] = this.elements[y];
				this.divideLookupTable[r2.value][y] = this.elements[x];
				if (Objects.equal(r2, one())) {
					this.reciprocalLookupTable[x] = this.elements[y];
					this.reciprocalLookupTable[y] = this.elements[x];
				}
			});
		});
	}
	
	public ZnElement zero() {
		return this.elements[0];
	}

	public ZnElement one() {
		return this.elements[1];
	}

	private ZnElement add(int x, int y) {
		int z = (x+y) % n;
		if (z < 0) z += n;
		return elements[z];
	}
	
	public ZnElement add(ZnElement x, ZnElement y) {
		return this.addLookupTable[x.value][y.value];
	}

	public ZnElement multiply(ZnElement x, ZnElement y) {
		if (x == null || y == null) return null;
		return this.multiplyLookupTable[x.value][y.value];
	}
	
	public ZnElement multiply(int x, int y) {
		int z = (x * y) % n;
		if (z < 0) z += n;
		return elements[z];
	}
	
	public ZnElement subtract(ZnElement x, ZnElement y) {
		if (x == null || y == null) return null;
		return this.subtractLookupTable[x.value][y.value];
	}
	
	public ZnElement divide(ZnElement x, ZnElement y) {
		if (x == null || y == null) return null;
		return this.divideLookupTable[x.value][y.value];
	}
	

	public ZnElement negate(ZnElement x) {
		if (x == null) return null;
		return this.nativeLookupTable[x.value];
	}

	public ZnElement reciprocal(ZnElement x) {
		if (x == null) return null;
		return this.reciprocalLookupTable[x.value];
	}

}
