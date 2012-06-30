package me.EdwJes.main.rooms;

import me.EdwJes.main.Console;
import me.EdwJes.main.Sprite;
import me.EdwJes.main.Entities.EntityHuman;

public class BattleRoom extends Room {

	public BattleRoom(String name){
		super(name);
	}
	
	@Override public void init(){
		System.out.println("Entered Room");
		new EntityHuman(15,13,Sprite.getEntity(Sprite.Name.Lyra));
	}
}
