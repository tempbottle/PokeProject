package me.EdwJes.main.rooms;

import me.EdwJes.main.Entities.EntityHuman;
import me.EdwJes.main.resources.Sprite;

public class BattleRoom extends Room {

	public BattleRoom(String name){
		super(name);
	}
	
	@Override public void init(){
		super.init();
		System.out.println("Entered Room");
		new EntityHuman(15,13,Sprite.getAnimationGroup("Lyra"));
	}
	
	@Override public void revisit(){
		super.revisit();
		System.out.println("Revisited");
	}
}
