import javax.swing.JOptionPane;

/**
 * This exception is throwed if an user write an invalid operation
 * 
 * @author Alba, Eva and Héctor
 *
 */
public class InvalidStructureException extends Exception {
	
	@Override
	public String getMessage(){
		return "The operation structure is invalid";
	}
	
}
