package lightout.field;
public interface FieldOperators<T> {
	
	T zero();
	
	T one();
	
	T add(T x, T y);
	
	T multiply(T x, T y);
	
	T negate(T x);
	
	/**
	 *  乘法的相反數, 不存在 return null
	 */
	T reciprocal(T x);
	
	default public T subtract(T x, T y) {
		return add(x, negate(y));
	}
	
	default public T divide(T x, T y) throws IllegalArgumentException {
		return multiply(x, reciprocal(y));
	}
	
	boolean equals(T x, T y);
	
	T productEqsY(T x, T y);
}