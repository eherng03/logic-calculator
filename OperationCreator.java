package INCO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

import com.squareup.javapoet.*;
import javax.lang.model.element.Modifier;
/**
 * Create new Class user operations.
 * 
 * @author Alba, Eva y Hector
 *
 */
@SuppressWarnings("unused")
public class OperationCreator {
	private LogicCalculator calculator;
	private MainWindow mainWindow;
	private final String A = "A";
	private final String B = "B";
	private final String NOT = "NOT";
	private final String OPENPARENTHESIS = "(";
	private final String CLOSEPARENTHESIS = ")";
	
	/**
	 * Check and create the new operation and add it to the calculator.
	 * @param mainWindow 
	 * 
	 * @param operationName.
	 * @param operationStructure.
	 * @param calculator.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvalidNameException. 
	 * @throws InvalidStructureException.
	 * 
	 */
	public void createOperation(String operationName, String operationStructure, LogicCalculator calculator, MainWindow mainWindow) throws InvalidStructureException, InvalidNameException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		this.calculator = calculator;
		this.mainWindow = mainWindow;
		
		if(checkOperation(operationName, operationStructure)){
			String[] operationStructureParts = operationStructure.split(" ");
			ArrayList<String> postfixExpression = toPostfixExpression(operationStructureParts);
			createClass(operationName, postfixExpression);
			
			JCompiler.compile(operationName + "Operation");
			
			
			
			Class<?> newClass = OperationCreator.class.getClassLoader().loadClass("INCO." + operationName + "Operation");
			Operation operation = (Operation) newClass.newInstance();
			
			calculator.addOperation(operation);
			 
			mainWindow.repaint();
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
		int countOperand = 0;
		int countOperation = 0;
		boolean validOperation = false;

		
		ArrayList<String> checkParentheses = new ArrayList<String>();
		
		for(int i = 0; i < operationStructureParts.length; i++){
			if(A.equals(operationStructureParts[i])){
				countOperand++;
				//The user shouldn't write two following operators or a NOT following an operator
				if(i < operationStructureParts.length - 1){
					if(B.equals(operationStructureParts[i + 1]) || A.equals(operationStructureParts[i + 1]) || NOT.equals(operationStructureParts[i + 1])){
						return false;
					}
				}
			}else if(B.equals(operationStructureParts[i])){
				countOperand++;
				//The user couldn't write two following operators or a NOT following an operator
				if(i < operationStructureParts.length - 1){
					if(B.equals(operationStructureParts[i + 1]) || A.equals(operationStructureParts[i + 1]) || NOT.equals(operationStructureParts[i + 1])){
						return false;
					}
				}
			}else if(NOT.equals(operationStructureParts[i])){
				//It can´t be possible to have a NOT at the end of the expression, or a NOT followed by a ")"
				if(i == operationStructureParts.length - 1 || CLOSEPARENTHESIS.equals(operationStructureParts[i + 1])){
					return false;
				}
				//Check if an operator follows the not operation
				for(int j = 0; j < calculator.getOperations().size(); j++){
					if(i < operationStructureParts.length - 1){
						if(calculator.getOperations().get(j).getName().equals(operationStructureParts[i + 1])){
							return false;
						}
					}
				}
			//Check parentheses
			}else if(OPENPARENTHESIS.equals(operationStructureParts[i])){
				checkParentheses.add("open");
			}else if(CLOSEPARENTHESIS.equals(operationStructureParts[i])){
				if(!checkParentheses.isEmpty()){
					checkParentheses.remove(checkParentheses.size() - 1);
				}else{
					return false;
				}
			//Check if the operation exists
			}else{
				if(calculator.containOperation(operationStructureParts[i])){
					countOperation++;
				}else{
					return false;
				}
			}
		}
		
		if(countOperand != countOperation + 1){
			validOperation = false;
		}else{
			validOperation = true;
		}
		
		return  validOperation && checkParentheses.isEmpty();
	}

	
	private String[] createBodyMethod(ArrayList<String> postfixExpression) throws InvalidStructureException{

		StringBuilder operateBody = new StringBuilder();
		int countOperation = 0;
		Stack<String> operationsStack = new Stack<String>();
		for(int i = 0; i < postfixExpression.size(); i++){
			if(postfixExpression.get(i).equals(A)){
				operationsStack.push("a");
				
			}else if(postfixExpression.get(i).equals(B)){
				operationsStack.push("b");
				
			}else if(NOT.equals(postfixExpression.get(i))){
				operateBody.append(operationsStack.peek() + ".deny()");
				if(i < postfixExpression.size() - 1){
					operateBody.append(";\n");
				}
				
			}else{
				operateBody.append(postfixExpression.get(i) + "Operation operation" + countOperation + " = new " + postfixExpression.get(i) + "Operation();\n");
				operateBody.append("Operand result" + countOperation + " = operation" + countOperation + ".operate(" + operationsStack.pop() +" ," + operationsStack.pop() + ")");
				if(i < postfixExpression.size() - 1){
					operateBody.append(";\n");
				}
				operationsStack.push("result" + countOperation);
				countOperation++;
			}
			
			
		}
		if(operationsStack.size() != 1){
			throw new InvalidStructureException();
		}
		return new String[] {operateBody.toString(), operationsStack.pop()};
	}
	
