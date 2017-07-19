package lightout.game;

import java.util.Objects;

import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.array2d.NeighberhoodDelta;
import lightout.solver.PercentSolvableCalculator;
import lombok.Getter;

public class Game {

	@Getter private int state;
	@Getter private Array2DGraph values;
	@Getter private NeighberhoodDelta delta;
	
	@Getter private boolean editMode = false;
	@Getter private Position cursor;
	@Getter private int numberOfClicks = 0;	
	
	public Game(int width, int height, int state) {
		this.values = new Array2DGraph(width, height);
		this.delta = new NeighberhoodDelta(this.values);
		this.state = state;
		this.reset();
	}

	public int getWidth() {
		int width = this.values.getWidth();
		return width;
	}
	
	public int getHeight() {
		int height = this.values.getHeight();
		return height;
	}
	
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		this.numberOfClicks = 0;
	}

	public void setSize(int width, int height) {
		this.values = new Array2DGraph(width, height);
		this.delta = new NeighberhoodDelta(this.values);
		this.reset();
	}
	
	public void setCursor(Position position) {
		this.cursor = position;
	}

	public void clearCursor() {
		this.cursor = null;
	}

	public void setState(int state) {
		this.state = state;
		this.reset();
	}	
	
	public void select(int x, int y) {
		this.setCursor(new Array2DPosition(x, y));
		this.select();
	}
	
	private void select() {
		if (this.editMode == true) {
			if (this.cursor != null) {
				this.values.increase((Array2DPosition) this.cursor);

			}
		}
		else { // if (this.editMode == false)
			this.delta.apply((Array2DGraph) this.values, (Array2DPosition) this.cursor);
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
		this.editMode = false;
		this.clearCursor();
		this.numberOfClicks = 0;
		reset();
		//this.delta.random(this.values, state);
		int width = (this.values).getWidth();
		int height = (this.values).getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int tt = (int) (Math.random() * state);
				for (int times = 0; times < tt; times++) {
					select(i, j);
				}
			}
		}
		this.recalculatePercentSolvable();
	}

	public boolean isSolved() {
		return this.values.checkAllPositions(value -> value == state - 1);
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
		
		int min = Math.min(width, height);
		PercentSolvableCalculator c = new PercentSolvableCalculator(min, state, delta);
		this._percentSolvable = c.calculate().doubleValue();
	}

	public int getDeltaValue(int x, int y) {
		return this.getDeltaValue(new Array2DPosition(x, y));
	}
	
	public int getDeltaValue(Position target) {
		if (this.editMode == true) {
			if (Objects.equals(target, this.cursor)) {
				return 1;
			}
			else {
				return 0;
			}
		}
		else if (this.cursor == null) {
			return 0;
		}
		else { // (this.editMode == false) {
			return delta.getDeltaValue((Array2DPosition) target, (Array2DPosition) cursor);
		}
	}
	
}
