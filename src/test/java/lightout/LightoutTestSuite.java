package lightout;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import lightout.graph.GraphTestSuite;
import lightout.math.MathTestSuite;
import lightout.math.matrix.MatrixSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MathTestSuite.class,
	GraphTestSuite.class,
})
public class LightoutTestSuite {
  //nothing
}