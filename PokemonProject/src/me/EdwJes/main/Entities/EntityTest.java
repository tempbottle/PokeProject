package me.EdwJes.main.Entities;

import me.EdwJes.main.PokemonProject;

import org.newdawn.slick.Animation;

public class EntityTest extends EntityHuman{
	public EntityTest(int xTile, int yTile,Animation[] sprite){
		super(xTile, yTile, sprite);
	}
	
	@Override
	public void onInteracted(Entity interactor){
		super.onInteracted(interactor);
		PokemonProject.cmd.outputConsole("YES YES YES");
	}
}
