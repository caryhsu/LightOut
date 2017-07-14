package lightout.solver;
import lightout.Delta;
import lightout.field.Matrix;
import lightout.field.Zn;
import lombok.Getter;
import lombok.Setter;

public class Solver {
	
	private Matrix<Integer> A;
	private int ASize;
	int boardRow;
	int boardCol;
	int state;
	
	@Getter @Setter private IDelta delta;

	public Solver(int boardRow, int boardCol, int state, IDelta delta) {
		this.boardRow = boardRow;
		this.boardCol = boardCol;
		this.delta = delta;
		this.ASize = boardRow * boardCol;
		this.state = state;
		this.A = new Matrix<Integer>(ASize, ASize, new Zn(this.state));
		initMatrixA();
	}

	private void initMatrixA() {
		for (int Arow = 0; Arow < ASize; Arow++) {
			for (int Acol = 0; Acol < ASize; Acol++) {
				int i, j, i_, j_ = 0;
				i = Arow / this.boardCol; // index (i, j) is the tile that you're setting the equation up for
				j = Arow % this.boardCol;
				i_ = Acol / this.boardCol; // index (i_, j_) is the index of where you are pressing
				j_ = Acol % this.boardCol;
				this.A.set(Arow, Acol, delta.getDeltaValue(i, j, i_, j_));
			}
		}
	}

	public void setBVector(int[] bVector) {
		// Check for illegal input
		if (bVector.length != ASize) {
			throw new IllegalArgumentException(
					"The b vector does not have the correct dimension.");
		}
		for (int i = 0; i < bVector.length; i++) {
			A.setBVector(i, 0, (Integer) bVector[i]);
		}
	}

	public void RowReduce() {
		A.reducedRowEchelonForm();
	}
	
	public boolean hasSolution() {
		for (int curr_Row = ASize - 1; curr_Row >= 0; curr_Row--) { // Go through each row of A starting from the bottom
			for (int i = 0; i < ASize; i++) {
				if ((int)A.get(curr_Row, i) != 0) { // If it isn't all zero
					return true;
				}
			}
			if ((int)A.getBVector(curr_Row, 0) != 0) { // If it is all zero with non zero value for b vector
				return false;
			}
		}
		return true;
	}
	
	public int[][] publishSolution() {
		int[][] solution = new int[boardRow][boardCol];
		for (int i = 0; i < ASize; i++) {
			solution[i / boardCol][i % boardCol] = A.getBVector(i, 0);
		}
		return solution;
	}
	
	public void printA() {
		for (int i = 0; i < ASize; i++) {
			for (int j = 0; j < ASize; j++) {
				System.out.print(A.get(i, j) + " ");
			}
			System.out.println();
		}
	}
	
	
	public int zeroRowCount() {
		int count = 0;
		for (int curr_Row = ASize - 1; curr_Row >= 0; curr_Row--) { // Go through each row of A starting from the bottom
			for (int i = 0; i < ASize; i++) {
				if ((int)A.get(curr_Row, i) != 0) { // If it isn't all zero
					return count;
				}
			}
			count++;
		}
		return count;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ASize=" + ASize + "," );
		sb.append("boardRow=" + boardRow + "," );
		sb.append("boardCol=" + boardCol + "," );
		sb.append("state=" + state);
		sb.append("\n");
		sb.append("A=\n" + A.toString());
		return sb.toString();
	}
}
