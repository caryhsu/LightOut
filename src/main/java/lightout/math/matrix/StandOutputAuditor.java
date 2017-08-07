package lightout.math.matrix;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StandOutputAuditor<T> implements GaussianEliminationAuditor<T> {

	private Matrix<T> matrix;
	
	@Override
	public void swapRows(int numPivots, int pivotRowIndex) {
		System.out.println(">>swap row: " + numPivots + "," + pivotRowIndex);
		System.out.println(matrix);
	}

	@Override
	public void multiply(int pivotRowIndex, T factor) {
		System.out.println(">>mul row: " + "row" + pivotRowIndex + "x" + factor + " => row" + pivotRowIndex);
		System.out.println(matrix);
	}

	@Override
	public void addRows(int srcRowIndex, int dstRowIndex, T factor) {
		System.out.println(">>add row: row" + srcRowIndex + "x" + factor + " ==> row" + dstRowIndex);
		System.out.println(matrix);
	}

	@Override
	public void divide(int rowIndex, T factor) {
		System.out.println(">>div row: row" + rowIndex + "/" + factor + " ==> row" + rowIndex);
		System.out.println(matrix);
	}

	
}