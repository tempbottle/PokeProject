package me.EdwJes.main.pokemon.data.stats;

import me.EdwJes.main.pokemon.data.stats.Stats;

public class BaseYieldStats extends Stats{
	public final Integer HP,
                         ATK,
                         DEF,
                         SPD,
                         SPATK,
                         SPDEF,
                         EXP;
	
	public BaseYieldStats(int hp,int attack,int defense,int speed,int specialAttack,int specialDefense,int experience){
		HP=hp;
		ATK=attack;
		DEF=defense;
		SPD=speed;
		SPATK=specialAttack;
		SPDEF=specialDefense;
		this.EXP=experience;}

}
