package lightout;

import lightout.solver.PercentSolvableCalculator;
import lombok.Getter;

public class Game {

	@Getter private int size;
	@Getter private int state;
	@Getter private int[][] valueTable;
	@Getter private boolean editMode = false;
	@Getter private int cursorX;
	@Getter private int cursorY;
	@Getter private boolean cursor;
	@Getter private int numberOfClicks = 0;

	public Game(int size, int state) {
		this.size = size;
		this.state = state;
		this.valueTable = new int[size][size];
		this.reset();
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		this.numberOfClicks = 0;
	}

	public void setSize(int size) {
		this.size = size;
		this.valueTable = new int[size][size];
		this.reset();
	}
	
	public void setCursor(int x, int y) {
		System.out.println("setCursor:" + x + ", " + y);
		this.cursorX = x;
		this.cursorY = y;
		this.cursor = true;
	}

	public void clearCursor() {
		this.cursor = false;
	}

	public void setState(int state) {
		this.state = state;
	}	
	
	public void select(int x, int y) {
		this.setCursor(x, y);
		this.select();
	}
	
	public void select() {
		int x = this.cursorX;
		int y = this.cursorY;
		if (this.editMode == true) {
			this.valueTable[x][y] = (this.valueTable[x][y] + 1) % state; // cycle the selected	
		}
		else { // if (this.editMode == false)
			numberOfClicks++;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (isHighlight(i, j)) {
						valueTable[i][j] = (valueTable[i][j] + 1) % state;
					}
				}
			}
		}
	}

	public void reset() {
		this.editMode = false;
		this.reset(0);
		this.clearCursor();
		this.numberOfClicks = 0;
		this.recalculatePercentSolvable();
	}
	
	private void reset(int value) {
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
		this.editMode = false;
		this.clearCursor();
		this.numberOfClicks = 0;
		this.recalculatePercentSolvable();
	}

	public boolean isSolved() {
		int n = valueTable[0][0]; // check the first tile
		for (int i = 0; i < size; i++) { // as soon as we encounter a tile of different value
			for (int j = 0; j < size; j++) {
				if (valueTable[i][j] != n) {
					return false; // return false
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				output.append(valueTable[i][j]);
				if (this.isHighlight(i, j)) {
					output.append("*");
				}
				else {
					output.append(" ");
				}
				output.append(" ");
			}
			output.append("\n");
		}
		return output.toString();
	}

	public boolean isHighlight(int x, int y) {
		if (this.editMode == true) {
			if (x == this.cursorX && y == this.cursorX) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (this.cursor == false) {
			return false;
		}
		else { // (this.editMode == false) {
			if (x == this.cursorX && y == this.cursorY) {
				return true;
			}
			else if (x-1 >= 0 && x-1 == this.cursorX && y == this.cursorY) {
				return true;
			}
			else if (x+1 < size && x+1 == this.cursorX && y == this.cursorY) {
				return true;
			}
			else if (x == this.cursorX && y-1 >= 0 && y-1 == this.cursorY) {
				return true;
			}
			else if (x == this.cursorX && y+1 < size && y+1 == this.cursorY) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	private Double _percentSolvable;
	
	public double getPercentSolvable() {
		if (this._percentSolvable == null) {
			this.recalculatePercentSolvable();
		}
		return this._percentSolvable;
	}
	
	public void recalculatePercentSolvable() {
		PercentSolvableCalculator c = new PercentSolvableCalculator(size, state);
		this._percentSolvable = c.calculate().doubleValue();
	}	
}
