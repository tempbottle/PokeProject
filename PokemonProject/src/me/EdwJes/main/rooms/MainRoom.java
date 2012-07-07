package me.EdwJes.main.rooms;

import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.Sprite;
import me.EdwJes.main.Entities.EntityHuman;
import me.EdwJes.main.Entities.EntityRival;
import me.EdwJes.main.Entities.EntityTest;
import me.EdwJes.main.tiles.Tiles;

public class MainRoom extends Room{

	public MainRoom(String name) {
		super(name);
	}
	
	@Override
	public void init(){
		new EntityRival(4,10,Sprite.getEntity(Sprite.Name.May));
		new EntityTest(1,5,Sprite.getEntity(Sprite.Name.May));
		new EntityHuman(5,7,Sprite.getEntity(Sprite.Name.Lyra));
		new EntityHuman(5,8,Sprite.getEntity(Sprite.Name.Lyra));
		new EntityHuman(8,6,Sprite.getEntity(Sprite.Name.Lyra));
		new EntityHuman(8,8,Sprite.getEntity(Sprite.Name.Lyra));
		
		Tiles tiles=new Tiles();
		tiles.LOAD_TESTING(PokemonProject.IMAGE_LOADER);
		tiles.tileAdd(tiles.TESTTILE, 0, 0, 40, 30);
	}
	
}
