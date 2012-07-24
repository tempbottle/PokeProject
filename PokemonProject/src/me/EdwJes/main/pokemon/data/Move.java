package me.EdwJes.main.pokemon.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.EdwJes.main.pokemon.data.effects.MoveEffect;

public class Move{
	private static List<Move> moves=new ArrayList<Move>();
	
	public static int count(){
		return moves.size();
	}
	
	public static Move get(String name){
		for(Move move:moves)
			if(move.name==name)
				return move;
		return null;
	}
	
	public static Move get(int index){
		return moves.get(index);
	}
	
	public static List<Move> list(){
		return new ArrayList<Move>(moves);
	}

	public Move(String name,Type[] types,DamageCategory category,int damagePower,int accuracy,int basePP){
		this.name=name;
		this.category=category;
		this.damagePower=damagePower;
		this.accuracy=accuracy;
		this.basePP=basePP;
		this.effect=null;
		this.types=Arrays.asList(Arrays.copyOf(types,MAX_TYPES));
		moves.add(this);
	}
	
	public Move(String name,Type[] types,DamageCategory category,int damagePower,int accuracy,int basePP,MoveEffect effect){
		this.name=name;
		this.category=category;
		this.damagePower=damagePower;
		this.accuracy=accuracy;
		this.basePP=basePP;
		this.effect=effect;
		this.types=Arrays.asList(Arrays.copyOf(types,MAX_TYPES));
		moves.add(this);
	}
	
	public static final int MAX_TYPES=1;
	public final String name;
	public final MoveEffect effect;
	public final DamageCategory category;
	public final int basePP,damagePower,accuracy;
	private String description;
	private final List<Type> types;
	
	public int countTypes(){
		/* If there is null elements in the list when applying asList on copyOf in constructor
		 * I'm not sure
		 * 
		 * int i=0;
		for(Type t:types)
			if(t!=null)
				i++;
		return i;*/
		return moves.size();
	}
	
	public ArrayList<Type> getTypes(){
		return new ArrayList<Type>(types);
	}
	
	public Type getType(int index){
		return types.get(index);
	}
}
