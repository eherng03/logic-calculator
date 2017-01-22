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
