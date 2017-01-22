/**
 * This exception is throwed when an user introduce a name that already exists.
 * 
 * @author Alba, Eva y Hector.
 *
 */
public class InvalidNameException extends Exception {
	
	@Override
	public String getMessage(){
		return "The operation name already exist.";
	}

}
