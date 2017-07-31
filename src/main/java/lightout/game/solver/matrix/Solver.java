package lightout.game.solver.matrix;
import lightout.field.Matrix;
import lightout.field.Zn;
import lightout.game.array2d.Array2DPosition;
import lightout.game.delta.NeighberhoodDelta;
import lombok.Getter;
import lombok.Setter;

public class Solver {
	
	@Getter private Matrix<Integer> A;
	@Getter private int ASize;
	@Getter private int boardRow;
	@Getter private int boardCol;
	@Getter private int state;
	
	@Getter @Setter private NeighberhoodDelta delta;
	
	public Solver(int boardRow, int boardCol, int state, NeighberhoodDelta delta) {
		this.boardRow = boardRow;
		this.boardCol = boardCol;
		this.delta = delta;
		this.ASize = boardRow * boardCol;
		this.state = state;
		
		this.A = new MatrixAFactoryImpl1(this.delta).newInstance();
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
