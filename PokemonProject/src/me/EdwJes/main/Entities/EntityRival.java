package me.EdwJes.main.Entities;

import org.newdawn.slick.Animation;

public class EntityRival extends EntityHuman{
	int move=4;
	
	public EntityRival(int xTile, int yTile, Animation[] sprite) {
		super(xTile, yTile, sprite);
		posMoveX(move);
	}

	@Override
	public void onMoveFinished(int xTile,int yTile){
		super.onMoveFinished(xTile, yTile);
		move=-move;
		posMoveX(move);
	}
}
