package lightout.math.matrix;

import java.util.Objects;

import lightout.math.algebra.FieldOperators;
import lightout.math.matrix.Matrix.Row;
import lombok.Getter;
import lombok.Setter;

/**
 * reducedRowEchelonForm
 */
public class GaussianElimination<T> {

	@Getter private Matrix<T> matrix;
	@Getter private FieldOperators<T> op;
	@Getter private int rows;
	@Getter private int columns;
	@Getter @Setter private GaussianEliminationAuditor<T> auditor = new StandOutputAuditor<T>();
	
	public GaussianElimination(Matrix<T> matrix) {
		this.matrix = matrix;
		this.op = matrix.getOp();
		this.rows = this.matrix.getRows();
		this.columns = this.matrix.getColumns();
	}

	int numPivots = 0;
	
	// reducedRowEchelonForm
	public void reduce() {
		this.numPivots = 0;
		for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
			reduceByColumn1(columnIndex);
		}
		
		for (int i = rows - 1; i >= 0; i--) {
			reduceByColumn2(i);
		}
	}

	public void reduceByColumn2(int rowIndex) {
		int pivotCol = 0;
		while (pivotCol < columns && Objects.equals(this.matrix.getCoefficient(rowIndex, pivotCol), op.zero()))
			pivotCol++;
		if (pivotCol == columns)
			return;
		for (int j = rowIndex - 1; j >= 0; j--) {
			T coef = this.matrix.getCoefficient(j, pivotCol);
			T factor = op.negate(coef);
			this.matrix.addRows(rowIndex, j, factor);
			if (auditor != null) {
				auditor.addRows(rowIndex, j, factor, this.matrix);
			}
		}
	}

	public void reduceByColumn1(int columnIndex) {
		int pivotRowIndex = numPivots; 
		while (pivotRowIndex < rows && Objects.equals(this.matrix.getCoefficient(pivotRowIndex, columnIndex), op.zero()))
			pivotRowIndex++;
		if (pivotRowIndex == rows)
			return;
		if (pivotRowIndex != numPivots) {
			this.matrix.swapRows(numPivots, pivotRowIndex);
			if (auditor != null) {
				auditor.swapRows(numPivots, pivotRowIndex, this.matrix);
			}
			
			pivotRowIndex = numPivots;
		}
		numPivots++;
		
		// 將 pivot Row 的第一個係數變成 1
		reciprocalPivotRow(columnIndex, pivotRowIndex);
		
		for (int i = pivotRowIndex + 1; i < rows; i++) {
			T coef0 = this.matrix.getCoefficient(i, columnIndex);
			
			T factor = op.negate(coef0);
			if (factor != null) {
				if (!Objects.equals(factor, op.zero())) {
					this.matrix.addRows(pivotRowIndex, i, factor);
					if (auditor != null) {
						auditor.addRows(pivotRowIndex, i, factor, this.matrix);
					}
				}
			}
		}
	}

	private void reciprocalPivotRow(int columnIndex, int pivotRowIndex) {
		for(int rowIndex = pivotRowIndex; rowIndex < this.matrix.getRows(); rowIndex++) {
			Row<T> row = this.matrix.row(rowIndex);
			T coef0 = row.getCoefficient(columnIndex);
			T factor = op.reciprocal(coef0);
			System.out.println(">>>" + rowIndex + ":" + factor);
			if (factor != null) {
				if (pivotRowIndex != rowIndex) {
					this.matrix.swapRows(rowIndex, pivotRowIndex);
					if (auditor != null) {
						auditor.swapRows(rowIndex, pivotRowIndex, this.matrix);
					}
				}
				if (!Objects.equals(factor, op.one())) {
					Row<T> pivotRow = this.matrix.row(pivotRowIndex);
					pivotRow.multiply(factor); // try to turn pivot to one
					if (auditor != null) {
						auditor.multiply(pivotRowIndex, factor, this.matrix);
					}
				}
				break;
			}
			// factor == null
			if (this.matrix.areMultipleOf(rowIndex, coef0)) {
				if (pivotRowIndex != rowIndex) {
					this.matrix.swapRows(rowIndex, pivotRowIndex);
					if (auditor != null) {
						auditor.swapRows(rowIndex, pivotRowIndex, this.matrix);
					}
				}
				Row<T> pivotRow = this.matrix.row(pivotRowIndex);
				pivotRow.divide(coef0); // try to turn pivot to one
				if (auditor != null) {
					auditor.divide(pivotRowIndex, coef0, this.matrix);
				}
				break;
			}
		}
	}
	
}
