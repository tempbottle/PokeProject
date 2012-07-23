package me.EdwJes.main.battle;

import java.util.ArrayList;
import java.util.List;

public class Battle {
	
	List<BattleEntity> EntitiyList = new ArrayList<BattleEntity>();
	
	public Battle(Trainer trn1, Trainer trn2){
		trn1.battle = this;
		trn2.battle = this;
		
	}

}
