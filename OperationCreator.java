import java.io.File;
import java.util.ArrayList;

public class OperationCreator {
	private LogicCalculator calculator;
	
	/**
	 * Check and create the new operation and add it to the calculator
	 * 
	 * @param operationName
	 * @param operationStructure
	 * @param calculator
	 * @throws InvalidNameException 
	 * @throws InvalidStructureException
	 * 
	 */
	public void create(String operationName, String operationStructure, LogicCalculator calculator) throws InvalidStructureException, InvalidNameException{
		this.calculator = calculator;
		
		if(checkOperation(operationName, operationStructure)){
			String[] operationStructureParts = operationStructure.split(" ");
			
			
			File newClassFile = new File("./" + operationName + "Operation.java");
		}
	}

	
	/**
	 * Check the operation name and the operation structure
	 * 
	 * @param operationName
	 * @param operationStructure
	 * @return true if the operation is valid and false if it is invalid
	 * @throws InvalidStructureException 
	 * @throws InvalidNameException 
	 */
	private boolean checkOperation(String operationName, String operationStructure) throws InvalidStructureException, InvalidNameException {
		boolean validName = checkName(operationName);
		boolean validStructure = checkStructure(operationStructure);
		
		if(!validName){
			throw new InvalidNameException();
		}
		if(!validStructure){
			throw new InvalidStructureException();
		}
		
		return validName && validStructure;
	}
	
	
	/**
	 * Check that the introduced name is not a repeated name
	 * 
	 * @param operationName
	 * @return true if the name is a valid name or false if it is invalid
	 */
	private boolean checkName(String operationName){
		for(int i = 0; i < calculator.getOperations().size(); i++){
			if(calculator.getOperations().get(i).getName().equals(operationName)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check the operation structure that the user has introduced
	 * 
	 * @param operationStructure
	 * @return true if the structure is valid and false if it is invalid
	 */
	private boolean checkStructure(String operationStructure) {
		String[] operationStructureParts = operationStructure.split(" ");
		boolean operatorAExist = false;
		boolean operatorBExist = false;
		boolean validOperation = false;
		final String A = "A";
		final String B = "B";
		final String NOT = "NOT";
		final String openParentheses = "(";
		final String closeParentheses = ")";
		
		ArrayList<String> checkParentheses = new ArrayList<String>();
		
		for(int i = 0; i < operationStructureParts.length; i++){
			if(A.equals(operationStructureParts[i])){
				operatorAExist = true;
				//The user shouldn't write two following operators or a NOT following an operator
				if(B.equals(operationStructureParts[i + 1]) || A.equals(operationStructureParts[i + 1]) || NOT.equals(operationStructureParts[i + 1])){
					return false;
				}
			}else if(B.equals(operationStructureParts[i])){
				operatorBExist = true;
				//The user couldn't write two following operators or a NOT following an operator
				if(B.equals(operationStructureParts[i + 1]) || A.equals(operationStructureParts[i + 1]) || NOT.equals(operationStructureParts[i + 1])){
					return false;
				}
			//Check if an operator follows the not operation
			}else if(NOT.equals(operationStructureParts[i])){
				for(int j = 0; j < calculator.getOperations().size(); j++){
					if(calculator.getOperations().get(j).getName().equals(operationStructureParts[i + 1])){
						return false;
					}
				}
				
			//Check parentheses
			}else if(openParentheses.equals(operationStructureParts[i])){
				checkParentheses.add("open");
			}else if(closeParentheses.equals(operationStructureParts[i])){
				if(!checkParentheses.isEmpty()){
					checkParentheses.remove(checkParentheses.size() - 1);
				}else{
					return false;
				}
			//Check if the operation exists
			}else{
				for(int j = 0; j < calculator.getOperations().size(); j++){
					if(calculator.getOperations().get(j).getName().equals(operationStructureParts[i])){
						validOperation = true;
						break;
					}
				}
				if(!validOperation){
					return false;
				}
			}
		}
		return operatorAExist && operatorBExist && validOperation && checkParentheses.isEmpty();
	}

}
