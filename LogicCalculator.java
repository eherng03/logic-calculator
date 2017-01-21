import java.util.ArrayList;

import javax.swing.JComboBox;

public class LogicCalculator {
	
	private ArrayList<String> operations;
	
	LogicCalculator(){
		operations = new ArrayList<String>();
		operations.add("NOT");
		operations.add("OR");
	}
	
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
	
	public ArrayList<String> getOperations(){
		return operations;
	}
	
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
	
	private int orOperator(int a, int b){
		boolean result = toBoolean(a) || toBoolean(b);
		if(result){
			return 1;
		}else{
			return 0;
		}
	}
	
	private boolean toBoolean(int x){
		return x == 1;
	}

}
