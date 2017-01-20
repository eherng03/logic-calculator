public class LogicCalculator {
	public int notOperator(int a){
		if(a == 0){
			return 1;
		}else{
			return 0;
		}
	}
	
	public int orOperator(int a, int b){
		boolean result = toBoolean(a) || toBoolean(b);
		if(result){
			return 1;
		}else{
			return 0;
		}
	}
	
	private boolean toBoolean(int x){
		return x == 1;
	}
}
