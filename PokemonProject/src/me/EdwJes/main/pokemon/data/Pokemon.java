package me.EdwJes.main.pokemon.data;

import java.util.ArrayList;
import java.util.List;

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
	
	public static Pokemon get(int index){
		return pokemons.get(index);
	}
	
	public static List<Pokemon> list(){
		return new ArrayList<Pokemon>(pokemons);
	}
	
	public static final int MAX_TYPES=2,MAX_EGGGROUPS=2,MAX_ABILITIES=2,MAX_HIDDENABILITIES=2;
	public final String name;
	private List<Type> type=new ArrayList<Type>(MAX_TYPES);
	private List<EggGroup> eggGroups=new ArrayList<EggGroup>(MAX_EGGGROUPS);
	//TODO: Make all variables final and required to be defined in the constructor
	private int baseHP,
	            baseATK,
	            baseDEF,
	            baseSPD,
	            baseSPATK,
	            baseSPDEF,
	            baseHappyness,
	            yieldExp,
	            yieldEV_HP,
	            yieldEV_ATK,
	            yieldEV_DEF,
	            yieldEV_SPD,
	            yieldEV_SPATK,
	            yieldEV_SPDEF,
	            weight,
	            height;
	private Species species;
	private String description;
	private List<Ability> possibleAbilities=new ArrayList<Ability>(MAX_ABILITIES),possibleHiddenAbilities=new ArrayList<Ability>(MAX_HIDDENABILITIES);
	private ExperienceGroup expGroup;
	
	public Pokemon(String name){
		this.name=name;
	}
	
	public List<Type> getType(){
		return type;
	}
	public List<EggGroup> getEggGroups(){
		return eggGroups;
	}
	public int getBaseHP(){
		return baseHP;
	}
	public int getBaseATK(){
		return baseATK;
	}
	public int getBaseDEF(){
		return baseDEF;
	}
	public int getBaseSPD(){
		return baseSPD;
	}
	public int getBaseSPATK(){
		return baseSPATK;
	}
	public int getBaseSPDEF(){
		return baseSPDEF;
	}
	public int getBaseHappyness(){
		return baseHappyness;
	}
	public int getYieldExp(){
		return yieldExp;
	}
	public int getYieldEV_HP(){
		return yieldEV_HP;
	}
	public int getYieldEV_ATK(){
		return yieldEV_ATK;
	}
	public int getYieldEV_DEF(){
		return yieldEV_DEF;
	}
	public int getYieldEV_SPD(){
		return yieldEV_SPD;
	}
	public int getYieldEV_SPATK(){
		return yieldEV_SPATK;
	}
	public int getYieldEV_SPDEF(){
		return yieldEV_SPDEF;
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
