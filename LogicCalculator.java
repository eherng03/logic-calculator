package INCO;
import java.util.ArrayList;

/**
 * The logic calculator. It has got its operations and it can operate.
 * 
 * @author Alba, Eva y Hector.
 *
 */

public class LogicCalculator {
	private ArrayList<Operation> operations; 		//The existing operations
	
	LogicCalculator(){
		operations = new ArrayList<Operation>();
		operations.add(new OROperation());
	}
	
	/**
     * Returns the arraylist of the available operations implemented.
     * @return operations
     */
	public ArrayList<Operation> getOperations() {
		return operations;
	}
	
	 /**
	  * Add a new operation to the operations attribute.
	  * 
	  * @param operation
	  */
	public void addOperation(Operation operation){
		operations.add(operation);
	}
	
	/**
     * Returns true if the string is an existing operation in the arraylist
     * @return true
     */
	public boolean containOperation(String operation){
		for(int j = 0; j < operations.size(); j++){
			if(operations.get(j).getName().equals(operation)){
				return true;
			}
		}
		return false;
	}

	/**
	  * Get the result of the operation.
	  * 
	  * @param aValue
	  * @param operationValue
	  * @param bValue
	  * @return result
	  */
	public Operand operate(Object aValue, Object operationValue, Object bValue) {
		Operand a = new Operand();
		Operand b = new Operand();
		a.setValue((int)aValue);
		b.setValue((int)bValue);
		
		String operationName = (String) operationValue;
		
		if(!operationName.equals(" ")){
			for(Operation operation : operations){
				if(operationName.equals(operation.getName())){
					return operation.operate(a, b);
				}
			}
		}
		return null;
	}
}
