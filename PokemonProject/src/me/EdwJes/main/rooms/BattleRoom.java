package me.EdwJes.main.rooms;



import me.EdwJes.main.battle.SingleBattle;
import me.EdwJes.main.battle.Trainer;


public class BattleRoom extends Room {

	public BattleRoom(String name){
		super(name);
	}
	
	@Override public void init(){
		super.init();
		new SingleBattle(new Trainer(Trainer.trainerType.bugCatcher, Trainer.trainerControl.player), 
				new Trainer(Trainer.trainerType.aceTrainer, Trainer.trainerControl.AI));
		
	}
	
	@Override public void revisit(){
		super.revisit();
	}
}
