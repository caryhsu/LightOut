package lightout.game.array2d;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import lightout.game.Graph;
import lombok.Getter;

public class Array2DGraph implements Graph<Array2DPosition> {

	@Getter private int width;
	@Getter private int height;
	private int[][] values;
	
	public Array2DGraph(int width, int height) {
		this.width = width;
		this.height = height;
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
		int x = position.getX();
		int y = position.getY();
		return this.values[x][y];
	}
	
	public void set(Array2DPosition position, int value) {
		int x = position.getX();
		int y = position.getY();
		this.values[x][y] = value;
	}

	public void increase(Array2DPosition position, int delta) {
		int x = position.getX();
		int y = position.getY();
		this.values[x][y] += delta;
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

	@Override
	public Array2DPosition[] getNeighberhood(Array2DPosition position) {  
		char[] directions = new char[] {'L', 'R', 'U', 'D'};
		List<Array2DPosition> positions = new ArrayList<Array2DPosition>();
		for(char direction : directions) {
			Array2DPosition newPos = move(position, direction);
			if (newPos != null)
				positions.add(newPos);
		}
		return positions.toArray(new Array2DPosition[] {});
	}

	@Override
	public Array2DPosition move(Array2DPosition position, char direction) {
		int x = position.getX();
		int y = position.getY();
		if (direction == '←' || direction == 'L') {
			x = x - 1;
		}
		else if (direction == '→' || direction == 'R') {
			x = x + 1;
		}
		else if (direction == '↑' || direction == 'U') {
			y = y - 1;
		}
		else if (direction == '↓' || direction == 'D') {
			y = y + 1;
		}
		if (x < 0 || x >= this.width || y < 0 || y <= this.height) {
			return null;
		}
		return new Array2DPosition(x, y);
	}
	
	public int[][] getValues() {
		return this.values;
	}
	
	
}
