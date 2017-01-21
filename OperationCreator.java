import java.io.File;

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
			StringBuilder function = new StringBuilder();
			function.append("public class " + operationName + "Operation extends Operation{ public )"
					+ operationName + "Operation(){ name = \"" + operationName + "\";} public static int operate(int a, int b){");
			for(int i = 0; i < operationStructureParts.length; i++){
				if(operationStructureParts[i] == "NOT"){
					//mirar todo lo de los partentesis
					//todas las operaciones a añadir etc etc
					//TODO
				}
			}
		
			function.append("} }");
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
		if(checkName(operationName)){
			throw new InvalidStructureException();
		}
		if(checkStructure(operationStructure)){
			throw new InvalidNameException();
		}
		return checkName(operationName) && checkStructure(operationStructure);
	}
	
	/**
	 * Check that the introduced name is not a repeated name
	 * 
	 * @param operationName
	 * @return true if the name is a valid name or false if it is invalid
	 */
	private boolean checkName(String operationName){
		if(calculator.getOperations().contains(operationName)){
			return false;
		}else{
			return true;
		}
		
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
		for(int i = 0; i < operationStructureParts.length; i++){
			if(A.equals(operationStructureParts[i])){
				//The user shouldn't write two following operators or a NOT following an operator
				if(B.equals(operationStructureParts[i + 1]) || A.equals(operationStructureParts[i + 1]) || NOT.equals(operationStructureParts[i + 1])){
					return false;
				}
				operatorAExist = true;
			}else if(B.equals(operationStructureParts[i])){
				//The user couldn't write two following operators or a NOT following an operator
				if(B.equals(operationStructureParts[i + 1]) || A.equals(operationStructureParts[i + 1]) || NOT.equals(operationStructureParts[i + 1])){
					return false;
				}
				operatorBExist = true;
			//Check if an operator follows the not operation
			}else if(NOT.equals(operationStructureParts[i]) && (!B.equals(operationStructureParts[i + 1]) || !A.equals(operationStructureParts[i + 1]))){
				return false;
			//Check if the operation exists
			}else if(calculator.getOperations().contains(operationStructureParts[i])){
				validOperation = true;
			}else{
				return false;
			}
		}
		
		//The user should write at least two operators and a operation
		return operatorAExist && operatorBExist && validOperation;
	}
	
}
