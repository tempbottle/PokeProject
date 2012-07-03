package me.EdwJes.main.Entities;

import me.EdwJes.main.PokemonProject;

import org.newdawn.slick.Animation;

public class EntityRival extends EntityHuman{
	int move=4,plannedMoveX=move;
	
	public EntityRival(int xTile, int yTile, Animation[] sprite) {
		super(xTile, yTile, sprite);
		posMoveX(move);
	}

	@Override
	public void onMoveFinished(int xTile,int yTile,int movedTiles){
		super.onMoveFinished(xTile,yTile,movedTiles);
		if(movedTiles>=Math.abs(plannedMoveX)){
			move=-move;
			plannedMoveX=move;}
		else{
			plannedMoveX=(int)((Math.abs(plannedMoveX)-Math.abs(movedTiles))*Math.signum(move));
		}
	}
	
	@Override
	public void update(){
		super.update();
		if(!isMoving())
			posMoveX(plannedMoveX);
	}
}
