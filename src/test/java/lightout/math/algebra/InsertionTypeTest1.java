package lightout.math.algebra;

import java.util.Optional;

import org.junit.Test;

public class InsertionTypeTest1 {

	@Test
	public void test1() {
		Zoo zoo = new Zoo() {
			public void zoo() {
				System.out.println("zoo");
			};
//			public void foo() {
//				System.out.println("foo");
//			};
		};
		zoo.zoo();
		Foo foo = (Foo & Zoo) zoo;
		foo.foo();
	}

	public interface Zoo {
		void zoo();
	}
	
	public interface Foo {
		void foo();
	}
}
