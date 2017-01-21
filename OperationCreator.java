
public class OperationCreator {
	
	private LogicCalculator calculator;
	

	public void create(String operationName, String operationStructure, LogicCalculator calculator) throws InvalidStructureException{
		this.calculator = calculator;
	
		if(checkOperation(operationName, operationStructure)){
			createOperation(operationName, operationStructure);
		}else{
			throw new InvalidStructureException();
		}
	}
	/**Check the operation name and the operation structure
	 * 
	 * @param operationName
	 * @param operationStructure
	 * @return true if the operation is valid and false if it is invalid
	 */
	private boolean checkOperation(String operationName, String operationStructure) {
		return checkName(operationName) && checkStructure(operationStructure);
	}
	
	/**Check that the introduced name is not a repeated name
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

	/**Check the operation structure that the user has introduced
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
	
	/**This method create a new operation with the name and structure that the user has introduced.
	 * 
	 * @param operationName
	 * @param operationStructure
	 */
	private void createOperation(String operationName, String operationStructure) {
		// TODO Auto-generated method stub
		
	}

}
