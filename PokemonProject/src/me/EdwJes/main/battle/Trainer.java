package me.EdwJes.main.battle;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
	
	List<BattleEntity> pokemonList = new ArrayList<BattleEntity>();
	Battle battle;
	trainerControl control;
	
	public enum trainerType{
		bugCatcher, aceTrainer, wild
	}
	
	public enum trainerControl{
		player, AI
	}
	
	public Trainer(trainerType type, trainerControl trnControl){
		
		control = trnControl;
		
		switch(type){
		case bugCatcher:
			pokemonList.add(new BattleEntity(this));
			break;
		}
	}
	
	

}
