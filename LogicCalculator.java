import java.util.ArrayList;

import javax.swing.JComboBox;

public class LogicCalculator {
	
	private ArrayList<String> operations; 		//The existing operations
	
	LogicCalculator(){
		operations = new ArrayList<String>();
		operations.add(NOTOperation.getName());
		operations.add(OROperation.getName());
	}
	
    /**
     * Returns the arraylist of the available operations implemented.
     * @return operations
     */
	public ArrayList<String> getOperations(){
		return operations;
	}
	
	/**
	 * Get the result of the operation
	 * 
	 * @param notAValue
	 * @param aValue
	 * @param operationValue
	 * @param notBValue
	 * @param bValue
	 * @return result
	 */
	public int operate(Object notAValue, Object aValue, Object operationValue, Object notBValue, Object bValue) {
		// cast parameters
		int a = (int) aValue;
		int b = (int) bValue;
		if(notAValue.equals("NOT")){
			a = NOTOperation.operate(a, 0);
		}
		if(notBValue.equals("NOT")){
			b = NOTOperation.operate(b, 0);
		}
		String notA = (String) notAValue;
		String notB = (String) notBValue;
		String operationName = (String) operationValue;
		Operation operation;
		//Choose operation and operate
		switch(operationName){
			case " ":
				javax.swing.JOptionPane.showMessageDialog(null, "Choose an operation", "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
			case "OR":
				return OROperation.operate(a, b);
				
			//TODO add operations
		}
		
		return -1;
	}
}
