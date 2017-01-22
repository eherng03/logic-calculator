import javax.swing.JOptionPane;

/**
 * This exception is throwed when an user write an invalid operation.
 * 
 * @author Alba, Eva and Hector
 */
public class InvalidStructureException extends Exception {
	
	@Override
	public String getMessage(){
		return "The operation structure is invalid.";
	}
	
}
