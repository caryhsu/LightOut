package lightout.math;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import lightout.math.algebra.AlgebraTestSuite;
import lightout.math.matrix.MatrixSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AlgebraTestSuite.class,
	MatrixSuite.class,
})
public class MathTestSuite {
  //nothing
}