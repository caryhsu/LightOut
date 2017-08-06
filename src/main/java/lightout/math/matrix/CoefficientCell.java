package lightout.math.matrix;

import lightout.math.matrix.Matrix.Row;
import lombok.Getter;

public class CoefficientCell<T> implements Cell<T> {

	private Row<T> row;
	@Getter private int cellIndex;
	
	public CoefficientCell(Row<T> row, int cellIndex) {
		this.row = row;
		this.cellIndex = cellIndex;
	}
	
	public T getValue() {
		return row.getCoefficient(this.cellIndex);
	}
	
	public void setValue(T value) {
		row.setCoefficient(this.cellIndex, value);
	}
	
}