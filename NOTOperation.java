
public class NOTOperation extends Operation{
	
	private static NOTOperation notOperation;
	
	private NOTOperation(){
		name = "NOT";
	}
	

	/**
	 * Get the result of the NOT logic operation
	 * @param operator
	 * @param a
	 * @return result
	 */
	public static int operate(int a, int b){
		if(a == 0){
			return 1;
		}else{
			return 0;
		}
	}
	
}
