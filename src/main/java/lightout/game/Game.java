package lightout.game;

import lightout.game.array2d.Array2DPosition;
import lightout.game.array2d.CrossDelta;
import lightout.solver.PercentSolvableCalculator;
import lombok.Getter;

public class Game {

	@Getter private int width;
	@Getter private int height;
	@Getter private int state;
	@Getter private Graph values;
	
	@Getter private boolean editMode = false;
	@Getter private int cursorX;
	@Getter private int cursorY;
	@Getter private boolean cursor;
	
	@Getter private int numberOfClicks = 0;
	
	
	public Game(int width, int height, int state) {
		this.width = width;
		this.height = height;
		this.state = state;
		this.values = new Graph(this.width, this.height, this.state);
		this.reset();
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		this.numberOfClicks = 0;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.values = new Graph(this.width, this.height, this.state);
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
			this.values.increase(x, y);
		}
		else { // if (this.editMode == false)
			numberOfClicks++;
			for (int i = 0; i < this.width; i++) {
				for (int j = 0; j < this.height; j++) {
					this.values.increase(i, j, getDeltaValue(i, j));
				}
			}
		}
	}

	public void reset() {
		this.editMode = false;
		this.values.reset(0);
		this.clearCursor();
		this.numberOfClicks = 0;
		this.recalculatePercentSolvable();
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
		return this.values.isAllEquals(this.state - 1);
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				output.append(this.values.get(i, j));
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
		CrossDelta delta = new CrossDelta(this.width, this.height);
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
			CrossDelta delta = new CrossDelta(this.width, this.height);
			Array2DPosition target = new Array2DPosition(x, y);
			Array2DPosition cursor = new Array2DPosition(this.cursorX, this.cursorY);
			return delta.getDeltaValue(target, cursor);
		}
	}
	
}
