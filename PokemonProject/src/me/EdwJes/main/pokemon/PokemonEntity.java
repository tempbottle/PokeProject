package me.EdwJes.main.pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonEntity{
/*TODO: Calculation of stats and damage
/*	http://www.smogon.com/dp/articles/stats
 *  http://bulbapedia.bulbagarden.net/wiki/Stats
 *  http://www.smogon.com/dp/articles/damage_formula
 *  http://bulbapedia.bulbagarden.net/wiki/Experience#Gain_formula
 *  http://bulbapedia.bulbagarden.net/wiki/Pok%C3%A9mon_base_stats_data_structure_in_Generation_III
 *  http://bulbapedia.bulbagarden.net/wiki/Catch_rate
 *  
 *  http://bulbapedia.bulbagarden.net/wiki/Move_variations
 *  http://bulbapedia.bulbagarden.net/wiki/Ability_variations
 *  
 *  http://bulbapedia.bulbagarden.net/wiki/List_of_moves_that_cause_flinching
 *  http://bulbapedia.bulbagarden.net/wiki/List_of_moves
 *  
 *  http://www.dragonflycave.com/deoxyseffort.aspx
 */
	
//TODO: Load everything (Pokemons, types, attacks...) from data files e.g. YML configs into a HashMap or something similiar
	public static enum Gender{
		MALE,
		FEMALE,
		GENDERLESS;
	}
	
	private static final int MAX_MOVES=4;
	private List<Move> moves=new ArrayList<Move>(MAX_MOVES);
	private Pokemon pokemon;
	private Gender gender;
	private Ability ability,hiddenAbility;
	private int HP,attack,defense,speed,spAttack,spDefense;
	
	public PokemonEntity(Pokemon pokemon){
		
	}
}
