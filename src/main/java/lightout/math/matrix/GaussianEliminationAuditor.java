package lightout.math.matrix;

public interface GaussianEliminationAuditor<T> {

	public void swapRows(int numPivots, int pivotRowIndex, Matrix<T> matrix);

	public void multiply(int pivotRowIndex, T factor, Matrix<T> matrix);

	public void addRows(int srcRowIndex, int dstRowIndex, T factor, Matrix<T> matrix);

	public void divide(int rowIndex, T factor, Matrix<T> matrix);
	
}
