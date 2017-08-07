package lightout.graph;

public interface Vertex<T> {

	public Position getPosition();
	
	public int getPositionIndex();
	
	public T getValue();
	
	public void setValue(T value);
	
	default public void increase() {
		increase(1);
	}
	
	default public void increase(int delta) {
		setValue(getValue() + delta);
	};
	
}
