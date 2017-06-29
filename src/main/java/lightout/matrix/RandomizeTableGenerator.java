package lightout.matrix;

import lombok.Getter;

public class RandomizeTableGenerator {

	@Getter private int size;
	@Getter private int state;
	@Getter private int[][] valueTable;
	
	public RandomizeTableGenerator(int size, int state) {
		this.size = size;
		this.state = state;
	}
	
	public int[][] generate() {
		this.valueTable = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int times = 0; times < (int) (Math.random() * size); times++) {
					select(i, j);
				}
			}
		}
		return this.valueTable;
	}
	
	public void select(int x, int y) {
		valueTable[x][y] = (valueTable[x][y] + 1) % state; // cycle the
															// selected
		//if (editMode == false) {
			// tile
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
		//}
	}

}
