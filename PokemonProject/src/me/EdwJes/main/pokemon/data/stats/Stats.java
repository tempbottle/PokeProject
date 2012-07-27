package me.EdwJes.main.pokemon.data.stats;

import me.EdwJes.main.pokemon.data.Flavor;

public class Stats{
	public static enum Stat{
		ATK(Flavor.SPICY),
		DEF(Flavor.SOUR),
		SPD(Flavor.SWEET),
		SPATK(Flavor.DRY),
		SPDEF(Flavor.BITTER);
		
		/**
		 * Flavor is a group of five attributes that all Berries, Pokéblocks, Poffin, and Apricorns have, in different amounts. Each flavor represents a specific Contest condition and Pokéathlon performance stat, and is liked and disliked by different Pokémon depending on their natures. The flavor is also consistently liked or disliked depending on which stat is raised or lowered; for example, all Pokémon who like spicy flavors will have a nature that raises their Attack, while those that dislike spicy flavors will all have a nature that lowers their Attack. Every Pokémon will like and dislike a certain flavor unless their nature is Hardy, Docile, Serious, Bashful, or Quirky, in which case they will have no preference.
		 * <P>Source: <a href=http://bulbapedia.bulbagarden.net/wiki/Flavor>Flavor - Bulbapedia</a> 
		 */
		public final Flavor flavor;
		
		Stat(Flavor flavor){
			this.flavor=flavor;
		}
	}
	public Integer HP=0,
	               ATK=0,
	               DEF=0,
	               SPD=0,
	               SPATK=0,
	               SPDEF=0;
	
	public Stats(){}
	
	public Stats(int hp,int attack,int defense,int speed,int specialAttack,int specialDefense){
		HP=hp;
		ATK=attack;
		DEF=defense;
		SPD=speed;
		SPATK=specialAttack;
		SPDEF=specialDefense;
	}
	
	public Stats(int attack,int defense,int speed,int specialAttack,int specialDefense){
		HP=null;
		ATK=attack;
		DEF=defense;
		SPD=speed;
		SPATK=specialAttack;
		SPDEF=specialDefense;
	}
	
	public Integer getStat(Stat stat){
		if(stat==Stat.ATK)
			return ATK;
		if(stat==Stat.DEF)
			return DEF;
		if(stat==Stat.SPD)
			return SPD;
		if(stat==Stat.SPATK)
			return SPATK;
		if(stat==Stat.SPDEF)
			return SPDEF;
		else
			return HP;
	}
	
	public void add(Stat stat,int value){
		Integer statVariableRef=getStat(stat);
		statVariableRef+=value;
	}
}
