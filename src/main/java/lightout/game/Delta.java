package lightout.game;

import lightout.solver.IDelta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Delta implements IDelta {

	@Getter @Setter private int width;
	@Getter @Setter private int height;
	
	public Delta(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	
	public int getDeltaValue(int x, int y, int cursorX, int cursorY) {
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
