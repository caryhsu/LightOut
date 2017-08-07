package lightout.math.matrix;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lightout.math.algebra.Z;
import lightout.math.algebra.Zn;
import lightout.math.algebra.ZnElement;
import lightout.math.algebra.ZnUtils;

public class MatrixTest {

//	@Test
//	public void testCoefficients1() {
//		Matrix<ZnElement> m = new Matrix<ZnElement>(3, 3, new Zn(100));
//		
//		m.setCoefficients(0, new int[] {1, 2, 3});
//		m.setCoefficientsRow(1, new int[] {4, 5, 6});
//		m.setCoefficientsRow(2, new int[] {7, 8, 9});
//		
//		assertThat(m.getCoefficient(0, 0), equalTo(1));
//		assertThat(m.getCoefficient(0, 1), equalTo(2));
//		assertThat(m.getCoefficient(0, 2), equalTo(3));
//		assertThat(m.getCoefficient(1, 0), equalTo(4));
//		assertThat(m.getCoefficient(1, 1), equalTo(5));
//		assertThat(m.getCoefficient(1, 2), equalTo(6));
//		assertThat(m.getCoefficient(2, 0), equalTo(7));
//		assertThat(m.getCoefficient(2, 1), equalTo(8));
//		assertThat(m.getCoefficient(2, 2), equalTo(9));
//	}

	@Test
	public void testCoefficients2() {
		Matrix<ZnElement> m = new Matrix<ZnElement>(3, 3, new Zn(100));
		
		m.setCoefficients(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		
		assertThat(m.getCoefficientAsInt(0, 0), equalTo(1));
		assertThat(m.getCoefficientAsInt(0, 1), equalTo(2));
		assertThat(m.getCoefficientAsInt(0, 2), equalTo(3));
		assertThat(m.getCoefficientAsInt(1, 0), equalTo(4));
		assertThat(m.getCoefficientAsInt(1, 1), equalTo(5));
		assertThat(m.getCoefficientAsInt(1, 2), equalTo(6));
		assertThat(m.getCoefficientAsInt(2, 0), equalTo(7));
		assertThat(m.getCoefficientAsInt(2, 1), equalTo(8));
		assertThat(m.getCoefficientAsInt(2, 2), equalTo(9));
	}
	
	@Test
	public void testContants() {
		Matrix<ZnElement> m = new Matrix<ZnElement>(3, 3, new Zn(100));
		m.setConstants(new int[] {1, 2, 3});
		
		assertThat(m.getConstantAsInt(0), equalTo(1));
		assertThat(m.getConstantAsInt(1), equalTo(2));
		assertThat(m.getConstantAsInt(2), equalTo(3));
	}
	
	@Test
	public void testSwapRows() {
		Matrix<ZnElement> m = new Matrix<ZnElement>(3, 3, new Zn(100));
		m.setCoefficients(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		m.setConstants(new int[] {1, 2, 3});
		
		m.swapRows(0, 1);
		assertThat(ZnUtils.toIntArray(m.getCoefficients()), equalTo(new Integer[][] {
			{4, 5, 6},
			{1, 2, 3},
			{7, 8, 9}
		}));
		assertThat(ZnUtils.toIntArray(m.getConstants()), equalTo(new int[] { 2, 1, 3 }));
	}

	@Test
	public void testMultiplyRow() {
		Matrix<ZnElement> m = new Matrix<ZnElement>(3, 3, new Zn(100));
		m.setCoefficients(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		m.setConstants(new int[] {1, 2, 3});
		
		m.multiplyRow(1, 10);
		assertThat(ZnUtils.toIntArray(m.getCoefficients()), equalTo(new int[][] {
			{1, 2, 3},
			{40, 50, 60},
			{7, 8, 9}
		}));
		assertThat(ZnUtils.toIntArray(m.getConstants()), equalTo(new int[] { 1, 20, 3 }));
	}

	@Test
	public void areMultipleOf() {
		Matrix<ZnElement> m = new Matrix<ZnElement>(3, 3, new Zn(100));
		m.setCoefficients(new int[][] {
			{1,  3,  1},
			{0, -5, -2},
			{0, -5, 0}
		});
		m.setConstants(new int[] {10, -16, -10});
		
		assertFalse(m.areMultipleOf(0, 5));
		assertFalse(m.areMultipleOf(1, 5));
		assertTrue(m.areMultipleOf(2, 5));
	}
	
	@Test
	public void divideRow() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Z());
		m.setCoefficients(new int[][] {
			{1,  3,  1},
			{0, -5, -2},
			{0, -5, 0}
		});
		m.setConstants(new int[] {10, -16, -10});

		System.out.println(m);
		m.divideRow(2, 5);
		System.out.println(m);
	}

}
