package lightout.game;

import java.util.function.Consumer;

public interface Graph<P extends Position> {

	default public void reset() {
		reset(0);
	};
	
	public void reset(int value);

	public boolean isAllEquals(int n);

	public int get(P position);
	
	public void set(P position, int value);

	public int[][] getValues();
	
	public P[] getPositions();
	
	public void forEachPosition(Consumer<P> action);
	
}
