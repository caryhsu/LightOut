package lightout.math.algebra;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import lightout.math.algebra.Expression;
import lightout.math.algebra.Zn;

public class ExpressionTest {

	@Test
	public void test1() {
		Integer value = new Expression<Integer>()
				.setOp(new Zn(100))
				.setInitial(10)
				.add(3)
				.subtract(5)
				.multiply(4)
				.result();
		assertThat(value, equalTo(32));
	}
}
