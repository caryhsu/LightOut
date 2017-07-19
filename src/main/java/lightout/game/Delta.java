package lightout.game;

public interface Delta<G extends Graph<P>, P extends Position> {

	public int getDeltaValue(P targetPosition, P cursorPosition);
	
	default public void apply(G graph, P cursorPosition) {
		graph.forEachPosition(position -> {
			int dv = getDeltaValue(position, cursorPosition);
			graph.increase(position, dv);
		});
	}
}
