package me.EdwJes.main.pokemon.data.stats;


public class BaseStats extends Stats{
	public final Integer HP,
                         ATK,
                         DEF,
                         SPD,
                         SPATK,
                         SPDEF,
                         happyness;
	
	public BaseStats(int hp,int attack,int defense,int speed,int specialAttack,int specialDefense,int happyness){
		HP=hp;
		ATK=attack;
		DEF=defense;
		SPD=speed;
		SPATK=specialAttack;
		SPDEF=specialDefense;
		this.happyness=happyness;}

}
