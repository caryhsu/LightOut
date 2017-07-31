package lightout.game.solver.matrix;

import lightout.field.Matrix;
import lightout.field.Zn;
import lightout.game.Graph;
import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.delta.NeighberhoodDelta;
import lombok.Getter;

public class MatrixAFactoryImpl implements MatrixAFactory {

	@Getter private int ASize;
	@Getter private Graph graph;
	@Getter private int state;
	@Getter private NeighberhoodDelta delta;

	public MatrixAFactoryImpl(NeighberhoodDelta delta) {
		this.delta = delta;
		this.graph = delta.getTarget();
		this.ASize = graph.getSize();
		this.state = graph.getModularNumber();
	}
	
	@Override
	public void init(Matrix<Integer> A) {
		graph.getVertexes().forEach(v1 -> {
			graph.getVertexes().forEach(v2 -> {
				A.set(v1.getPositionIndex(), v2.getPositionIndex(), delta.getDeltaValue(v1.getPosition(), v2.getPosition()));
			});
		});
	}

	@Override
	public Matrix<Integer> newInstance() {
		Matrix<Integer> A = new Matrix<Integer>(ASize, ASize, new Zn(this.state));
		init(A);
		return A;
	}

}
