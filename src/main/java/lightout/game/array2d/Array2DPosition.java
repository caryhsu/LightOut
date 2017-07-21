package lightout.game.array2d;

import lightout.game.Position;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(exclude={})
public class Array2DPosition implements Position, Comparable<Array2DPosition> {

	@Getter private int x;
	@Getter private int y;
	
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

	@Override
	public int compareTo(Array2DPosition o) {
		int r1 = Integer.compare(this.x, o.x);
		if (r1 != 0) return r1;
		int r2 = Integer.compare(this.y, o.y);
		return r2;
	}
}
