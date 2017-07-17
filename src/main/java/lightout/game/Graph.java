package lightout.game;

import lombok.Getter;

public class Graph {

	@Getter private int width;
	@Getter private int height;
	@Getter private int state;
	private int[][] values;
	
	public Graph(int width, int height, int state) {
		this.width = width;
		this.height = height;
		this.state = state;
		this.values = new int[this.width][this.height];
	}

	public void reset(int value) {
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				this.values[i][j] = value;
			}
		}
	}
	
	public int get(int x, int y) {
		return this.values[x][y];
	}

	public void increase(int x, int y) {
		increase(x, y, 1);
	}
	
	public void increase(int x, int y, int delta) {
		this.values[x][y] += delta;
		this.values[x][y] %= this.state;
	}

	public boolean isAllEquals(int n) {
		for (int i = 0; i < this.width; i++) { // as soon as we encounter a tile of different value
			for (int j = 0; j < this.height; j++) {
				if (this.values[i][j] != n) {
					return false; // return false
				}
			}
		}
		return true;
	}

	public int[][] getValues() {
		return this.values;
	}
	
}
