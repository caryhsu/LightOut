package lightout.game;

import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.array2d.CrossDelta;
import lightout.solver.PercentSolvableCalculator;
import lombok.Getter;

public class Game {

	@Getter private Graph<Array2DPosition> values;
	
	@Getter private boolean editMode = false;
	@Getter private int cursorX;
	@Getter private int cursorY;
	@Getter private boolean cursor;
	
	@Getter private int numberOfClicks = 0;
	
	
	public Game(int width, int height, int state) {
		this.values = new Array2DGraph(width, height, state);
		this.reset();
	}

	public int getWidth() {
		int width = ((Array2DGraph) this.values).getWidth();
		return width;
	}
	
	public int getHeight() {
		int height = ((Array2DGraph) this.values).getHeight();
		return height;
	}
	
	public int getState() {
		int state = this.values.getState();
		return state;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		this.numberOfClicks = 0;
	}

	public void setSize(int width, int height) {
		this.values = new Array2DGraph(width, height, ((Array2DGraph) this.values).getState());
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
		int width = ((Array2DGraph) this.values).getWidth();
		int height = ((Array2DGraph) this.values).getHeight();
		this.values = new Array2DGraph(width, height, ((Array2DGraph) this.values).getState());
		this.reset();
	}	
	
	public void select(int x, int y) {
		this.setCursor(x, y);
		this.select();
	}
	
	public void select() {
		int x = this.cursorX;
		int y = this.cursorY;
		if (this.editMode == true) {
			this.values.increase(new Array2DPosition(x, y));
		}
		else { // if (this.editMode == false)
			int width = ((Array2DGraph) this.values).getWidth();
			int height = ((Array2DGraph) this.values).getHeight();
			numberOfClicks++;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Array2DPosition position = new Array2DPosition(i, j);
					this.values.increase(position, getDeltaValue(i, j));
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
		int width = ((Array2DGraph) this.values).getWidth();
		int height = ((Array2DGraph) this.values).getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				for (int times = 0; times < (int) (Math.random() * (width * height)); times++) {
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
		int state = ((Array2DGraph) this.values).getState();
		return this.values.isAllEquals(state - 1);
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		int width = ((Array2DGraph) this.values).getWidth();
		int height = ((Array2DGraph) this.values).getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Array2DPosition position = new Array2DPosition(i, j);
				output.append(this.values.get(position));
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
		int width = ((Array2DGraph) this.values).getWidth();
		int height = ((Array2DGraph) this.values).getHeight();
		int state= ((Array2DGraph) this.values).getState();
		
		int min = Math.min(width, height);
		CrossDelta delta = new CrossDelta(width, height);
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
			int width = ((Array2DGraph) this.values).getWidth();
			int height = ((Array2DGraph) this.values).getHeight();
			CrossDelta delta = new CrossDelta(width, height);
			Array2DPosition target = new Array2DPosition(x, y);
			Array2DPosition cursor = new Array2DPosition(this.cursorX, this.cursorY);
			return delta.getDeltaValue(target, cursor);
		}
	}
	
}
