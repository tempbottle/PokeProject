package me.EdwJes.main.pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.EdwJes.main.pokemon.data.Ability;
import me.EdwJes.main.pokemon.data.Gender;
import me.EdwJes.main.pokemon.data.Item;
import me.EdwJes.main.pokemon.data.Move;
import me.EdwJes.main.pokemon.data.Nature;
import me.EdwJes.main.pokemon.data.Pokemon;
import me.EdwJes.main.pokemon.data.stats.Stats;

public class PokemonEntity{
//TODO: PokemonEntity Data Structure hint: http://bulbapedia.bulbagarden.net/wiki/Pok%C3%A9mon_data_structure_in_Generation_IV
	
/*TODO: Calculation of stats and damage
/*	http://www.smogon.com/dp/articles/stats
 *  http://bulbapedia.bulbagarden.net/wiki/Stats
 *  http://www.smogon.com/dp/articles/damage_formula
 *  http://bulbapedia.bulbagarden.net/wiki/Experience#Gain_formula
 *  http://bulbapedia.bulbagarden.net/wiki/Pok%C3%A9mon_base_stats_data_structure_in_Generation_III
 *  http://bulbapedia.bulbagarden.net/wiki/Catch_rate
 *  
 *  MoveEffect: http://bulbapedia.bulbagarden.net/wiki/Move_variations
 *  AbilityEffect: http://bulbapedia.bulbagarden.net/wiki/Ability_variations
 *  
 *  MoveEffect: http://bulbapedia.bulbagarden.net/wiki/List_of_moves_that_cause_flinching
 *  Move: http://bulbapedia.bulbagarden.net/wiki/List_of_moves
 *  
 *  http://www.dragonflycave.com/deoxyseffort.aspx
 */
	
//TODO: Load everything (Pokemons, types, attacks...) from data files e.g. YML configs into a HashMap or something similiar
	
	public static final int MAX_MOVES=4,MAX_HELD_ITEMS=2;
	public final long uniqueId;
	public final Pokemon pokemon;
	public final Nature nature;
	public final Gender gender;
	private List<Move> moves=new ArrayList<Move>(MAX_MOVES);
	private HashMap<Move,Integer> movesAddedPP=new HashMap<Move,Integer>();//E.g. from PP-Up
	private List<Ability> ability=new ArrayList<Ability>();
	private Ability hiddenAbility=null;
	private int level=1,experience=0,happyness=0;
	private Stats iv,ev;
	private List<Item> heldItems=new ArrayList<Item>(MAX_HELD_ITEMS);
	private String nickname=null;
	
	public PokemonEntity(long uniqueId,Pokemon pokemon,Nature nature,Gender gender){
		this.uniqueId=uniqueId;
		this.pokemon=pokemon;
		this.nature=nature;
		this.gender=gender;
	}
	
	public double getHP(){
		return ((iv.HP+(2*pokemon.base.HP+(ev.HP/4)+100)*level)/100)+10;
	}
	
	private double getStat(int iv,int ev,int base,double natureMultiplier){
		return ((((iv+(2*base)+(ev/4))*level)/100)+5)*natureMultiplier;
	}
	
	public double getATK(){
		return getStat(iv.ATK,ev.ATK,pokemon.base.ATK,nature.multiplier.get(Stats.Stat.ATK));
	}
	
	public double getDEF(){
		return getStat(iv.DEF,ev.DEF,pokemon.base.DEF,nature.multiplier.get(Stats.Stat.DEF));
	}
	
	public double getSPD(){
		return getStat(iv.SPD,ev.SPD,pokemon.base.SPD,nature.multiplier.get(Stats.Stat.SPD));
	}
	
	public double getSPATK(){
		return getStat(iv.SPATK,ev.SPATK,pokemon.base.SPATK,nature.multiplier.get(Stats.Stat.SPATK));
	}
	
	public double getSPDEF(){
		return getStat(iv.SPDEF,ev.SPDEF,pokemon.base.SPDEF,nature.multiplier.get(Stats.Stat.SPDEF));
	}
	
	public int getExperience(){
		return experience;
	}
	
	public Move getMove(int index){
		return moves.get(index);
	}
	
	public int getExperienceToLevel(){
		return pokemon.getExpGroup().getLevelExperience(level);
	}
	
	public void ivAdd(Stats.Stat stat,int amount){
		iv.add(stat,amount);
	}
	
	public void evAdd(Stats.Stat stat,int amount){
		ev.add(stat,amount);
	}
	
	public String getName(){
		if(nickname==null)
			return pokemon.name;
		else
			return nickname;
	}
}
