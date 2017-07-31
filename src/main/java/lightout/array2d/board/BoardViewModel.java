package lightout.array2d.board;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lightout.game.Graph;
import lightout.game.Position;
import lightout.game.Rectangle;
import lightout.game.Vertex;
import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.delta.NeighberhoodDelta;
import lightout.game.delta.SelfDelta;
import lightout.game.solver.impl1.PercentSolvableCalculator;
import lombok.Getter;

public class BoardViewModel implements Rectangle {

	@Getter private int state;
	@Getter private Array2DGraph graph;
	@Getter private SelfDelta deltaForEditMode;
	@Getter private NeighberhoodDelta delta;
	
	@Getter private boolean editMode = false;
	@Getter private Position cursor;
	@Getter private int numberOfClicks = 0;	
	
	public BoardViewModel(int width, int height, int state) {
		this.graph = new Array2DGraph(width, height);
		this.graph.setCycled(true);
		this.delta = new NeighberhoodDelta(this.graph);
		this.deltaForEditMode = new SelfDelta(this.graph);
		this.state = state;
		this.graph.setModularNumber(this.state);
		this.reset();
	}

	@Override
	public int getWidth() {
		int width = ((Array2DGraph) this.graph).getWidth();
		return width;
	}
	
	
	@Override
	public int getHeight() {
		int height = ((Array2DGraph) this.graph).getHeight();
		return height;
	}
	
	@Override
	public void setSize(int width, int height) {
		this.graph = new Array2DGraph(width, height);
		this.graph.setCycled(true);
		this.graph.setModularNumber(this.state);
		this.delta = new NeighberhoodDelta(this.graph);
		this.reset();
	}
	
	public void setEditMode(boolean editMode) {
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
		this.graph.setModularNumber(this.state);
		this.reset();
	}	
	
	public void select(Position position) {
		this.setCursor(position);
		if (!this.graph.inScope(position)) {
			return;
		}
		this.deltaForEditMode.setTarget(this.graph);
		this.delta.setTarget(this.graph);
		if (this.editMode == true) {
			this.deltaForEditMode.apply(this.cursor);
		}
		else { // if (this.editMode == false)
			this.delta.apply(this.cursor);
		}
	}
	

	public void reset() {
		this.editMode = false;
		this.graph.reset(0);
		this.clearCursor();
		this.numberOfClicks = 0;
		this.recalculatePercentSolvable();
	}
	
	public void randomize() {
		this.editMode = false;
		this.clearCursor();
		this.numberOfClicks = 0;
		reset();
		this.graph.getVertexes().forEach(v->{
			int tt = (int) (Math.random() * state);
			for (int times = 0; times < tt; times++) {
				select(v.getPosition());
			}
		});
		this.recalculatePercentSolvable();
	}

	public boolean isSolved() {
		List<Vertex> vertexes = this.graph.getVertexes();
		return vertexes.stream().allMatch(v->v.getValue()==state - 1);
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		int width = ((Array2DGraph) this.graph).getWidth();
		int height = ((Array2DGraph) this.graph).getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Array2DPosition position = new Array2DPosition(i, j);
				output.append(this.graph.get(position));
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
		int width = ((Array2DGraph) this.graph).getWidth();
		int height = ((Array2DGraph) this.graph).getHeight();
		
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
