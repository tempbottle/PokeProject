package me.EdwJes.main.rooms;

import me.EdwJes.main.Sprite;
import me.EdwJes.main.Entities.EntityHuman;

public class BattleRoom extends Room {

	public BattleRoom(String name){
		super(name);
	}
	
	@Override public void init(){
		super.init();
		System.out.println("Entered Room");
		new EntityHuman(15,13,Sprite.getEntity(Sprite.Name.Lyra));
	}
	
	@Override public void revisit(){
		super.revisit();
		System.out.println("Revisited");
	}
}
