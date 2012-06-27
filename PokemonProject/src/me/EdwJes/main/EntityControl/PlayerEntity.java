package me.EdwJes.main.EntityControl;

import me.EdwJes.main.PlayerControlObject;
import me.EdwJes.main.Entities.Entity;

public class PlayerEntity implements PlayerControlObject,EntityControl{
	private Entity entity;
	
	public PlayerEntity(Entity entity) {
		this.entity=entity;
	}

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
		entity.setPosMoveSpeed(3);
	}

	@Override
	public Entity getEntity() {
		return entity;
	}
}
