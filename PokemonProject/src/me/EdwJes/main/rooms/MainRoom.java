package me.EdwJes.main.rooms;

import me.EdwJes.main.overworld.entities.EntityHuman;
import me.EdwJes.main.overworld.entities.EntityRival;
import me.EdwJes.main.overworld.entities.EntityTest;
import me.EdwJes.main.overworld.tiles.Tiles;
import me.EdwJes.main.resources.Sprite;

public class MainRoom extends Room{

	public MainRoom(String name) {
		super(name);
	}
	
	@Override
	public void init(){
		super.init();
		new EntityRival(4,10,Sprite.getAnimationGroup("May"));
		new EntityTest(1,5,Sprite.getAnimationGroup("May"));
		new EntityHuman(5,7,Sprite.getAnimationGroup("Lyra"));
		new EntityHuman(5,8,Sprite.getAnimationGroup("Lyra"));
		new EntityHuman(8,6,Sprite.getAnimationGroup("Lyra"));
		new EntityHuman(8,8,Sprite.getAnimationGroup("Lyra"));
		
		Tiles tiles=new Tiles();
		tiles.LOAD_TESTING();
		tiles.tileAdd(tiles.TESTTILE, 0, 0, 40, 30);
	}
}
