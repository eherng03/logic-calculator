import javax.swing.JOptionPane;

public class InvalidStructureException extends Exception {
	
	@Override
	public String getMessage(){
		return "The operation structure is invalid";
	}
	
}
