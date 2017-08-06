package lightout.math.matrix;

public class StandOutputAuditor<T> implements GaussianEliminationAuditor<T> {

	@Override
	public void swapRows(int numPivots, int pivotRowIndex, Matrix<T> matrix) {
		System.out.println(">>swap row: " + numPivots + "," + pivotRowIndex);
		System.out.println(matrix);
	}

	@Override
	public void multiply(int pivotRowIndex, T factor, Matrix<T> matrix) {
		System.out.println(">>mul row: " + "row" + pivotRowIndex + "x" + factor + " => row" + pivotRowIndex);
		System.out.println(matrix);
	}

	@Override
	public void addRows(int srcRowIndex, int dstRowIndex, T factor, Matrix<T> matrix) {
		System.out.println(">>add row: row" + srcRowIndex + "x" + factor + " ==> row" + dstRowIndex);
		System.out.println(matrix);
	}

	@Override
	public void divide(int rowIndex, T factor, Matrix<T> matrix) {
		System.out.println(">>div row: row" + rowIndex + "/" + factor + " ==> row" + rowIndex);
		System.out.println(matrix);
	}

	
}