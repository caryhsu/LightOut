package lightout.game.solver.matrix;
import lightout.field.Matrix;
import lightout.field.Zn;
import lightout.game.array2d.Array2DPosition;
import lightout.game.delta.NeighberhoodDelta;
import lightout.game.solver.matrix.matrixa.MatrixFactory;
import lombok.Getter;
import lombok.Setter;

public class Solver {
	
	@Getter private Matrix A;
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
		
		this.A = new MatrixFactory(this.delta).newInstance();
	}

//	public void setConstants(int[] constants) {
//		// Check for illegal input
//		if (constants.length != ASize) {
//			throw new IllegalArgumentException(
//					"The constants does not have the correct dimension.");
//		}
//		for (int i = 0; i < constants.length; i++) {
//			A.setConstant(i, (Integer) constants[i]);
//		}
//	}

	public void RowReduce() {
		A.reducedRowEchelonForm();
	}
	
	public boolean hasSolution() {
		for (int curr_Row = ASize - 1; curr_Row >= 0; curr_Row--) { // Go through each row of A starting from the bottom
			for (int i = 0; i < ASize; i++) {
				if (A.getCoefficient(curr_Row, i) != 0) { // If it isn't all zero
					return true;
				}
			}
			if (A.getConstant(curr_Row) != 0) { // If it is all zero with non zero value for b vector
				return false;
			}
		}
		return true;
	}
	
	public int[][] publishSolution() {
		int[][] solution = new int[boardRow][boardCol];
		for (int i = 0; i < ASize; i++) {
			solution[i / boardCol][i % boardCol] = A.getConstant(i);
		}
		return solution;
	}
	
	public void printA() {
		for (int i = 0; i < ASize; i++) {
			for (int j = 0; j < ASize; j++) {
				System.out.print(A.getCoefficient(i, j) + " ");
			}
			System.out.println();
		}
	}
	
	
	public int zeroRowCount() {
		int count = 0;
		for (int curr_Row = ASize - 1; curr_Row >= 0; curr_Row--) { // Go through each row of A starting from the bottom
			for (int i = 0; i < ASize; i++) {
				if (A.getCoefficient(curr_Row, i) != 0) { // If it isn't all zero
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
