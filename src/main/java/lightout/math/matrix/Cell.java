package lightout.math.matrix;

public interface Cell<T> {
	public int getCellIndex();
	public T getValue();
	public void setValue(T value);
}