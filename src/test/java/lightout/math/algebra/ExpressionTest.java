package lightout.math.algebra;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExpressionTest {

	@Test
	public void test1() {
		Zn zn = new Zn(100);
		ZnElement value = new Expression<ZnElement>()
				.setOp(zn)
				.setInitial(10)
				.add(3)
				.subtract(5)
				.multiply(4)
				.result();
		assertThat(value, equalTo(zn.of(32)));
	}
}