	/**
	 * Create the new operation class file
	 * @param operationName
	 * @param postfixExpression
	 * @throws IOException 
	 * @throws InvalidStructureException 
	 */
	private void createClass(String operationName, ArrayList<String> postfixExpression) throws IOException, InvalidStructureException {
		
		
		//Constructor
		MethodSpec constructor = MethodSpec.constructorBuilder()
			    .addModifiers(Modifier.PUBLIC)
			    .addStatement("this.$N = $N", "name", "\"" + operationName + "\"")
			    .build();
		//getName()
		MethodSpec getName = MethodSpec.methodBuilder("getName")
			    .addModifiers(Modifier.PUBLIC)
			    .returns(String.class)
			    .addStatement("return $N", "name")
			    .build();
		
		
		String[] bodyAndReturnName = createBodyMethod(postfixExpression);
		String body = bodyAndReturnName[0];
		String resultName = bodyAndReturnName[1];
	
		MethodSpec operate = MethodSpec.methodBuilder("operate")
				.addModifiers(Modifier.PUBLIC)
				.addParameter(Operand.class, "a")
				.addParameter(Operand.class, "b")
				.addStatement("Operand result = new Operand()")
				.addStatement(body)
				.returns(Operand.class)
				.addStatement("return $N",  resultName)
				.build();
			
		TypeSpec newClass = TypeSpec.classBuilder(operationName + "Operation")
				.addField(String.class, "name", Modifier.PRIVATE)
			    .addModifiers(Modifier.PUBLIC)
			    .addSuperinterface(Operation.class)  
			    .addMethod(constructor)
			    .addMethod(getName)
			    .addMethod(operate)
			    .build();
		
		JavaFile javaFile = JavaFile.builder("INCO", newClass)
			    .build();
		
		javaFile.writeTo(Paths.get("./src"));
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
			if(operationInfixStructureParts[i].equals(A) || operationInfixStructureParts[i].equals(B)){
				postfixExpression.add(operationInfixStructureParts[i]);
			}else if(operationInfixStructureParts[i].equals(OPENPARENTHESIS)){
				stack.add(operationInfixStructureParts[i]);
			}else if(operationInfixStructureParts[i].equals(CLOSEPARENTHESIS)){
				while(!stack.isEmpty() && !stack.peek().equals(OPENPARENTHESIS)){
					postfixExpression.add(stack.pop());
				}
				if(!stack.isEmpty()){
					stack.pop();
				}else{
					//Error
				}
			}else if(calculator.containOperation(operationInfixStructureParts[i])){
				if(stack.isEmpty() || stack.peek().equals(OPENPARENTHESIS)){
					stack.push(operationInfixStructureParts[i]);
				}else{
					while(!stack.isEmpty() && !stack.peek().equals(OPENPARENTHESIS) && !stack.peek().equals(NOT)){
						postfixExpression.add(stack.pop());
					}
					stack.push(operationInfixStructureParts[i]);
				}
			}else if(operationInfixStructureParts[i].equals(NOT)){
				//We can do this because, previously we have checked that after a not always there are a letter or a parenthesis
				if(operationInfixStructureParts[i + 1].equals(A) || operationInfixStructureParts[i + 1].equals(B)){
					postfixExpression.add(operationInfixStructureParts[i + 1]);
					postfixExpression.add(operationInfixStructureParts[i]);
					i++;
				}else if(operationInfixStructureParts[i + 1].equals(OPENPARENTHESIS)){		
					stack.add(operationInfixStructureParts[i]);
					i++;
				}
			}
		}
		//pa sacar lo que queda en la pila
		while(!stack.isEmpty()){
			String expresion = stack.pop();
			if(expresion.equals(OPENPARENTHESIS) || expresion.equals(CLOSEPARENTHESIS)){
				//Error
			}
			postfixExpression.add(expresion);
		}
		return postfixExpression;
		
	}
	
	
}
