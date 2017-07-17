package lightout.game;

public interface Graph<P extends Position> {

	default public void increase(P position) {
		increase(position, 1);
	};

	public void increase(P position, int deltaValue);

	public void reset(int value);

	public boolean isAllEquals(int i);

	public int get(P position);

	public int[][] getValues();
	
}
