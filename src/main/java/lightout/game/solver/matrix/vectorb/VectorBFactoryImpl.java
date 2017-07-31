package lightout.game.solver.matrix.vectorb;

import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.delta.NeighberhoodDelta;
import lombok.Getter;

public class VectorBFactoryImpl implements VectorBFactory {

	@Getter private int width;
	@Getter private int height;
	@Getter private int state;
	@Getter private Array2DGraph graph;
	@Getter private NeighberhoodDelta delta;

	public VectorBFactoryImpl(NeighberhoodDelta delta) {
		this.delta = delta;
		this.graph = (Array2DGraph) delta.getTarget();
		this.width = graph.getWidth();
		this.height = graph.getHeight();
		this.state = graph.getModularNumber();
	}
	
	@Override
	public int[] newInstance() {
		int[] b = new int[width*height];
		int index = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Array2DPosition position = new Array2DPosition(i, j);
				b[index] = state - graph.get(position);
				index++;
			}
		}
		return b;
	}

}
