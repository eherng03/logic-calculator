package INCO;

/**
 * This exception is throwed when an user write an invalid operation.
 * 
 * @author Alba, Eva and Hector
 */
@SuppressWarnings("serial")
public class InvalidStructureException extends Exception {
	
	@Override
	public String getMessage(){
		return "The operation structure is invalid.";
	}
	
}
