import java.util.ArrayList;

import javax.swing.JComboBox;

public class LogicCalculator {
	
	private ArrayList<String> operations; 		//The existing operations
	
	LogicCalculator(){
		operations = new ArrayList<String>();
		operations.add("NOT");
		operations.add("OR");
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
		String notA = (String) notAValue;
		String notB = (String) notBValue;
		String operation = (String) operationValue;
		a = notOperator(notA, a);
		b = notOperator(notB, b);
		
		//Choose operation and operate
		switch(operation){
			case "OR":
				return orOperator(a, b);
		}
		
		return -1;
	}
	
	/**
	 * @return The existing operations
	 */
	public ArrayList<String> getOperations(){
		return operations;
	}
	
	/**
	 * Get the result of the NOT logic operation
	 * @param operator
	 * @param a
	 * @return result
	 */
	private int notOperator(String operator, int a){
		if(operator.equals("NOT")){
			if(a == 0){
				return 1;
			}else{
				return 0;
			}
		}else{
			return a;
		}
	}
	
	/**
	 * Get the result of the OR logic operation
	 * @param a
	 * @param b
	 * @return
	 */
	private int orOperator(int a, int b){
		boolean result = toBoolean(a) || toBoolean(b);
		if(result){
			return 1;
		}else{
			return 0;
		}
	}
	/**
	 * Get a true value if the parameter is one and a false value if it is 0
	 * @param x
	 * @return result
	 */
	private boolean toBoolean(int x){
		return x == 1;
	}

}
