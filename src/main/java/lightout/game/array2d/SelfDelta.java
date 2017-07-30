package lightout.game.array2d;

import java.util.Objects;

import lightout.game.Delta;
import lightout.game.Graph;
import lightout.game.Position;
import lombok.Getter;
import lombok.Setter;

public class SelfDelta implements Delta {

	@Getter @Setter private Graph target;

	public SelfDelta(Graph target) {
		this.target = target;
	}
	
	public SelfDelta() {
		this.target = null;
	}
	
	@Override
	public void apply(Position cursor) {
		Graph graph = this.getTarget();
		int dv = getDeltaValue(cursor, cursor);
		graph.getVertex(cursor).increase(dv);
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
