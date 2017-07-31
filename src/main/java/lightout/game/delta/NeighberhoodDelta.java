package lightout.game.delta;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lightout.game.Delta;
import lightout.game.Graph;
import lightout.game.Position;
import lombok.Getter;
import lombok.Setter;

public class NeighberhoodDelta implements Delta {

	@Getter @Setter private Graph target;

	public NeighberhoodDelta() {
		this.target = null;
	}
	
	public NeighberhoodDelta(Graph target) {
		this.target = target;
	}
	
	@Override
	public int getDeltaValue(Position targetPosition, Position cursor) {
		if (Objects.equals(targetPosition, cursor)) {
			return 1;
		} else {
			List<Position> neighberhood = Arrays.asList(this.target.getNeighberhood(cursor));
			if (neighberhood.contains(targetPosition)) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

}
