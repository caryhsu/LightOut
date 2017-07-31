package lightout.game.solver.matrix.matrixa;

import lightout.field.Matrix;

public interface MatrixAFactory {
	
	void init(Matrix<Integer> A);

	Matrix<Integer> newInstance();

}
