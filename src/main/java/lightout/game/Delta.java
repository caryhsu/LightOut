package lightout.game;

public interface Delta {

	public int getDeltaValue(Position target, Position cursor);
	
	default public void apply(Graph graph, Position cursor) {
		graph.forEachVertex(vertex -> {
			int dv = getDeltaValue(vertex.getPosition(), cursor);
			vertex.increase(dv);
		});
	}

	default public void apply(Graph graph, Position cursor, int count) {
		for(int i = 0; i < count; i++) {
			apply(graph, cursor);
		}
	}

	default public void random(Graph graph, int state) {
		graph.forEachVertex(vertex -> {
			int tt = (int) (Math.random() * state);
			for(int i = 0; i < tt; i++) {
				apply(graph, vertex.getPosition());
			}
		});
	}
}
