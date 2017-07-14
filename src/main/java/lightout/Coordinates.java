package lightout;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode(exclude= {})
public class Coordinates {

	@Getter @Setter private int width;
	@Getter @Setter private int height;

	public boolean isOutOfRange(int x, int y) {
		return (x < 0 || y < 0 || x >= width || y >= height);
	}
	
	public Position move(Position p, Direction direction) {
		return move(p, direction, 1);
	}
	
	public Position move(Position p, Direction direction, int delta) {
		int x = p.x;
		int y = p.y;
		
		switch(direction) {
		case LEFT:
			x = x - delta; break;
		case RIGHT:
			y = y + delta; break;
		case UP:
			y = y - delta; break;
		case DOWN:
			x = x + delta; break;
		default:
			throw new RuntimeException("unknown Direction:" + direction);
		}
		if (isOutOfRange(x, y)) return null;
		return new Position(this, x, y);
	}
	
	@EqualsAndHashCode(exclude= {})
	public static class Position {
		
		@Getter private Coordinates coordinates;
		@Getter private int x; 
		@Getter private int y;
		
		public Position(Coordinates coordinates, int x, int y) {
			if (coordinates.isOutOfRange(x, y)) {
				throw new RuntimeException("OutOfRange:" + x + "," + y);
			}
			this.x = x;
			this.y = y;
		}

		public Position move(Direction direction) {
			return move(direction, 1);
		}

		public Position move(Direction direction, int delta) {
			return coordinates.move(this, direction, delta);
		}
				
		@Override
		public String toString() {
			return "[" + this.x + "," + this.y + "]";
		}
	
	}

	public enum Direction {
		UP, DOWN, RIGHT, LEFT;
	}

}
