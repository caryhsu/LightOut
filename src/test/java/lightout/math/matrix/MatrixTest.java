package lightout.math.matrix;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import lightout.math.algebra.Zn;

public class MatrixTest {

	@Test
	public void testCoefficients1() {
		Matrix m = new Matrix(3, 3, new Zn(100));
		
		m.setCoefficientsRow(0, new int[] {1, 2, 3});
		m.setCoefficientsRow(1, new int[] {4, 5, 6});
		m.setCoefficientsRow(2, new int[] {7, 8, 9});
		
		assertThat(m.getCoefficient(0, 0), equalTo(1));
		assertThat(m.getCoefficient(0, 1), equalTo(2));
		assertThat(m.getCoefficient(0, 2), equalTo(3));
		assertThat(m.getCoefficient(1, 0), equalTo(4));
		assertThat(m.getCoefficient(1, 1), equalTo(5));
		assertThat(m.getCoefficient(1, 2), equalTo(6));
		assertThat(m.getCoefficient(2, 0), equalTo(7));
		assertThat(m.getCoefficient(2, 1), equalTo(8));
		assertThat(m.getCoefficient(2, 2), equalTo(9));
	}

	@Test
	public void testCoefficients2() {
		Matrix m = new Matrix(3, 3, new Zn(100));
		
		m.setCoefficients(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		
		assertThat(m.getCoefficient(0, 0), equalTo(1));
		assertThat(m.getCoefficient(0, 1), equalTo(2));
		assertThat(m.getCoefficient(0, 2), equalTo(3));
		assertThat(m.getCoefficient(1, 0), equalTo(4));
		assertThat(m.getCoefficient(1, 1), equalTo(5));
		assertThat(m.getCoefficient(1, 2), equalTo(6));
		assertThat(m.getCoefficient(2, 0), equalTo(7));
		assertThat(m.getCoefficient(2, 1), equalTo(8));
		assertThat(m.getCoefficient(2, 2), equalTo(9));
	}
	
	@Test
	public void testContants() {
		Matrix m = new Matrix(3, 3, new Zn(100));
		m.setContants(new int[] {1, 2, 3});
		
		assertThat(m.getConstant(0), equalTo(1));
		assertThat(m.getConstant(1), equalTo(2));
		assertThat(m.getConstant(2), equalTo(3));
	}
	
	@Test
	public void testSwapRows() {
		Matrix m = new Matrix(3, 3, new Zn(100));
		m.setCoefficients(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		m.setContants(new int[] {1, 2, 3});
		
		m.swapRows(0, 1);
		assertThat(m.getCoefficients(), equalTo(new Integer[][] {
			{4, 5, 6},
			{1, 2, 3},
			{7, 8, 9}
		}));
		assertThat(m.getConstants(), equalTo(new int[] { 2, 1, 3 }));
	}

	@Test
	public void testMultiplyRow() {
		Matrix m = new Matrix(3, 3, new Zn(100));
		m.setCoefficients(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		m.setContants(new int[] {1, 2, 3});
		
		m.multiplyRow(1, 10);
		assertThat(m.getCoefficients(), equalTo(new int[][] {
			{1, 2, 3},
			{40, 50, 60},
			{7, 8, 9}
		}));
		assertThat(m.getConstants(), equalTo(new int[] { 1, 20, 3 }));
	}
}
