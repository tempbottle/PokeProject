package me.EdwJes.main.pokemon;

import java.util.ArrayList;
import java.util.List;
/*
 * Should be impossible to remove a Type in-game
 */
public class Type{
	public static List<Type> types=new ArrayList<Type>();
	private String name;
	
	public Type(String name){
		types.add(this);
		this.name=name;
	}

	public String getName(){
		return name;
	}
	
	public static int count(){
		return types.size();
	}
	
	public static Type get(String name){
		for(Type type:types)
			if(type.getName()==name)
				return type;
		return null;
	}
	
	public static Type get(int index){
		return types.get(index);
	}
}
