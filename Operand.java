package INCO;
/**
 * The operand involved in an operation. It has its value and implements deniable .
 * 
 * @author Alba, Eva y Hector
 *
 */
public class Operand {

	
	private int value;
	
	public Operand(){
		value = -1;
	}
	
	/**
	 * Set the value of the attribute value.
	 * 
	 * @param value
	 */
	public void setValue(int value){
		this.value = value;
	}
	
	/**
	 * get the value of the atribute value.
	 * 
	 * @param value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * This method get the boolean value of the attribute value.
	 * 
	 * @return true if the value is 1 or false if it is not.
	 */
	public boolean getBooleanValue(){
		return value == 1;
	}
	
	/**
	 * Deny the value attribute. If the value is 1, when it is denied, it becomes to 0, else if the value is 0, when it
	 * is denied it becomes to 1.
	 */
	public void deny() {
		if(value == 1){
			setValue(0);
		}else if (value == 0){
			setValue(1);
		}
	}

}
