package lightout.graph;

public interface Vertex {

	public Position getPosition();
	
	public int getPositionIndex();
	
	public int getValue();
	
	public void setValue(int value);
	
	default public void increase() {
		increase(1);
	}
	
	default public void increase(int delta) {
		setValue(getValue() + delta);
	};
	
}
