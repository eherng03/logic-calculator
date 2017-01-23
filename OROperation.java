package INCO;

public class OROperation implements Operation{
	
	private String name;
	public OROperation() {
		name = "OR";
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Get the result of the OR logic operation
	 * @param a
	 * @param b
	 * @return a OR b
	 */
	@Override
	public Operand operate(Operand a, Operand b){
		Operand result = new Operand();
		if(a.getBooleanValue() || b.getBooleanValue()){
			result.setValue(1);
		}else{
			result.setValue(0);
		}
		return result;
	}
}
