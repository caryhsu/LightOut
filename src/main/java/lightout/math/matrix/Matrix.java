package lightout.math.matrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import lightout.math.algebra.Expression;
import lightout.math.algebra.FieldOperators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public final class Matrix<T> implements Cloneable {
	
	@Getter private int rows;
	@Getter private int columns;
	@Getter private T[][] coefficients;
	@Getter private T[] constants; 

	@Getter private FieldOperators<T> op;
	
	public Matrix(int rows, int columns, FieldOperators<T> op) {
		this.rows = rows;
		this.columns = columns;
		this.op = op;
//		this.coefficients = new int[rows][columns];
//		this.constants = new int[rows][1];
	}
		
	public T getCoefficient(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= this.rows || columnIndex < 0 || columnIndex >= this.columns)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		return this.coefficients[rowIndex][columnIndex];
	}
	
	public T getConstant(int rowIndex) {
		if (rowIndex < 0 || rowIndex >= this.rows)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		return this.constants[rowIndex];
	}
	
	public void setCoefficient(int rowIndex, int columnIndex, T value) {
		if (rowIndex < 0 || rowIndex >= this.rows || columnIndex < 0 || columnIndex >= this.columns)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.coefficients[rowIndex][columnIndex] = value;
	}
	
	public void setCoefficientsRow(int rowIndex, T[] coefficients) {
		if (rowIndex < 0 || rowIndex >= this.rows || coefficients.length != this.columns)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.coefficients[rowIndex] = coefficients;
	}

	public void setCoefficients(T[][] coefficients) {
		if (coefficients.length != this.rows || coefficients[0].length != this.columns)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.coefficients = coefficients;
	}

	public void setConstant(int rowIndex, T constant) {
		if (rowIndex < 0 || rowIndex >= this.rows)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.constants[rowIndex] = constant;
	}
	
	public void setConstants(T[] constants) {
		if (constants.length != this.rows)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.constants = constants;
	}

	public Matrix<T> clone() {
		int rows = this.rows;
		int columns = this.columns;
		Matrix<T> result = new Matrix<T>(rows, columns, op);
		for (int i = 0; i < coefficients.length; i++)
			System.arraycopy(coefficients[i], 0, result.coefficients[i], 0, columns);
		return result;
	}
	
	/**
	 * 將兩列互調
	 * @param row0
	 * @param row1
	 */
	public void swapRows(Row<T> row0, Row<T> row1) {
		swapRows(row0, row1);
	}
	
	/**
	 * 將兩列互調
	 * @param rowIndex0
	 * @param rowIndex1
	 */
	public void swapRows(int rowIndex0, int rowIndex1) {
		if (rowIndex0 < 0 || rowIndex0 >= this.rows || rowIndex1 < 0 || rowIndex1 >= this.rows)
			throw new IndexOutOfBoundsException("Row index out of bounds");
		T[] temp = coefficients[rowIndex0];
		coefficients[rowIndex0] = coefficients[rowIndex1];
		coefficients[rowIndex1] = temp;
		// change identity matrix
		T temp2 = constants[rowIndex0];
		constants[rowIndex0] = constants[rowIndex1];
		constants[rowIndex1] = temp2;
	}
	
	/**
	 * 將某列乘以 factor
	 * @param rowIndex
	 * @param factor
	 */
	public void multiplyRow(Row<T> row, T factor) {
		multiplyRow(row.rowIndex, factor);
	}
	
	/**
	 * 將某列乘以 factor
	 * @param rowIndex
	 * @param factor
	 */
	public void multiplyRow(int rowIndex, T factor) {
		this.updateValues(rowIndex, cell -> {return op.multiply(cell.getValue(), factor);});
	}
	
	public void _multiplyRow(int rowIndex, T factor) {
		Row<T> row = row(rowIndex);
		for (int j = 0, cols = this.columns; j < cols; j++) {
			T newCoefficient = op.multiply(row.getCoefficient(j), factor);
			row.setCoefficient(j, newCoefficient);
		}
		row.setConstant(op.multiply(row.getConstant(), factor));
	}
	
	public void updateValues(int rowIndex, Function<Cell<T>, T> function) {
		Row<T> row = row(rowIndex);
		row.getCells().forEach(cell-> {
			T newCoef = function.apply(cell);
			cell.setValue(newCoef);
		});
	}
	
	public void updateValues(int srcRowIndex, int dstRowIndex, Function<PairCells<T>, T> function) {
		Objects.requireNonNull(function);
		Row<T> srcRow = row(srcRowIndex);
		Row<T> dstRow = row(dstRowIndex);
		
		if (dstRow == null) {
			throw new NullPointerException();
		}
		if (dstRow.getCells() == null) {
			throw new NullPointerException();
		}
		dstRow.getCells().forEach(dstCell-> {
			Cell<T> srcCell = srcRow.cell(dstCell.getCellIndex());
			PairCells<T> pair = new PairCells<T>(srcCell, dstCell);
			T newCoef = op.zero();
			try {
				newCoef = function.apply(pair);
			}
			catch(NullPointerException ex) {
				int x = 0;
				x++;
				throw ex;
			}
			dstCell.setValue(newCoef);
		});
	}

	public void addRows(int srcRowIndex, int dstRowIndex, T factor) {
		updateValues(srcRowIndex, dstRowIndex, pair-> {
			Cell<T> srcCell = pair.srcCell;
			Cell<T> dstCell = pair.dstCell;
			T result = new Expression<T>()
					.setOp(this.op)
					.setInitial(srcCell.getValue())
					.multiply(factor)
					.add(dstCell.getValue())
					.result();
			return result;
		});
	}
	
