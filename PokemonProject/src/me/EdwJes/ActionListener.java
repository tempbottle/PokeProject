package me.EdwJes;

public class ActionListener{
	private Object[] args;
	
	public ActionListener(Object... args){
		this.args=args;
	}
	
	public ActionListener(){
		this.args=null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getArg(int argIndex){
		return (T)args[argIndex];
	}
	
	public void onAction(int actionId){}
}
