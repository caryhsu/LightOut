package lightout.field;

import lombok.Getter;

/**
 * reducedRowEchelonForm
 */
public class GaussianElimination<T> {

	@Getter private Matrix<T> matrix;
	@Getter private FieldOperators<T> op;
	@Getter private int rows;
	@Getter private int columns;
	
	public GaussianElimination(Matrix<T> matrix) {
		this.matrix = matrix;
		this.op = matrix.getOp();
		this.rows = this.matrix.getRows();
		this.columns = this.matrix.getColumns();
	}

	int numPivots = 0;
	
	// reducedRowEchelonForm
	public void reduce() throws IllegalArgumentException {
		this.numPivots = 0;
		for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
			reduceByColumn(columnIndex);
		}
		
		for (int i = rows - 1; i >= 0; i--) {
			int pivotCol = 0;
			while (pivotCol < columns && op.equals(this.matrix.getCoefficient(i, pivotCol), op.zero()))
				pivotCol++;
			if (pivotCol == columns)
				continue;
			for (int j = i - 1; j >= 0; j--) {
				this.matrix.addRows(i, j, op.negate(this.matrix.getCoefficient(j, pivotCol)));
			}
		}
	}

	public void reduceByColumn(int columnIndex) {
		int pivotRowIndex = numPivots; 
		while (pivotRowIndex < rows && op.equals(this.matrix.getCoefficient(pivotRowIndex, columnIndex), op.zero()))
			pivotRowIndex++;
		if (pivotRowIndex == rows)
			return;
		if (pivotRowIndex != numPivots) {
			System.out.println(">>swap row: " + numPivots + "," + pivotRowIndex);
			this.matrix.swapRows(numPivots, pivotRowIndex);
			System.out.println(this.matrix);
			pivotRowIndex = numPivots;
		}
		numPivots++;
		
		Matrix<T>.Row pivotRow = this.matrix.row(pivotRowIndex);
		
		try {
			T factor = op.reciprocal(pivotRow.getCoefficient(columnIndex));
			if (!op.equals(factor, op.one())) {
				System.out.println(">>mult row: " + "row" + pivotRowIndex + "x" + factor + " => row" + pivotRowIndex);
				pivotRow.multiply(factor); // try to turn pivot to one
				System.out.println(this.matrix);
			}
		} catch(IllegalArgumentException e) {
		}
		
		for (int i = pivotRowIndex + 1; i < rows; i++) {
			T factor = op.negate(this.matrix.getCoefficient(i, columnIndex));
			if (!op.equals(factor, op.zero())) {
				System.out.println(">>add row: row" + pivotRowIndex + "x" + factor + " ==> row" + i);
				this.matrix.addRows(pivotRowIndex, i, factor);
				System.out.println(this.matrix);
			}
		}
	}
	
}
