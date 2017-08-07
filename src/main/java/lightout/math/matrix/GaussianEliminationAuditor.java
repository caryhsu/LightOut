package lightout.math.matrix;

public interface GaussianEliminationAuditor<T> {

	public void swapRows(int numPivots, int pivotRowIndex);

	public void multiply(int pivotRowIndex, T factor);

	public void addRows(int srcRowIndex, int dstRowIndex, T factor);

	public void divide(int rowIndex, T factor);
	
}
