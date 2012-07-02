package me.EdwJes.main.EntityControl;

import me.EdwJes.main.GameObject;
import me.EdwJes.main.OverworldObject;
import me.EdwJes.main.PlayerInputControlObject;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.Entities.Entity;
import me.EdwJes.main.Entities.Entity.Direction;
import me.EdwJes.main.Entities.Entity.DirectionX;
import me.EdwJes.main.Entities.Entity.DirectionY;

public class PlayerInputEntityControl implements PlayerInputControlObject,EntityControl{
	private Entity entity;
	
	public PlayerInputEntityControl(Entity entity) {
		this.entity=entity;
	}
	//TODO: Lätt-tryck för att vända sig istället för att röra sig direkt när man har vänt sig, dir variabeln. Får göra en timer eller något 
	@Override
	public void onKeyLeft(){
		entity.posMoveX(DirectionX.LEFT);}
	
	@Override
	public void onKeyRight(){
		entity.posMoveX(DirectionX.RIGHT);}

	public void onKeyUp(){
		entity.posMoveY(DirectionY.UP);}
	
	@Override
	public void onKeyDown(){
		entity.posMoveY(DirectionY.DOWN);}

	@Override
	public void onKeyAction() {

	}

	@Override
	public void onKeySkip() {
		
	}

	@Override
	public GameObject getControlledObject() {
		return entity;
	}

	@Override
	public void onKeyLeftPressed() {
		
	}

	@Override
	public void onKeyRightPressed() {

	}

	@Override
	public void onKeyUpPressed() {

	}

	@Override
	public void onKeyDownPressed() {

	}

	@Override
	public void onKeyActionPressed() {
		if(!entity.isDirectionFree(entity.dir)){
			OverworldObject target=entity.getObjCollidedDir(entity.dir);
			entity.interact(target);}
	}

	@Override
	public void onKeySkipPressed() {
		entity.setPosMoveSpeed(entity.runningSpeed);
	}

	@Override
	public void onKeyLeftReleased() {
		if(entity.getStopMove()==-1)
			entity.stopMove(Direction.LEFT);
	}

	@Override
	public void onKeyRightReleased() {
		if(entity.getStopMove()==-1)
			entity.stopMove(Direction.RIGHT);
	}

	@Override
	public void onKeyUpReleased() {
		if(entity.getStopMove()==-1)
			entity.stopMove(Direction.UP);
	}

	@Override
	public void onKeyDownReleased() {
		if(entity.getStopMove()==-1)
			entity.stopMove(Direction.DOWN);
	}

	@Override
	public void onKeyActionReleased() {

	}

	@Override
	public void onKeySkipReleased() {
		entity.setPosMoveSpeed(entity.walkingSpeed);
	}
	@Override
	public float getXPos() {
		return entity.getXPos();
	}
	@Override
	public float getYPos() {
		return entity.getYPos();
	}
	@Override
	public int getXTile() {
		return entity.getXTile();
	}
	@Override
	public int getYTile() {
		return entity.getYTile();
	}
	@Override
	public void onKeyEnter() {
		
	}
	@Override
	public void onKeyEnterPressed() {
		
		
	}
	@Override
	public void onKeyEnterReleased() {
		
	}
	@Override
	public void onKeyChat() {
	
	}
	@Override
	public void onKeyChatPressed(){
		PokemonProject.player.setObj(PokemonProject.cmd);
		PokemonProject.cmd.enterConsole();
	}
	@Override
	public void onKeyChatReleased(){
	}

	@Override
	public void onKeyPressed(int key, char chr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onKeyReleased(int key, char chr) {
		// TODO Auto-generated method stub
		
	}
}
