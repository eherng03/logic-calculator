
public class OperationCreator {
	
	private LogicCalculator calculator;
	
	public void introduceData(String operationName, String operationStructure, LogicCalculator calculator) throws InvalidStructureException{
		this.calculator = calculator;
	
		if(checkStructure(operationStructure)){
			//TODO nueva operación
			
		}else{
			throw new InvalidStructureException();
		}
	}

	/**This method check the operation structure that the user has introduced
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
		for(int i = 0; i < operationStructureParts.length; i++){
			if(A.equals(operationStructureParts[i])){
				//The user shouldn't write two following operators
				if(B.equals(operationStructureParts[i + 1]) || A.equals(operationStructureParts[i + 1])){
					return false;
				}
				operatorAExist = true;
			}else if(B.equals(operationStructureParts[i])){
				//The user couldn't write two following operators
				if(B.equals(operationStructureParts[i + 1]) || A.equals(operationStructureParts[i + 1])){
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

}
