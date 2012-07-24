package me.EdwJes.main.pokemon.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
 * Should be impossible to remove a Type in-game
 */
public class Type{
	private static List<Type> types=new ArrayList<Type>();
	private HashMap<Type,Integer> typeEffectivity=new HashMap<Type,Integer>();
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
	
	/**
	 * Returns how effective this type is when attacking the target type
	 * @param type The target Type
	 * @return Damage multiplier e.g. 1x, 0.5x, 2x in int
	 */
	public int getTypeEffectivity(Type type){
		if(typeEffectivity.containsKey(type))
			return typeEffectivity.get(type);
		return 1;
	}
}
