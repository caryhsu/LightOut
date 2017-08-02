package lightout.field;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

public class Test1 {

	@Test
	public void test1() {
		List<String> lists = Arrays.asList("aa", "bb", "cc");
		Consumer<String> action = this::print;
		lists.forEach(action);
	}
	
	public void print(String str) {
		System.out.println("str=" + str);
	}
}
