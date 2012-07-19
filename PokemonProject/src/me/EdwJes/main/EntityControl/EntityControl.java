package me.EdwJes.main.EntityControl;

import me.EdwJes.main.OverworldObject;
import me.EdwJes.main.Entities.Entity;

public abstract class EntityControl {
	Entity entity=null;
	
	public EntityControl(Entity entity){
		this.entity=entity;
		entity.controller=this;
	}
	
	public void destroy(){
		entity=null;
	}
	
	public void onMove(){}
	public void onInteracted(Entity target){}
	public void onInteracted(Entity target,int playerId){}
	public void onInteract(OverworldObject target){}
	public void onInteract(OverworldObject target,int playerId){}
	public void onMoveTile(int xTileTo,int yTileTo){}
	public void onMoveFinished(int xTile,int yTile,int movedTiles){}
	public void onMoveStep(int xTile,int yTile){}
}
