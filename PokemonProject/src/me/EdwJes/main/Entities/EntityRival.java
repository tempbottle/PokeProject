package me.EdwJes.main.Entities;

import java.util.ArrayList;
import me.EdwJes.main.PlayerInput;
import org.newdawn.slick.Animation;

public class EntityRival extends EntityHuman{
	int move=4,plannedMoveX=move;
	
	public EntityRival(int xTile, int yTile, ArrayList<Animation> sprite) {
		super(xTile, yTile, sprite);
		posMoveX(move);
		canInteracted=true;
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
	
	@Override
	public void onInteracted(Entity interactor,int playerId){
		super.onInteracted(interactor);
		PlayerInput.getPlayerInput(playerId).view.createTextbox("I'm apparently your rival...");
	}
}
