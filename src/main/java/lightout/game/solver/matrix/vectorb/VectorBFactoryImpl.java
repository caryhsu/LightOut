package lightout.game.solver.matrix.vectorb;

import lightout.game.Graph;
import lightout.game.delta.NeighberhoodDelta;
import lombok.Getter;

public class VectorBFactoryImpl implements VectorBFactory {

	@Getter private int state;
	@Getter private Graph graph;
	@Getter private NeighberhoodDelta delta;

	public VectorBFactoryImpl(NeighberhoodDelta delta) {
		this.delta = delta;
		this.graph = delta.getTarget();
		this.state = graph.getModularNumber();
	}
	
	@Override
	public int[] newInstance() {
		int[] b = new int[graph.getSize()];
		graph.getVertexes().forEach(v->{
			b[v.getPositionIndex()] = state - v.getValue();
		});
		return b;
	}

}
