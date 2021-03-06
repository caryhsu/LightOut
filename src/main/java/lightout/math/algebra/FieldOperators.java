package lightout.math.algebra;
public interface FieldOperators<T> {

	/**
	 * 加法單位元素
	 * @return
	 */
	T zero();
	
	/**
	 * 乘法單位元素
	 * @return
	 */
	T one();

	/**
	 * 加法
	 * @param x
	 * @param y
	 * @return
	 */
	T add(T x, T y);

	/**
	 * 乘法
	 * @param x
	 * @param y
	 * @return
	 */
	T multiply(T x, T y);

	/**
	 * 加法反元素
	 * @param x
	 * @return
	 */
	T negate(T x);
	
	/**
	 *  乘法的相反數, 不存在 return null
	 */
	T reciprocal(T x);

	/**
	 * 減法 相當於加y的反元素
	 * @param x
	 * @param y
	 * @return
	 */
	default public T subtract(T x, T y) {
		return add(x, negate(y));
	}

	/**
	 * 除法 相當於乘以y的反元素
	 * @param x
	 * @param y
	 * @return
	 */
	default public T divide(T x, T y) {
		return multiply(x, reciprocal(y));
	}
	
	boolean equals(T x, T y);
	
	T productEqsY(T x, T y);
}