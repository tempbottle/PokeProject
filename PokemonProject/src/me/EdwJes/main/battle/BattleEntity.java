package me.EdwJes.main.battle;

import java.util.ArrayList;
import java.util.List;

public class BattleEntity {
	
	int hp, attack, defense, spAttack, spDefense, speed = 10;
	Trainer trainer;
	
	List<BattleEntity> foes = new ArrayList<BattleEntity>();

	public BattleEntity(Trainer trainer){
		this.trainer = trainer;
	}
	
	public void performMove(Move move){
		move.getTargets(this);
	}

}
