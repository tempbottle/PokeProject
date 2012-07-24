package me.EdwJes.main.pokemon;

import java.util.ArrayList;
import java.util.List;
import me.EdwJes.main.pokemon.data.Ability;
import me.EdwJes.main.pokemon.data.Gender;
import me.EdwJes.main.pokemon.data.Item;
import me.EdwJes.main.pokemon.data.Move;
import me.EdwJes.main.pokemon.data.Nature;
import me.EdwJes.main.pokemon.data.Pokemon;
import me.EdwJes.main.pokemon.data.Stat;

public class PokemonEntity{
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
	
	public static final int MAX_MOVES=4;
	public final long uniqueId;
	public final Pokemon pokemon;
	public final Nature nature;
	private List<Move> moves=new ArrayList<Move>(MAX_MOVES);
	private Gender gender;
	private Ability ability,hiddenAbility;
	private int ivHP=0,ivATK=0,ivDEF=0,ivSPD=0,ivSPATK=0,ivSPDEF=0,
			    evHP=0,evATK=0,evDEF=0,evSPD=0,evSPATK=0,evSPDEF=0,
			    level=1,experience=0;
	private Item heldItem;
	
	public PokemonEntity(long uniqueId,Pokemon pokemon,Nature nature){
		this.uniqueId=uniqueId;
		this.pokemon=pokemon;
		this.nature=nature;
	}
	
	public double getHP(){
		return ((ivHP+(2*pokemon.getBaseHP()+(evHP/4)+100)*level)/100)+10;
	}
	
	private double getStat(int iv,int ev,int base,double natureMultiplier){
		return ((((iv+(2*base)+(ev/4))*level)/100)+5)*natureMultiplier;
	}
	
	public double getATK(){
		return getStat(ivATK,evATK,pokemon.getBaseATK(),nature.multiplier.get(Stat.ATK));
	}
	
	public double getDEF(){
		return getStat(ivDEF,evDEF,pokemon.getBaseDEF(),nature.multiplier.get(Stat.DEF));
	}
	
	public double getSPD(){
		return getStat(ivSPD,evSPD,pokemon.getBaseSPD(),nature.multiplier.get(Stat.SPD));
	}
	
	public double getSPATK(){
		return getStat(ivSPATK,evSPATK,pokemon.getBaseSPATK(),nature.multiplier.get(Stat.SPATK));
	}
	
	public double getSPDEF(){
		return getStat(ivSPDEF,evSPDEF,pokemon.getBaseSPDEF(),nature.multiplier.get(Stat.SPDEF));
	}
	
	public int getExperience(){
		return experience;
	}
	
	public int getExperienceToLevel(){
		return pokemon.getExpGroup().getLevelExperience(level);
	}
}
