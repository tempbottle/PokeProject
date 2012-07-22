package me.EdwJes.main.pokemon;

import java.util.ArrayList;
import java.util.List;

public class Move{
	public static List<Move> moves=new ArrayList<Move>();

	public Move(){
		moves.add(this);
	}
	
	public static enum DamageCategory{
		PHYSICAL,
		SPECIAL,
		STATUS;
	}
	
	public static final int MAX_TYPES=1;
	private String name,description;
	private List<Type> types=new ArrayList<Type>(MAX_TYPES);
	private MoveEffect effect;
	private DamageCategory category;
	private int basePP,damagePower,accuracy;
	
	public static int count(){
		return moves.size();
	}
	
	public String getName(){
		return name;
	}
	
	public static Move get(String name){
		for(Move move:moves)
			if(move.getName()==name)
				return move;
		return null;
	}
	
	public static Move get(int index){
		return moves.get(index);
	}
}
