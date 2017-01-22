/**
 * This interface indicate that a class is an operation, it has a name and it can operate.
 * 
 * @author Alba, Eva y Hector.
 *
 */
public interface Operation{
	
	/**
	 * Get the name attribute of the operation.
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * Get the result of the logic operation.
	 * @param a
	 * @param b
	 * @return result
	 */
	public Operand operate(Operand a, Operand b);
	
}
