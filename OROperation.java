
public class OROperation extends Operation{

	public OROperation() {
		name = "OR";
	}
	
	/**
	 * Get the result of the OR logic operation
	 * @param a
	 * @param b
	 * @return
	 */
	
	public static int operate(int a, int b){
		boolean result = toBoolean(a) || toBoolean(b);
		if(result){
			return 1;
		}else{
			return 0;
		}
	}
}
