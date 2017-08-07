package lightout.math.algebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		this.elements = new ZnElement[n];
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
//				System.out.println(x + "x" + y + "=" + r2);
				if (x != 0) {
					this.divideLookupTable[r2.value][x] = this.of(y);
//					System.out.println(r2 + "/" + x + "=" + y);
				}
				if (y != 0) {
					this.divideLookupTable[r2.value][y] = this.elements[x];
//					System.out.println(r2 + "/" + y + "=" + x);
				}
				if (Objects.equal(r2, one())) {
					this.reciprocalLookupTable[x] = this.elements[y];
					this.reciprocalLookupTable[y] = this.elements[x];
				}
			});
		});
	}
	
	@Override
	public ZnElement zero() {
		return this.elements[0];
	}

	@Override
	public ZnElement one() {
		return this.elements[1];
	}

	private ZnElement add(int x, int y) {
		int z = (x+y) % n;
		if (z < 0) z += n;
		return elements[z];
	}
	
	@Override
	public ZnElement add(ZnElement x, ZnElement y) {
		return this.addLookupTable[x.value][y.value];
	}

	@Override
	public ZnElement multiply(ZnElement x, ZnElement y) {
		if (x == null || y == null) return null;
		int xx = x.value % n;
		if (xx < 0) xx+= n;
		int yy = y.value % n;
		if (yy < 0) yy+= n;
		return this.multiplyLookupTable[x.value][y.value];
	}
	
	private ZnElement multiply(int x, int y) {
		int z = (x * y) % n;
		if (z < 0) z += n;
		return elements[z];
	}
	
	@Override
	public ZnElement subtract(ZnElement x, ZnElement y) {
		if (x == null || y == null) return null;
		int xx = x.value % n;
		if (xx < 0) xx+= n;
		int yy = y.value % n;
		if (yy < 0) yy+= n;
		return this.subtractLookupTable[xx][yy];
	}
	
	@Override
	public ZnElement divide(ZnElement x, ZnElement y) {
		if (x == null || y == null) return null;
		int xx = x.value % n;
		if (xx < 0) xx+= n;
		int yy = y.value % n;
		if (yy < 0) yy+= n;
		return this.divideLookupTable[xx][yy];
	}
	
	@Override
	public ZnElement negate(ZnElement x) {
		if (x == null) return null;
		int xx = x.value % n;
		if (xx < 0) xx += n;
		return this.nativeLookupTable[x.value];
	}

	@Override
	public ZnElement reciprocal(ZnElement x) {
		if (x == null) return null;
		return this.reciprocalLookupTable[x.value];
	}

	@Override
	public int convertToInt(ZnElement value) {
		return value.value;
	}
	
	@Override
	public ZnElement convertFromInt(int value) {
		int index = value % n;
		if (index < 0) index += n;
		return this.elements[index];
	}

	@Override
	public ZnElement[][] convertFromIntArray(int[][] data) {
		ZnElement[][] result = new ZnElement[data.length][];
		for(int i = 0; i < data.length; i++) {
			result[i] = convertFromIntArray(data[i]);
		}
		return result;
	}
	
	@Override 
	public ZnElement[] convertFromIntArray(int[] data) {
		List<ZnElement> elements = new ArrayList<>();
		for(int d : data) {
			elements.add(this.of(d));
		}
		return elements.toArray(new ZnElement[] {});
	}
	
	public ZnElement of(int index) {
		index = index % n;
		if (index < 0) index += n;
		return this.elements[index];
	}
}
