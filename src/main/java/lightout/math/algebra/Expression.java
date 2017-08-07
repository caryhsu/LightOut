package lightout.math.algebra;

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
	
	public Expression<T> setInitial(int initial) {
		return this.setInitial(op.convertFromInt(initial));
	}
	
	public Expression<T> setInitial(T initial) {
		this.functions.add(T->{return initial;});
		return this;
	}

	public Expression<T> add(int x) {
		return this.add(op.convertFromInt(x));
	}
	
	public Expression<T> add(T x) {
		this.functions.add(T->{return op.add(target, x);});
		return this;
	}

	public Expression<T> multiply(int x) {
		return this.multiply(op.convertFromInt(x));
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
	
	public Expression<T> subtract(int x) {
		return this.subtract(op.convertFromInt(x));
	}
	
	public Expression<T> subtract(T x) {
		this.functions.add(T->{return op.subtract(target, x);});
		return this;
	}
	
	public Expression<T> divide(int x) {
		return this.divide(op.convertFromInt(x));
	}
	
	public Expression<T> divide(T x) {
		this.functions.add(T->{return op.divide(target, x);});
		return this;
	}

	public T result() {
		this.functions.forEach(f->{this.target = f.apply(this.target);});
		return this.target;
	}
}
