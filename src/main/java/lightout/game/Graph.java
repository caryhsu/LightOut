package lightout.game;

public interface Graph {

	public void increase(int x, int y);

	public void increase(int i, int j, int deltaValue);

	public void reset(int value);

	public boolean isAllEquals(int i);

	public int get(int i, int j);

	public int[][] getValues();
	
}
