package lightout.game.delta;

import lightout.graph.Graph;
import lightout.graph.Position;

public interface Delta {

	public int getDeltaValue(Position target, Position cursor);
	
	public Graph getTarget();
	public void setTarget(Graph target);
	
	default public void apply(Position cursor) {
		Graph graph = this.getTarget();
		graph.getVertexes().forEach(vertex -> {
			int dv = getDeltaValue(vertex.getPosition(), cursor);
			vertex.increase(dv);
		});
	}

	default public void apply(Position cursor, int count) {
		for(int i = 0; i < count; i++) {
			apply(cursor);
		}
	}

	default public void apply(Position[] positions) {
		for(Position pos : positions) {
			apply(pos);
		}
	}

	default public void random(int state) {
		Graph graph = this.getTarget();
		graph.getVertexes().forEach(vertex -> {
			int tt = (int) (Math.random() * state);
			for(int i = 0; i < tt; i++) {
				apply(vertex.getPosition());
			}
		});
	}
}
