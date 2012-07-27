package me.EdwJes.main.pokemon.data;

import java.util.ArrayList;
import java.util.List;
import me.EdwJes.main.pokemon.data.stats.BaseStats;
import me.EdwJes.main.pokemon.data.stats.BaseYieldStats;

/**
 * <P>The structure for a Pokemon specie. E.g. Charmander, Pikachu and Mewtwo are three Pokemon species.
 * <P>Each Pokemon is stored with a unique ID when creating new Pokemon objects with "new Pokemon()".
 * <P>All the data in a Pokemon should be final and not editable. It should not be able to delete a Pokemon specie in-game.
 * <P>When a Pokemon specie data is needed, get it with "get(String name)" or "get(int pokemonId)". 
 * 
 * @author Edwin
 *
 */
public class Pokemon{
	private static List<Pokemon> pokemons=new ArrayList<Pokemon>();
	
	public static int count(){
		return pokemons.size();
	}
	
	public static Pokemon get(String name){
		for(Pokemon o:pokemons)
			if(o.name==name)
				return o;
		return null;
	}
	
	public static Pokemon get(int pokemonId){
		return pokemons.get(pokemonId);
	}
	
	public static List<Pokemon> list(){
		return new ArrayList<Pokemon>(pokemons);
	}
	
	public static final int MAX_TYPES=2,MAX_EGGGROUPS=2,MAX_ABILITIES=2,MAX_HIDDENABILITIES=2;
	public final String name;
	private List<Type> type=new ArrayList<Type>(MAX_TYPES);
	private List<EggGroup> eggGroups=new ArrayList<EggGroup>(MAX_EGGGROUPS);
	//TODO: Make all variables final and required to be defined in the constructor
	public final int baseHappyness,
	                 yieldExp;
	public final BaseYieldStats yield;
	public final BaseStats base;
	private int weight,
	            height;
	private Species species;
	private String description;
	private List<Ability> possibleAbilities=new ArrayList<Ability>(MAX_ABILITIES),possibleHiddenAbilities=new ArrayList<Ability>(MAX_HIDDENABILITIES);
	private ExperienceGroup expGroup;
	
	public Pokemon(String name,BaseStats base,int baseHappyness,BaseYieldStats yield,int yieldExp){
		this.name=name;
		this.base=base;
		this.baseHappyness=baseHappyness;
		this.yield=yield;
		this.yieldExp=yieldExp;
	}
	
	public List<Type> getType(){
		return type;
	}
	public List<EggGroup> getEggGroups(){
		return eggGroups;
	}
	public int getWeight(){
		return weight;
	}
	public int getHeight(){
		return height;
	}
	public Species getSpecies(){
		return species;
	}
	public String getDescription(){
		return description;
	}
	public List<Ability> getPossibleAbilities(){
		return possibleAbilities;
	}
	public List<Ability> getPossibleHiddenAbilities(){
		return possibleHiddenAbilities;
	}
	public ExperienceGroup getExpGroup(){
		return expGroup;
	}
}
