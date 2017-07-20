package lightout.game.array2d;

import java.util.Objects;

import lightout.game.Delta;
import lightout.game.Graph;
import lightout.game.Position;
import lombok.Getter;

public class SelfDelta implements Delta {

	@Getter private Graph graph;
	
	public SelfDelta(Graph graph) {
		this.graph = graph;
	}
	
	@Override
	public int getDeltaValue(Position target, Position cursor) {
		if (Objects.equals(target, cursor)) {
			return 1;
		} else {
			return 0;
		}
	}

}
