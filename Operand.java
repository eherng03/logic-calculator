
public class Operand implements Deniable{

	private int value;
	
	public Operand(){
		value = -1;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean getBooleanValue(){
		return value == 1;
	}
	
	@Override
	public void deny() {
		if(value == 1){
			setValue(0);
		}else{
			setValue(1);
		}
	}

}
