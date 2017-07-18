package lightout.game.array2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import lightout.game.Graph;
import lombok.Getter;

public class Array2DGraph implements Graph<Array2DPosition> {

	@Getter private int width;
	@Getter private int height;
	@Getter private int state;
	private int[][] values;
	
	public Array2DGraph(int width, int height, int state) {
		this.width = width;
		this.height = height;
		this.state = state;
		this.values = new int[this.width][this.height];
	}

	public void reset() {
		reset(0);
	}
	
	public void reset(int value) {
		this.forEachPosition(
				p -> this.values[p.getX()][p.getY()] = value
		);
	}
	
	public void forEachPosition(Consumer<Array2DPosition> action) {
		Objects.requireNonNull(action);
        for (Array2DPosition position : this.getPositions()) {
            action.accept(position);
        }
	}
	
	@Override
	public Array2DPosition[] getPositions() {
		List<Array2DPosition> positions = new ArrayList<>();
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				positions.add(new Array2DPosition(i, j));
			}
		}
		return positions.toArray(new Array2DPosition[] {});
	}

	public int get(Array2DPosition position) {
		return this.values[position.getX()][position.getY()];
	}
	
	public void increase(Array2DPosition position, int delta) {
		int x = position.getX();
		int y = position.getY();
		this.values[x][y] += delta;
		while(this.values[x][y] < 0) {
			this.values[x][y] += this.state;
		}
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
