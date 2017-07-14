package lightout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Delta {

	@Getter @Setter private int width;
	@Getter @Setter private int height;
	
	public int getDeltaValue(int x, int y, int cursorX, int cursorY) {
		if (x == cursorX && y == cursorY) {
			return 1;
		}
		else if (x-1 >= 0 && x-1 == cursorX && y == cursorY) {
			return 1;
		}
		else if (x+1 < width && x+1 == cursorX && y == cursorY) {
			return 1;
		}
		else if (x == cursorX && y-1 >= 0 && y-1 == cursorY) {
			return 1;
		}
		else if (x == cursorX && y+1 < width && y+1 == cursorY) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
