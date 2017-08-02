package lightout.field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import lombok.Getter;

public class Expression<T> {

	private T target;
	@Getter private FieldOperators<T> op;
	@Getter private List<Function<T, T>> functions = new ArrayList<>();
		
	public Expression<T> setOp(FieldOperators<T> op) {
		this.op = op;
		return this;
	}
	
	public Expression<T> setInitial(T initial) {
		this.functions.add(T->{return initial;});
		return this;
	}

	public Expression<T> add(T x) {
		this.functions.add(T->{return op.add(target, x);});
		return this;
	}

	public Expression<T> multiply(T x) {
		this.functions.add(T->{return op.multiply(target, x);});
		return this;
	}

	public Expression<T> negate() {
		this.functions.add(T->{return op.negate(target);});
		return this;
	}
	
	public Expression<T> reciprocal() {
		this.functions.add(T->{return op.reciprocal(target);});
		return this;
	}
	
	public Expression<T> subtract(T x) {
		this.functions.add(T->{return op.subtract(target, x);});
		return this;
	}
	
	public Expression<T> divide(T x) {
		this.functions.add(T->{return op.divide(target, x);});
		return this;
	}

	public Expression<T> productEqsY(T x) {
		this.functions.add(T->{return op.productEqsY(target, x);});
		return this;
	}
	
	public T result() {
		this.functions.forEach(f->{this.target = f.apply(this.target);});
		return this.target;
	}
}
