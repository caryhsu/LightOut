package lightout.field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void testCoefficients1() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Zn(100));
		
		m.setCoefficientsRow(0, new Integer[] {1, 2, 3});
		m.setCoefficientsRow(1, new Integer[] {4, 5, 6});
		m.setCoefficientsRow(2, new Integer[] {7, 8, 9});
		
		assertThat(m.getCoefficient(0, 0), is(1));
		assertThat(m.getCoefficient(0, 1), is(2));
		assertThat(m.getCoefficient(0, 2), is(3));
		assertThat(m.getCoefficient(1, 0), is(4));
		assertThat(m.getCoefficient(1, 1), is(5));
		assertThat(m.getCoefficient(1, 2), is(6));
		assertThat(m.getCoefficient(2, 0), is(7));
		assertThat(m.getCoefficient(2, 1), is(8));
		assertThat(m.getCoefficient(2, 2), is(9));
	}

	@Test
	public void testCoefficients2() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Zn(100));
		
		m.setCoefficients(new Integer[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		
		assertThat(m.getCoefficient(0, 0), is(1));
		assertThat(m.getCoefficient(0, 1), is(2));
		assertThat(m.getCoefficient(0, 2), is(3));
		assertThat(m.getCoefficient(1, 0), is(4));
		assertThat(m.getCoefficient(1, 1), is(5));
		assertThat(m.getCoefficient(1, 2), is(6));
		assertThat(m.getCoefficient(2, 0), is(7));
		assertThat(m.getCoefficient(2, 1), is(8));
		assertThat(m.getCoefficient(2, 2), is(9));
	}
	
	@Test
	public void testContants() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Zn(100));
		m.setContants(new Integer[] {1, 2, 3});
		
		assertThat(m.getConstant(0), is(1));
		assertThat(m.getConstant(1), is(2));
		assertThat(m.getConstant(2), is(3));
	}
	
	@Test
	public void testSwapRows() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Zn(100));
		m.setCoefficients(new Integer[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		m.setContants(new Integer[] {1, 2, 3});
		
		m.swapRows(0, 1);
		assertThat(m.getCoefficients(), is(new Integer[][] {
			{4, 5, 6},
			{1, 2, 3},
			{7, 8, 9}
		}));
		assertThat(m.getConstants(), is(new Integer[][] {
			{2},
			{1},
			{3}
		}));
		
	}

	@Test
	public void testMultiplyRow() {
		Matrix<Integer> m = new Matrix<Integer>(3, 3, new Zn(100));
		m.setCoefficients(new Integer[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		});
		m.setContants(new Integer[] {1, 2, 3});
		
		m.multiplyRow(1, 10);
		assertThat(m.getCoefficients(), is(new Integer[][] {
			{1, 2, 3},
			{40, 50, 60},
			{7, 8, 9}
		}));
		assertThat(m.getConstants(), is(new Integer[][] {
			{1},
			{20},
			{3}
		}));
		
	}
}
