package lightout.game;

import java.util.function.Consumer;

public interface Graph<P extends Position> {

	public int getState();

	default public void increase(P position) {
		increase(position, 1);
	};

	public void increase(P position, int deltaValue);

	default public void reset() {
		reset(0);
	};
	
	public void reset(int value);

	public boolean isAllEquals(int i);

	public int get(P position);

	public int[][] getValues();
	
	public P[] getPositions();
	
	public void forEachPosition(Consumer<P> action);
	
}