//	public void _addRows(int srcRowIndex, int dstRowIndex, T factor) {
//		for (int j = 0, cols = this.columns; j < cols; j++) {
//			setCoefficient(dstRowIndex, j, 
//					op.add(getCoefficient(dstRowIndex, j), op.multiply(getCoefficient(srcRowIndex, j), factor)));
//		}
//		setConstant(dstRowIndex, op.add(getConstant(dstRowIndex), 
//				op.multiply(getConstant(srcRowIndex), factor)));
//	}
	
	/**
	 * 矩陣相乘
	 * @param other
	 * @return
	 */
	public Matrix<T> multiply(Matrix<T> other) {
		if (this.columns != other.rows)
			throw new IllegalArgumentException("Incompatible matrix sizes for multiplication");
		int rows = this.rows;
		int columns = other.columns;
		int cells = this.columns;
		Matrix<T> result = new Matrix<T>(rows, columns, op);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				T sum = op.zero();
				for (int k = 0; k < cells; k++) {
					T mul = op.multiply(getCoefficient(i, k), (T) other.getCoefficient(k, j));
					sum = op.add(mul, sum);
				}
				result.setCoefficient(i, j, sum);
			}
		}
		return result;
	}
	
	public void reducedRowEchelonForm() {
		new GaussianElimination(this).reduce();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		String lineSeparator = System.lineSeparator();
		for (int i = 0; i < coefficients.length; i++) {
			T[] row1 = coefficients[i];
			T rowB = this.constants == null? null : this.constants[i];
		    sb.append(Arrays.toString(row1))
		      .append(rowB == null? "" : "[" + rowB + "]")
		      .append(lineSeparator);
		}
		return sb.toString();
	}
	
	public Row<T> row(int rowIndex) {
		return new Row<T>(this, rowIndex);
	}
	
	public static class Row<T> {
		@Getter Matrix<T> matrix;
		@Getter private int rowIndex;
		
		private Row(Matrix<T> matrix, int rowIndex) {
			this.matrix = matrix;
			this.rowIndex = rowIndex;
		}
		
		public T getCoefficient(int columnIndex) {
			return matrix.getCoefficient(this.rowIndex, columnIndex);
		}

		public T getConstant() {
			return matrix.getConstant(this.rowIndex);
		}
		
		public void setCoefficient(int columnIndex, T value) {
			matrix.setCoefficient(this.rowIndex, columnIndex, value);
		}
		
		public void setCoefficients(T[] coefficients) {
			matrix.setCoefficientsRow(this.rowIndex, coefficients);
		}
		
		public void setConstant(T constant) {
			matrix.setConstant(this.rowIndex, constant);
		}
		
		/**
		 * 和另一列互調
		 * @param otherRow
		 */
		public void swap(Row<T> otherRow) {
			matrix.swapRows(this, otherRow);
		}
		
		/**
		 * 乘以 factor
		 * @param factor
		 */
		public void multiply(T factor) {
			matrix.multiplyRow(this, factor);
		}
		
		public List<Cell<T>> getCells() {
			List<Cell<T>> cells = new ArrayList<>();
			for (int cellIndex = -1; cellIndex < matrix.columns; cellIndex++) {
				cells.add(cell(cellIndex));
			}
			return cells;
		}
		
		public Cell<T> cell(int cellIndex) {
			if (cellIndex == -1) {
				return new ConstantCell<T>(this);
			}
			else {
				return new CoefficientCell<T>(this, cellIndex);
			}
		}
		
//		@Override
//		public String toString() {
//			StringBuilder sb = new StringBuilder();
//			
//			String lineSeparator = System.lineSeparator();
//			for (int i = 0; i < coefficients.length; i++) {
//				Object[] row1 = coefficients[i];
//				Object[] rowB = this.constants == null? null : this.constants[i];
//			    sb.append(Arrays.toString(row1))
//			      .append(rowB == null? "" : Arrays.toString(rowB))
//			      .append(lineSeparator);
//			}
//			return sb.toString();
//		}

	}
	
	@AllArgsConstructor
	@Data
	public static class PairCells<T> {
		private Cell<T> srcCell;
		private Cell<T> dstCell;
	}
}