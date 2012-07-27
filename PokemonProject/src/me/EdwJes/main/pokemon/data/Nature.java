package me.EdwJes.main.pokemon.data;

import java.util.EnumMap;
import me.EdwJes.main.pokemon.data.stats.Stats;

public class Nature{
	public final EnumMap<Stats.Stat,Float> multiplier;
	private Stats.Stat increase=null,decrease=null;
	
	public Nature(float ATKMultiplier,float DEFMultiplier,float SPDMultiplier,float SPATKMultiplier,float SPDEFMultiplier,Flavor favoriteFlavor,Flavor dislikedFlavor){
		EnumMap<Stats.Stat,Float> stat=new EnumMap<Stats.Stat,Float>(Stats.Stat.class);
		
		stat.put(Stats.Stat.ATK,ATKMultiplier);
		stat.put(Stats.Stat.DEF,DEFMultiplier);
		stat.put(Stats.Stat.SPD,SPDMultiplier);
		stat.put(Stats.Stat.SPATK,SPATKMultiplier);
		stat.put(Stats.Stat.SPDEF,SPDEFMultiplier);
		
		multiplier=stat;
	}
	
	public Nature(Stats.Stat statIncrease,Stats.Stat statDecrease){
		EnumMap<Stats.Stat,Float> stat=new EnumMap<Stats.Stat,Float>(Stats.Stat.class);
		
		stat.put(Stats.Stat.ATK,1f);
		stat.put(Stats.Stat.DEF,1f);
		stat.put(Stats.Stat.SPD,1f);
		stat.put(Stats.Stat.SPATK,1f);
		stat.put(Stats.Stat.SPDEF,1f);
		
		stat.put(statIncrease,1.1f);
		stat.put(statDecrease,0.9f);
		increase=statIncrease;
		decrease=statDecrease;
		
		multiplier=stat;
	}
	
	public Flavor getFavoriteFlavor(){
		if(increase==null)
			return Flavor.NONE;
		else
			return increase.flavor;
	}
	
	public Flavor getDislikedFlavor(){
		if(decrease==null)
			return Flavor.NONE;
		else
			return decrease.flavor;
	}
}