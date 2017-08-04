package lightout.game.delta;

import java.util.Objects;

import lightout.graph.Graph;
import lightout.graph.Position;
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
