package lightout.math.matrix;

import lightout.math.matrix.Matrix.Row;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConstantCell<T> implements Cell<T> {
	
	private Matrix.Row<T> row;
	
	ConstantCell() {
	}
	
	public int getCellIndex() {
		return -1;
	}
	
	public T getValue() {
		return row.getConstant();
	}
	
	public void setValue(T value) {
		row.setConstant(value);
	}
	
}