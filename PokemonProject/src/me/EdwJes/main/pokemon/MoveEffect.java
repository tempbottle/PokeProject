package me.EdwJes.main.pokemon;

import java.util.HashMap;

public class MoveEffect{
	private String name;
	public static HashMap<String,MoveEffect> effects=new HashMap<String,MoveEffect>();

	public MoveEffect(String name){
		this.name=name;
		effects.put(name,this);
	}
	
	public String getName(){
		return name;
	}
}
