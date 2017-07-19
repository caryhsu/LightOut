package lightout.game.array2d;

import lightout.game.Delta;
import lightout.game.Position;
import lombok.Getter;
import lombok.Setter;

public class CrossDelta implements Delta {

	@Getter @Setter private int width;
	@Getter @Setter private int height;
	
	public CrossDelta(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int getDeltaValue(Position targetPosition, Position cursorPosition) {
		int x = ((Array2DPosition) targetPosition).getX();
		int y = ((Array2DPosition) targetPosition).getY();
		int cursorX = ((Array2DPosition) cursorPosition).getX();
		int cursorY = ((Array2DPosition) cursorPosition).getY();
		if (x == cursorX && y == cursorY) {
			return 1;
		}
		else if (x-1 >= 0 && x-1 == cursorX && y == cursorY) {
			return 1;
		}
		else if (x+1 < this.width && x+1 == cursorX && y == cursorY) {
			return 1;
		}
		else if (x == cursorX && y-1 >= 0 && y-1 == cursorY) {
			return 1;
		}
		else if (x == cursorX && y+1 < this.height && y+1 == cursorY) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
