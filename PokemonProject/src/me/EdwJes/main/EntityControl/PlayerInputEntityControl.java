package me.EdwJes.main.EntityControl;

import me.EdwJes.main.GameObject;
import me.EdwJes.main.PlayerInputControlObject;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.Entities.Entity;
import me.EdwJes.main.Entities.Entity.Direction;

public class PlayerInputEntityControl implements PlayerInputControlObject,EntityControl{
	private Entity entity;
	
	public PlayerInputEntityControl(Entity entity) {
		this.entity=entity;
	}
	//TODO: Lätt-tryck för att vända sig istället för att röra sig direkt när man har vänt sig, dir variabeln. Får göra en timer eller något 
	@Override
	public void onKeyLeft(){
		entity.posMoveX(-1);}
	
	@Override
	public void onKeyRight(){
		entity.posMoveX(1);}

	public void onKeyUp(){
		entity.posMoveY(-1);}
	
	@Override
	public void onKeyDown(){
		entity.posMoveY(1);}

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
		int xDistance=0,yDistance=0;
		if(entity.dir==Direction.LEFT)
			xDistance=-1;
		if(entity.dir==Direction.RIGHT)
			xDistance=1;
		if(entity.dir==Direction.UP)
			yDistance=-1;
		if(entity.dir==Direction.DOWN)
			yDistance=1;
		//TODO: Implement interaction with entities when collision check code is finished
	}

	@Override
	public void onKeySkipPressed() {
		entity.setPosMoveSpeed(entity.runningSpeed);
	}

	@Override
	public void onKeyLeftReleased() {
	}

	@Override
	public void onKeyRightReleased() {
	}

	@Override
	public void onKeyUpReleased() {
	}

	@Override
	public void onKeyDownReleased() {
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
