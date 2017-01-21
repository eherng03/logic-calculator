
public class Operation{
	protected static String name;
	
	public static String getName(){
		return name;
	}
	
	/**
	 * Get the result of the logic operation
	 * @param a
	 * @param b
	 * @return result
	 */
	public static int operate(int a, int b){
		return 0;
	}
	
	/**
	 * Get a true value if the parameter is one and a false value if it is 0
	 * @param x
	 * @return result
	 */
	protected static boolean toBoolean(int x){
		return x == 1;
	}
}
