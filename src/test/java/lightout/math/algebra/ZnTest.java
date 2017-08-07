package lightout.math.algebra;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ZnTest {

	@Test
	public void testBasicOperator() {
		Zn zn = new Zn(10);
		assertThat(zn.zero(), equalTo(zn.of(0)));
		assertThat(zn.one(), equalTo(zn.of(1)));
		
		assertThat(zn.add(zn.of(3), zn.of(8)), equalTo(zn.of(1)));
		assertThat(zn.add(zn.of(3), zn.of(-8)), equalTo(zn.of(5)));
		
		assertThat(zn.multiply(zn.of(3), zn.of(8)), equalTo(zn.of(4)));
		
		assertThat(zn.negate(zn.of(3)), equalTo(zn.of(7)));
		assertThat(zn.negate(zn.of(4)), equalTo(zn.of(6)));
		assertThat(zn.negate(zn.of(7)), equalTo(zn.of(3)));
		
		assertThat(zn.reciprocal(zn.of(3)), equalTo(zn.of(7)));
		assertThat(zn.reciprocal(zn.of(7)), equalTo(zn.of(3)));		
	}

	@Test
	public void getElements() {
		Zn zn = new Zn(10);
		assertThat(ZnUtils.toIntArray(zn.getElements()), equalTo(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
//		ZnUtils.outJavaArray(zn.getAddLookupTable());
	}
	
	@Test
	public void getAddLookupTable() {
		Zn zn = new Zn(10);
		assertThat(ZnUtils.toIntArray(zn.getAddLookupTable()), equalTo(new int[][] {
			{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
			{1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
			{2, 3, 4, 5, 6, 7, 8, 9, 0, 1},
			{3, 4, 5, 6, 7, 8, 9, 0, 1, 2},
			{4, 5, 6, 7, 8, 9, 0, 1, 2, 3},
			{5, 6, 7, 8, 9, 0, 1, 2, 3, 4},
			{6, 7, 8, 9, 0, 1, 2, 3, 4, 5},
			{7, 8, 9, 0, 1, 2, 3, 4, 5, 6},
			{8, 9, 0, 1, 2, 3, 4, 5, 6, 7},
			{9, 0, 1, 2, 3, 4, 5, 6, 7, 8},
		}));
//		ZnUtils.outJavaArray(zn.getAddLookupTable());
	}

	@Test
	public void getSubtractLookupTable() {
		Zn zn = new Zn(10);
		assertThat(ZnUtils.toIntArray(zn.getSubtractLookupTable()), equalTo(new int[][] {
			{0, 9, 8, 7, 6, 5, 4, 3, 2, 1},
			{1, 0, 9, 8, 7, 6, 5, 4, 3, 2},
			{2, 1, 0, 9, 8, 7, 6, 5, 4, 3},
			{3, 2, 1, 0, 9, 8, 7, 6, 5, 4},
			{4, 3, 2, 1, 0, 9, 8, 7, 6, 5},
			{5, 4, 3, 2, 1, 0, 9, 8, 7, 6},
			{6, 5, 4, 3, 2, 1, 0, 9, 8, 7},
			{7, 6, 5, 4, 3, 2, 1, 0, 9, 8},
			{8, 7, 6, 5, 4, 3, 2, 1, 0, 9},
			{9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
		}));
//		ZnUtils.outJavaArray(zn.getSubtractLookupTable());
	}

	@Test
	public void getMultiplyLookupTable() {
		Zn zn = new Zn(10);
		assertThat(ZnUtils.toIntArray(zn.getMultiplyLookupTable()), equalTo(new int[][] {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
			{0, 2, 4, 6, 8, 0, 2, 4, 6, 8},
			{0, 3, 6, 9, 2, 5, 8, 1, 4, 7},
			{0, 4, 8, 2, 6, 0, 4, 8, 2, 6},
			{0, 5, 0, 5, 0, 5, 0, 5, 0, 5},
			{0, 6, 2, 8, 4, 0, 6, 2, 8, 4},
			{0, 7, 4, 1, 8, 5, 2, 9, 6, 3},
			{0, 8, 6, 4, 2, 0, 8, 6, 4, 2},
			{0, 9, 8, 7, 6, 5, 4, 3, 2, 1},
		}));
//		ZnUtils.outJavaArray(zn.getMultiplyLookupTable());
	}

	@Test
	public void getDivideLookupTable() {
		Zn zn = new Zn(10);
//		assertThat(ZnUtils.toIntArray(zn.getDivideLookupTable()), equalTo(new Integer[][] {
//			{9, 0, 5, 0, 5, 8, 5, 0, 5, 0},
//			{null, 1, null, 7, null, null, null, 3, null, 9},
//			{null, 2, 6, 4, 8, null, 7, 6, 9, 8},
//			{null, 3, null, 1, null, null, null, 9, null, 7},
//			{null, 4, 7, 8, 6, null, 9, 2, 8, 6},
//			{null, 5, null, 5, null, 9, null, 5, null, 5},
//			{null, 6, 8, 2, 9, null, 6, 8, 7, 4},
//			{null, 7, null, 9, null, null, null, 1, null, 3},
//			{null, 8, 9, 6, 7, null, 8, 4, 6, 2},
//			{null, 9, null, 3, null, null, null, 7, null, 1},
//		}));
		ZnUtils.outJavaArray(zn.getDivideLookupTable());
	}


	@Test
	public void getReciprocalLookupTable() {
		Zn zn = new Zn(10);
//		assertThat(ZnUtils.toIntArray(zn.getReciprocalLookupTable()), equalTo(new Integer[][] {
//			{9, 0, 5, 0, 5, 8, 5, 0, 5, 0},
//			{null, 1, null, 7, null, null, null, 3, null, 9},
//			{null, 2, 6, 4, 8, null, 7, 6, 9, 8},
//			{null, 3, null, 1, null, null, null, 9, null, 7},
//			{null, 4, 7, 8, 6, null, 9, 2, 8, 6},
//			{null, 5, null, 5, null, 9, null, 5, null, 5},
//			{null, 6, 8, 2, 9, null, 6, 8, 7, 4},
//			{null, 7, null, 9, null, null, null, 1, null, 3},
//			{null, 8, 9, 6, 7, null, 8, 4, 6, 2},
//			{null, 9, null, 3, null, null, null, 7, null, 1},
//		}));
		System.out.println("getReciprocalLookupTable");
		ZnUtils.outJavaArray(zn.getReciprocalLookupTable());
	}
	
	@Test
	public void getNativeLookupTable() {
		Zn zn = new Zn(10);
//		assertThat(ZnUtils.toIntArray(zn.getNativeLookupTable()), equalTo(new Integer[][] {
//			{9, 0, 5, 0, 5, 8, 5, 0, 5, 0},
//			{null, 1, null, 7, null, null, null, 3, null, 9},
//			{null, 2, 6, 4, 8, null, 7, 6, 9, 8},
//			{null, 3, null, 1, null, null, null, 9, null, 7},
//			{null, 4, 7, 8, 6, null, 9, 2, 8, 6},
//			{null, 5, null, 5, null, 9, null, 5, null, 5},
//			{null, 6, 8, 2, 9, null, 6, 8, 7, 4},
//			{null, 7, null, 9, null, null, null, 1, null, 3},
//			{null, 8, 9, 6, 7, null, 8, 4, 6, 2},
//			{null, 9, null, 3, null, null, null, 7, null, 1},
//		}));
		System.out.println("getNativeLookupTable");
		ZnUtils.outJavaArray(zn.getNativeLookupTable());
	}
}
