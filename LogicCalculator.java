import java.util.ArrayList;

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
	 * Get the result of the operation
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
		
		switch(operationName){
			case " ":
				javax.swing.JOptionPane.showMessageDialog(null, "Choose an operation", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
			case "OR":
				OROperation operation = new OROperation();
				return operation.operate(a, b);
			
		//TODO add operations
		}
		return null;
	}
}
