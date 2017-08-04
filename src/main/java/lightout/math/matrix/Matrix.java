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

public final class Matrix implements Cloneable {
	
	@Getter private int rows;
	@Getter private int columns;
	@Getter private int[][] coefficients;
	private int[][] constants; 

	@Getter private FieldOperators<Integer> op;
	
	@SuppressWarnings("unchecked")
	public Matrix(int rows, int columns, FieldOperators<Integer> f) {
		this.rows = rows;
		this.columns = columns;
		this.op = f;
		this.coefficients = new int[rows][columns];
		this.constants = new int[rows][1];
	}
		
	public int getCoefficient(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= this.rows || columnIndex < 0 || columnIndex >= this.columns)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		return this.coefficients[rowIndex][columnIndex];
	}
	
	public int getConstant(int rowIndex) {
		if (rowIndex < 0 || rowIndex >= this.rows)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		return this.constants[rowIndex][0];
	}
	
	public void setCoefficient(int rowIndex, int columnIndex, int value) {
		if (rowIndex < 0 || rowIndex >= this.rows || columnIndex < 0 || columnIndex >= this.columns)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.coefficients[rowIndex][columnIndex] = value;
	}
	
	public void setCoefficientsRow(int rowIndex, int[] coefficients) {
		if (rowIndex < 0 || rowIndex >= this.rows || coefficients.length != this.columns)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.coefficients[rowIndex] = coefficients;
	}

	public void setCoefficients(int[][] coefficients) {
		if (coefficients.length != this.rows || coefficients[0].length != this.columns)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.coefficients = coefficients;
	}

	public void setConstant(int rowIndex, int constant) {
		if (rowIndex < 0 || rowIndex >= this.rows)
			throw new IndexOutOfBoundsException("Row or column index out of bounds");
		this.constants[rowIndex][0] = constant;
	}

	public void setContants(int[] constants) {
		for(int index = 0; index < rows; index++) {
			this.constants[index][0] = constants[index];
		}
	}
	
	public Matrix clone() {
		int rows = this.rows;
		int columns = this.columns;
		Matrix result = new Matrix(rows, columns, op);
		for (int i = 0; i < coefficients.length; i++)
			System.arraycopy(coefficients[i], 0, result.coefficients[i], 0, columns);
		return result;
	}
	
	/**
	 * 將兩列互調
	 * @param row0
	 * @param row1
	 */
	public void swapRows(Row row0, Row row1) {
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
		int[] temp = coefficients[rowIndex0];
		coefficients[rowIndex0] = coefficients[rowIndex1];
		coefficients[rowIndex1] = temp;
		// change identity matrix
		int[] temp2 = constants[rowIndex0];
		constants[rowIndex0] = constants[rowIndex1];
		constants[rowIndex1] = temp2;
	}
	
	/**
	 * 將某列乘以 factor
	 * @param rowIndex
	 * @param factor
	 */
	public void multiplyRow(Row row, int factor) {
		multiplyRow(row.rowIndex, factor);
	}
	
	/**
	 * 將某列乘以 factor
	 * @param rowIndex
	 * @param factor
	 */
	public void multiplyRow(int rowIndex, int factor) {
		this.updateValues(rowIndex, cell -> {return op.multiply(cell.getValue(), factor);});
	}
	
	public void _multiplyRow(int rowIndex, int factor) {
		Row row = row(rowIndex);
		for (int j = 0, cols = this.columns; j < cols; j++) {
			int newCoefficient = op.multiply(row.getCoefficient(j), factor);
			row.setCoefficient(j, newCoefficient);
		}
		row.setConstant(op.multiply(row.getConstant(), factor));
	}
	
	public void updateValues(int rowIndex, Function<Cell,Integer> function) {
		Row row = row(rowIndex);
		row.getCells().forEach(cell-> {
			int newCoef = function.apply(cell);
			cell.setValue(newCoef);
		});
	}
	
	public void updateValues(int srcRowIndex, int dstRowIndex, Function<PairCells,Integer> function) {
		Objects.requireNonNull(function);
		Row srcRow = row(srcRowIndex);
		Row dstRow = row(dstRowIndex);
		
		if (dstRow == null) {
			throw new NullPointerException();
		}
		if (dstRow.getCells() == null) {
			throw new NullPointerException();
		}
		dstRow.getCells().forEach(dstCell-> {
			Cell srcCell = srcRow.cell(dstCell.getCellIndex());
			PairCells pair = new PairCells(srcCell, dstCell);
			int newCoef = 0;
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

	public void addRows(int srcRowIndex, int dstRowIndex, int factor) {
		updateValues(srcRowIndex, dstRowIndex, pair-> {
			Cell srcCell = pair.srcCell;
			Cell dstCell = pair.dstCell;
			Integer result = new Expression<Integer>()
					.setOp(this.op)
					.setInitial(srcCell.getValue())
					.multiply(factor)
					.add(dstCell.getValue())
					.result();
			return result;
		});
	}
	
	public void _addRows(int srcRowIndex, int dstRowIndex, int factor) {
		for (int j = 0, cols = this.columns; j < cols; j++) {
			setCoefficient(dstRowIndex, j, 
					op.add(getCoefficient(dstRowIndex, j), op.multiply(getCoefficient(srcRowIndex, j), factor)));
		}
		setConstant(dstRowIndex, op.add(getConstant(dstRowIndex), 
				op.multiply(getConstant(srcRowIndex), factor)));
	}
	
	/**
	 * 矩陣相乘
	 * @param other
	 * @return
	 */
	public Matrix multiply(Matrix other) {
		if (this.columns != other.rows)
			throw new IllegalArgumentException("Incompatible matrix sizes for multiplication");
		int rows = this.rows;
		int columns = other.columns;
		int cells = this.columns;
		Matrix result = new Matrix(rows, columns, op);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int sum = op.zero();
				for (int k = 0; k < cells; k++)
					sum = op.add(op.multiply(getCoefficient(i, k), other.getCoefficient(k, j)), sum);
				result.setCoefficient(i, j, sum);
			}
		}
		return result;
	}
	
	public void reducedRowEchelonForm() throws IllegalArgumentException {
		new GaussianElimination(this).reduce();
	}
	
	public int[] getConstants() {
		int[] constants = new int[this.constants.length];
		for(int i = 0; i < this.constants.length; i++) {
			constants[i] = this.constants[i][0];
		}
		return constants;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		String lineSeparator = System.lineSeparator();
		for (int i = 0; i < coefficients.length; i++) {
			int[] row1 = coefficients[i];
			int[] rowB = this.constants == null? null : this.constants[i];
		    sb.append(Arrays.toString(row1))
		      .append(rowB == null? "" : Arrays.toString(rowB))
		      .append(lineSeparator);
		}
		return sb.toString();
	}
	
	public Row row(int rowIndex) {
		return new Row(rowIndex);
	}
	
	public class Row {
		@Getter private int rowIndex;
		
		private Row(int rowIndex) {
			this.rowIndex = rowIndex;
		}
		
		public int getCoefficient(int columnIndex) {
			return Matrix.this.getCoefficient(this.rowIndex, columnIndex);
		}

		public int getConstant() {
			return Matrix.this.getConstant(this.rowIndex);
		}
		
		public void setCoefficient(int columnIndex, int value) {
			Matrix.this.setCoefficient(this.rowIndex, columnIndex, value);
		}
		
		public void setCoefficients(int[] coefficients) {
			Matrix.this.setCoefficientsRow(this.rowIndex, coefficients);
		}
		
		public void setConstant(int constant) {
			Matrix.this.setConstant(this.rowIndex, constant);
		}
		
		/**
		 * 和另一列互調
		 * @param otherRow
		 */
		public void swap(Row otherRow) {
			Matrix.this.swapRows(this, otherRow);
		}
		
		/**
		 * 乘以 factor
		 * @param factor
		 */
		public void multiply(int factor) {
			Matrix.this.multiplyRow(this, factor);
		}
		
		public List<Cell> getCells() {
			List<Cell> cells = new ArrayList<>();
			for (int cellIndex = -1; cellIndex < Matrix.this.columns; cellIndex++) {
				cells.add(cell(cellIndex));
			}
			return cells;
		}
		
		public Cell cell(int cellIndex) {
			if (cellIndex == -1) {
				return new ConstantCell();
			}
			else {
				return new CoefficientCell(cellIndex);
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
				
		public class CoefficientCell implements Cell {

			@Getter private int cellIndex;
			
			private CoefficientCell(int cellIndex) {
				this.cellIndex = cellIndex;
			}
			
			public int getValue() {
				return Row.this.getCoefficient(this.cellIndex);
			}
			
			public void setValue(int value) {
				Row.this.setCoefficient(this.cellIndex, value);
			}
			
		}

		public class ConstantCell implements Cell {
			
			private ConstantCell() {
			}
			
			public int getCellIndex() {
				return -1;
			}
			
			public int getValue() {
				return Row.this.getConstant();
			}
			
			public void setValue(int value) {
				Row.this.setConstant(value);
			}
			
		}
	}
	
	@AllArgsConstructor
	@Data
	public static class PairCells {
		private Cell srcCell;
		private Cell dstCell;
	}
	
	public static interface Cell {
		public int getCellIndex();
		public int getValue();
		public void setValue(int value);
	}
}