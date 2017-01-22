
public class InvalidNameException extends Exception {
	
	@Override
	public String getMessage(){
		return "The operation name already exist.";
	}

}
