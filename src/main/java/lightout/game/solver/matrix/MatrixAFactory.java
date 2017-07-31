package lightout.game.solver.matrix;

import lightout.field.Matrix;

public interface MatrixAFactory {
	
	void init(Matrix<Integer> A);

	Matrix<Integer> newInstance();

}
