package lightout.game;

import lightout.solver.PercentSolvableCalculator;
import lombok.Getter;

public class Game {

	@Getter private int width;
	@Getter private int height;
	@Getter private int state;
	@Getter private int[][] valueTable;
	
	@Getter private boolean editMode = false;
	@Getter private int cursorX;
	@Getter private int cursorY;
	@Getter private boolean cursor;
	
	@Getter private int numberOfClicks = 0;
	
	
	public Game(int width, int height, int state) {
		this.width = width;
		this.height = height;
		this.state = state;
		this.valueTable = new int[this.width][this.height];
		this.reset();
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		this.numberOfClicks = 0;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.valueTable = new int[this.width][this.height];
		this.reset();
	}
	
	public void setCursor(int x, int y) {
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
			for (int i = 0; i < this.width; i++) {
				for (int j = 0; j < this.height; j++) {
					this.valueTable[i][j] += getDeltaValue(i, j);
					this.valueTable[i][j] %= state;
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
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				this.valueTable[i][j] = value;
			}
		}
	}

	public void randomize() {
		reset();
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				for (int times = 0; times < (int) (Math.random() * (this.width * this.height)); times++) {
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
		int n = this.valueTable[0][0]; // check the first tile
		for (int i = 0; i < this.width; i++) { // as soon as we encounter a tile of different value
			for (int j = 0; j < this.height; j++) {
				if (this.valueTable[i][j] != n) {
					return false; // return false
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				output.append(this.valueTable[i][j]);
				if (this.getDeltaValue(i, j)>0) {
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


	private Double _percentSolvable;
	
	public double getPercentSolvable() {
		if (this._percentSolvable == null) {
			this.recalculatePercentSolvable();
		}
		return this._percentSolvable;
	}
	
	public void recalculatePercentSolvable() {
		int min = Math.min(this.width, this.height);
		Delta delta = new Delta(this.width, this.height);
		PercentSolvableCalculator c = new PercentSolvableCalculator(min, state, delta);
		this._percentSolvable = c.calculate().doubleValue();
	}

	public int getDeltaValue(int x, int y) {
		if (this.editMode == true) {
			if (x == this.cursorX && y == this.cursorY) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else if (this.cursor == false) {
			return 0;
		}
		else { // (this.editMode == false) {
			Delta delta = new Delta(this.width, this.height);
			return delta.getDeltaValue(this.cursorX, this.cursorY, x, y);
		}
	}
	
}
