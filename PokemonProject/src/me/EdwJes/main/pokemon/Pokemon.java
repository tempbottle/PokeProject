package me.EdwJes.main.pokemon;

import java.util.ArrayList;
import java.util.List;

public class Pokemon{
	private static final int MAX_TYPES=2,MAX_EGGGROUPS=2,MAX_ABILITIES=2,MAX_HIDDENABILITIES=2;
	private List<Type> type=new ArrayList<Type>(MAX_TYPES);
	private List<EggGroup> eggGroups=new ArrayList<EggGroup>(MAX_EGGGROUPS);
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
}
