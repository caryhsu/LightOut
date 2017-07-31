package lightout.game.solver.matrix;

import lightout.field.Matrix;
import lightout.field.Zn;
import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.delta.NeighberhoodDelta;
import lombok.Getter;

public class MatrixAFactoryImpl1 implements MatrixAFactory {

	@Getter private int boardRow;
	@Getter private int boardCol;
	@Getter private int ASize;
	@Getter private int state;
	@Getter private NeighberhoodDelta delta;

	public MatrixAFactoryImpl1(NeighberhoodDelta delta) {
		this.delta = delta;
		Array2DGraph graph = (Array2DGraph) delta.getTarget();
		this.boardRow = graph.getWidth();
		this.boardCol = graph.getHeight();
		this.ASize = this.boardRow * this.boardCol;
		this.state = graph.getModularNumber();
	}
	
	@Override
	public void init(Matrix<Integer> A) {
		for (int Arow = 0; Arow < ASize; Arow++) {
			for (int Acol = 0; Acol < ASize; Acol++) {
				int i, j, i_, j_ = 0;
				i = Arow / this.boardCol; // index (i, j) is the tile that you're setting the equation up for
				j = Arow % this.boardCol;
				i_ = Acol / this.boardCol; // index (i_, j_) is the index of where you are pressing
				j_ = Acol % this.boardCol;
				Array2DPosition p1 = new Array2DPosition(i, j);
				Array2DPosition p2 = new Array2DPosition(i_, j_);
				A.set(Arow, Acol, delta.getDeltaValue(p1, p2));
			}
		}
	}

	@Override
	public Matrix<Integer> newInstance() {
		Matrix<Integer> A = new Matrix<Integer>(ASize, ASize, new Zn(this.state));
		init(A);
		return A;
	}

}
