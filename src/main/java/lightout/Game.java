package lightout;

import lombok.Getter;

public class Game {

	@Getter private int size;
	@Getter private int state;
	@Getter private int[][] valueTable;
	
	public Game(int size, int state) {
		this.size = size;
		this.state = state;
		this.valueTable = new int[size][size];
	}
	
	public void select(int x, int y) {
		this.valueTable[x][y] = (this.valueTable[x][y] + 1) % state; // cycle the selected
		
		if (x - 1 >= 0) { // cycle the left tile
			valueTable[x - 1][y] = (valueTable[x - 1][y] + 1) % state;
			// System.out.println("you have cycled " + (x-1) + " " + y);
		}
		if (x + 1 <= size - 1) { // cycle the right tile
			valueTable[x + 1][y] = (valueTable[x + 1][y] + 1) % state;
		}
		if (y + 1 <= size - 1) { // cycle the bottom tile
			valueTable[x][y + 1] = (valueTable[x][y + 1] + 1) % state;
		}
		if (y - 1 >= 0) { // cycle the top tile
			valueTable[x][y - 1] = (valueTable[x][y - 1] + 1) % state;
		}
	}

	public void reset() {
		this.reset(0);
	}
	
	public void reset(int value) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				valueTable[i][j] = value;
			}
		}
	}

	public void randomize() {
		reset();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int times = 0; times < (int) (Math.random() * size); times++) {
					select(i, j);
				}
			}
		}
	}

}
