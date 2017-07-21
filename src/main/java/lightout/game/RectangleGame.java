package lightout.game;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.array2d.NeighberhoodDelta;
import lightout.game.array2d.SelfDelta;
import lightout.solver.PercentSolvableCalculator;
import lombok.Getter;

public class RectangleGame implements Game, Rectangle {

	@Getter private int state;
	@Getter private Graph values;
	@Getter private SelfDelta deltaForEditMode;
	@Getter private NeighberhoodDelta delta;
	
	@Getter private boolean editMode = false;
	@Getter private Position cursor;
	@Getter private int numberOfClicks = 0;	
	
	public RectangleGame(int width, int height, int state) {
		this.values = new Array2DGraph(width, height);
		this.delta = new NeighberhoodDelta(this.values);
		this.deltaForEditMode = new SelfDelta(this.values);
		this.state = state;
		this.reset();
	}

	@Override
	public int getWidth() {
		int width = ((Array2DGraph) this.values).getWidth();
		return width;
	}
	
	
	@Override
	public int getHeight() {
		int height = ((Array2DGraph) this.values).getHeight();
		return height;
	}
	
	@Override
	public void setSize(int width, int height) {
		this.values = new Array2DGraph(width, height);
		this.delta = new NeighberhoodDelta(this.values);
		this.reset();
	}
	
	public void setEditMode(boolean editMode) {
		this.reset();
		this.editMode = editMode;
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
	
	public void select(Position position) {
		this.setCursor(position);
		if (!this.values.inScope(position)) {
			return;
		}
		if (this.editMode == true) {
			this.deltaForEditMode.apply(this.cursor);
		}
		else { // if (this.editMode == false)
			this.delta.apply(this.cursor);
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
		Arrays.asList(this.values.getVertexes()).forEach(
			v->{
				int tt = (int) (Math.random() * state);
				for (int times = 0; times < tt; times++) {
					select(v.getPosition());
				}
			}
		);
		this.recalculatePercentSolvable();
	}

	public boolean isSolved() {
		List<Vertex> vertexes = Arrays.asList(this.values.getVertexes());
		return vertexes.stream().allMatch(v->v.getValue()==state - 1);
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