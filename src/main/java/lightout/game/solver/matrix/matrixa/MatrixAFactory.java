package lightout.game.solver.matrix.matrixa;

import lightout.field.Matrix;
import lightout.field.Zn;
import lightout.game.Graph;
import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.delta.NeighberhoodDelta;
import lombok.Getter;

public class MatrixAFactory {

	@Getter private int size;
	@Getter private Graph graph;
	@Getter private int state;
	@Getter private NeighberhoodDelta delta;

	public MatrixAFactory(NeighberhoodDelta delta) {
		this.delta = delta;
		this.graph = delta.getTarget();
		this.size = graph.getSize();
		this.state = graph.getModularNumber();
	}
	
	protected void init(Matrix<Integer> m) {
		graph.getVertexes().forEach(v1 -> {
			graph.getVertexes().forEach(v2 -> {
				m.setCoefficient(v1.getPositionIndex(), v2.getPositionIndex(), 
						delta.getDeltaValue(v1.getPosition(), v2.getPosition()));
			});
		});
	}

	public Matrix<Integer> newInstance() {
		Matrix<Integer> m = new Matrix<Integer>(this.size, this.size, new Zn(this.state));
		init(m);
		return m;
	}

}
