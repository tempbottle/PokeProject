package me.EdwJes.main.rooms;

import me.EdwJes.main.Sprite;
import me.EdwJes.main.Entities.EntityHuman;

public class InitRoom extends Room {
	
	public InitRoom(String name){
		super(name);
	}
	
	public void init(){
		super.init();
		new EntityHuman(4,4,Sprite.getEntity(Sprite.Name.May));
		new EntityHuman(1,5,Sprite.getEntity(Sprite.Name.May));
		new EntityHuman(5,7,Sprite.getEntity(Sprite.Name.Lyra));
		new EntityHuman(5,8,Sprite.getEntity(Sprite.Name.Lyra));
		new EntityHuman(8,6,Sprite.getEntity(Sprite.Name.Lyra));
		new EntityHuman(8,8,Sprite.getEntity(Sprite.Name.Lyra));
	}

}
