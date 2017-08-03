package lightout.game.solver.matrix.matrixa;

import lightout.field.FieldOperators;
import lightout.field.Matrix;
import lightout.field.Zn;
import lightout.game.Graph;
import lightout.game.delta.NeighberhoodDelta;
import lombok.Getter;

public class MatrixFactory {

	private int size;
	@Getter private Graph graph;
	@Getter private NeighberhoodDelta delta;
	@Getter private FieldOperators<Integer> op;

	public MatrixFactory(NeighberhoodDelta delta) {
		this(delta, delta.getTarget(), delta.getTarget().getModularNumber());
	}
	
	public MatrixFactory(NeighberhoodDelta delta, Graph graph, int state) {
		this(delta, graph, new Zn(state));
	}
	
	public MatrixFactory(NeighberhoodDelta delta, Graph graph, FieldOperators<Integer> op) {
		this.delta = delta;
		this.graph = graph;
		this.size = graph.getSize();
		this.op = op;
	}

	protected void initCoefficients(Matrix m) {
		graph.getVertexes().forEach(v1 -> {
			graph.getVertexes().forEach(v2 -> {
				m.setCoefficient(v1.getPositionIndex(), v2.getPositionIndex(), 
						delta.getDeltaValue(v1.getPosition(), v2.getPosition()));
			});
		});
	}

	protected void initConstants(Matrix m) {
		int[] constants = new int[graph.getSize()];
		graph.getVertexes().forEach(v->{
			constants[v.getPositionIndex()] = op.negate(v.getValue());
		});
		m.setContants(constants);
	}

	protected void initMatrix(Matrix m) {
		this.initCoefficients(m);
		this.initConstants(m);
	}
	
	public Matrix newInstance() {
		Matrix m = new Matrix(this.size, this.size, this.op);
		initMatrix(m);
		return m;
	}

}
