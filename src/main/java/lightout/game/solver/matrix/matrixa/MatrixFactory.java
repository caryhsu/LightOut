package lightout.game.solver.matrix.matrixa;

import lightout.game.delta.NeighberhoodDelta;
import lightout.graph.Graph;
import lightout.math.algebra.FieldOperators;
import lightout.math.algebra.Zn;
import lightout.math.algebra.ZnElement;
import lightout.math.matrix.Matrix;
import lombok.Getter;

public class MatrixFactory {

	private int size;
	@Getter private Graph graph;
	@Getter private NeighberhoodDelta delta;
	@Getter private FieldOperators<ZnElement> op;

	public MatrixFactory(NeighberhoodDelta delta) {
		this(delta, delta.getTarget(), delta.getTarget().getModularNumber());
	}
	
	public MatrixFactory(NeighberhoodDelta delta, Graph graph, int state) {
		this(delta, graph, new Zn(state));
	}
	
	public MatrixFactory(NeighberhoodDelta delta, Graph graph, FieldOperators<ZnElement> op) {
		this.delta = delta;
		this.graph = graph;
		this.size = graph.getSize();
		this.op = op;
	}

	protected void initCoefficients(Matrix<ZnElement> m) {
		graph.getVertexes().forEach(v1 -> {
			graph.getVertexes().forEach(v2 -> {
				m.setCoefficient(
						v1.getPositionIndex(), v2.getPositionIndex(), 
						delta.getDeltaValue(v1.getPosition(), v2.getPosition()));
			});
		});
	}

	protected void initConstants(Matrix<ZnElement> m) {
		int[] constants = new int[graph.getSize()];
		graph.getVertexes().forEach(v->{
			constants[v.getPositionIndex()] = op.negate(v.getValue());
		});
		m.setConstants(constants);
	}

	protected void initMatrix(Matrix<ZnElement> m) {
		this.initCoefficients(m);
		this.initConstants(m);
	}
	
	public Matrix newInstance() {
		Matrix<ZnElement> m = new Matrix<>(this.size, this.size, this.op);
		initMatrix(m);
		return m;
	}

}
