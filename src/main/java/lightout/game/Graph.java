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
	
	default public void increase(P position, int value) {
		set(position, get(position) + value);
	}
	
	public int[][] getValues();
	
	public P[] getPositions();
	
	public void forEachPosition(Consumer<P> action);
	
	public P[] getNeighberhood(P position);

	public P move(P position, char direction);
	
}
