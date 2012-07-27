package me.EdwJes;

public class Ratio{
	int value,valueMax=100;
	
	public Ratio(int percent){
		value=percent;
	}
	
	public Ratio(int value,int valueMax){
		this.value=value;
		this.valueMax=valueMax;
	}
	
	public boolean isRand(){
		if(value>=Math.round(Math.random()*valueMax))
			return true;
		return false;
	}
	
	public int getRand(){
		return (int)Math.round(Math.random()*valueMax);
	}
	
	public int getValue(){
		return value;
	}
	
	public int getMaxValue(){
		return valueMax;
	}
	
	public int getRatio(){
		return value/valueMax;
	}
}
