import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Create new Class user operations.
 * 
 * @author Alba, Eva y Hector
 *
 */
public class OperationCreator {
	private LogicCalculator calculator;
	
	/**
	 * Check and create the new operation and add it to the calculator.
	 * 
	 * @param operationName.
	 * @param operationStructure.
	 * @param calculator.
	 * @throws InvalidNameException. 
	 * @throws InvalidStructureException.
	 * 
	 */
	public void createOperation(String operationName, String operationStructure, LogicCalculator calculator) throws InvalidStructureException, InvalidNameException{
		this.calculator = calculator;
		
		if(checkOperation(operationName, operationStructure)){
			String[] operationStructureParts = operationStructure.split(" ");
			ArrayList<String> lista = toPostfixExpression(operationStructureParts);
			
			File newClassFile = new File("./" + operationName + "Operation.java");
		}
	}

	
	/**
	 * Check the operation name and the operation structure.
	 * 
	 * @param operationName.
	 * @param operationStructure.
	 * @return true if the operation is valid and false if it is invalid.
	 * @throws InvalidStructureException.
	 * @throws InvalidNameException.
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
	 * Check that the introduced name is not a repeated name.
	 * 
	 * @param operationName.
	 * @return true if the name is a valid name or false if it is invalid.
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
	 * Check the operation structure that the user has introduced.
	 * 
	 * @param operationStructure.
	 * @return true if the structure is valid and false if it is invalid.
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
				return calculator.containOperation(operationStructureParts[i]);
			}
		}
		return operatorAExist && operatorBExist && validOperation && checkParentheses.isEmpty();
	}

	
	
	private void createClass(){
		
	}
	
    /**
     * Translate an infix expression into a postfix expression.
     *
     * @param operationInfixStructureParts an array with the infix expression.
     * @return The list with the postfix expression.
     */
	private ArrayList<String> toPostfixExpression(String[] operationInfixStructureParts){
		// We used this algorithm    --->    http://faculty.cs.niu.edu/~hutchins/csci241/eval.htm
		//https://github.com/mobgen/halo-android/blob/develop/sdk-libs/halo-content/src/main/java/com/mobgen/halo/android/content/models/SearchSyntax.java#L386
		Deque<String> stack = new ArrayDeque<>();
		ArrayList<String> postfixExpression = new ArrayList<>();
		for(int i = 0; i < operationInfixStructureParts.length; i++){
			if(operationInfixStructureParts[i].equals("A") || operationInfixStructureParts[i].equals("B")){
				postfixExpression.add(operationInfixStructureParts[i]);
			}else if(operationInfixStructureParts[i].equals("(")){
				stack.add(operationInfixStructureParts[i]);
			}else if(operationInfixStructureParts[i].equals(")")){
				while(!stack.isEmpty() && (!stack.peek().equals("("))){
					postfixExpression.add(stack.pop());
				}
				if(!stack.isEmpty()){
					stack.pop();
				}else{
					//Error
				}
			}else if(calculator.containOperation(operationInfixStructureParts[i])){
				if(stack.isEmpty() || stack.peek().equals("(")){
					stack.push(operationInfixStructureParts[i]);
				}else{
					while(!stack.isEmpty() && !stack.peek().equals("(")){
						postfixExpression.add(stack.pop());
					}
					stack.push(operationInfixStructureParts[i]);
				}
			}else if(operationInfixStructureParts[i].equals("NOT")){
				//TODO
			}
		}
		//pa sacar lo que queda en la pila
		while(!stack.isEmpty()){
			String expresion = stack.pop();
			if(expresion.equals("(") || expresion.equals(")")){
				//Error
			}
			postfixExpression.add(expresion);
		}
		return postfixExpression;
		
	}
	
	
}
