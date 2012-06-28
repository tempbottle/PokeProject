package me.EdwJes.main.EntityControl;

import me.EdwJes.main.PlayerControlObject;
import me.EdwJes.main.Entities.Entity;

public class PlayerEntityControl implements PlayerControlObject,EntityControl{
	private Entity entity;
	
	public PlayerEntityControl(Entity entity) {
		this.entity=entity;
	}
	//TODO: L�tt-tryck f�r att v�nda sig ist�llet f�r att r�ra sig direkt n�r man har v�nt sig, dir variabeln. F�r g�ra en timer eller n�got 
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
		// TODO Auto-generated method stub
	}

	@Override
	public void onKeySkip() {
		
	}

	@Override
	public Entity getEntity() {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyDownPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyActionPressed() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeySkipReleased() {
		entity.setPosMoveSpeed(entity.walkingSpeed);
	}
}
