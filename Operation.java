
public interface Operation{
	
	public String getName();
	
	/**
	 * Get the result of the logic operation
	 * @param a
	 * @param b
	 * @return result
	 */
	public Operand operate(Operand a, Operand b);
	
	/**
	 * Get a true value if the parameter is one and a false value if it is 0
	 * @param x
	 * @return result
	 */
}
