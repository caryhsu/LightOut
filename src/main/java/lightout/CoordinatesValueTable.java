package lightout;

import java.util.function.Function;

import lightout.Coordinates.Position;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(exclude={})
public class CoordinatesValueTable {

	private Coordinates coordinates;
	private int[][] values;
	
	public CoordinatesValueTable(int width, int height) {
		this(new Coordinates(width, height));
		setSize(width, height);
	}

	public void setSize(int width, int height) {
		this.coordinates.setWidth(width);
		this.coordinates.setHeight(height);
		this.values = new int[width][height];
		this.resetValues();
	}
	
	public void resetValues() {
		resetValues(0);
	}
	
	public void resetValues(int value) {
		for(int i = 0; i < this.getWidth(); i++) {
			for(int j = 0; j < this.getWidth(); j++) {
				values[i][j] = value;
			}
		}
	}
	
	public CoordinatesValueTable(Coordinates coordinates) {
		this(coordinates, new int[coordinates.getWidth()][coordinates.getHeight()]);
	}
	
	public int getWidth() {
		return this.coordinates.getWidth();
	}

	public int getHeight() {
		return this.coordinates.getHeight();
	}

	public int getValue(Position position) {
		return getValue(position.getX(), position.getY());
	}

	private int getValue(int x, int y) {
		return this.values[x][y];
	}
}
