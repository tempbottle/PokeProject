package me.EdwJes.main.overworld.entitycontrol;

import org.newdawn.slick.Input;
import me.EdwJes.main.GameObject;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PlayerInputControlObject;
import me.EdwJes.main.config.Config.Key;
import me.EdwJes.main.overworld.OverworldObject;
import me.EdwJes.main.overworld.entities.Entity;
import me.EdwJes.main.overworld.entities.Entity.Direction;
import me.EdwJes.main.overworld.entities.Entity.DirectionX;
import me.EdwJes.main.overworld.entities.Entity.DirectionY;
import me.EdwJes.main.overworld.entities.Entity.Movement;

public class PlayerInputEntityControl extends EntityControl implements PlayerInputControlObject{
	private int beginMovementTimer=0,beginMovementTime=6;
	Input input=null;
	Direction beginDir=Direction.RIGHT;
	
	public PlayerInputEntityControl(Entity entity) {
		super(entity);
	}
	//TODO: Lätt-tryck för att vända sig istället för att röra sig direkt när man har vänt sig, dir variabeln. Får göra en timer eller något
	
	@Override
	public void handleInput(Input input,PlayerInput player){
		this.input=input;
		
		if(input.isKeyDown(player.getKeymap(Key.LEFT))){
			if(beginMovementTimer>=beginMovementTime||beginDir==Direction.LEFT){
				entity.posMoveX(DirectionX.LEFT);}
			else
				beginMovementTimer++;}
		else if(input.isKeyDown(player.getKeymap(Key.RIGHT))){
			if(beginMovementTimer>=beginMovementTime||beginDir==Direction.RIGHT){
				entity.posMoveX(DirectionX.RIGHT);}
			else
				beginMovementTimer++;}
		else if(input.isKeyDown(player.getKeymap(Key.UP))){
			if(beginMovementTimer>=beginMovementTime||beginDir==Direction.UP){
				entity.posMoveY(DirectionY.UP);}
			else
				beginMovementTimer++;}
		else if(input.isKeyDown(player.getKeymap(Key.DOWN))){
			if(beginMovementTimer>=beginMovementTime||beginDir==Direction.DOWN){
				entity.posMoveY(DirectionY.DOWN);}
			else
				beginMovementTimer++;}
		
		if(input.isKeyPressed(Input.KEY_F5)){
			if(player.view.followsObject==null)
				player.view.followsObject=entity;
			else
				player.view.followsObject=null;
		}
	}

	public GameObject getControlledObject() {
		return entity;
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
	public void onKeyPressed(int key, char chr,PlayerInput player) {
		if(key==player.getKeymap(Key.LEFT)){
			if(!entity.isMoving()){
				beginMovementTimer=0;
				beginDir=entity.dir;
				entity.dir=Direction.LEFT;
			}
		}
		else if(key==player.getKeymap(Key.RIGHT)){
			if(!entity.isMoving()){
				beginMovementTimer=0;
				beginDir=entity.dir;
				entity.dir=Direction.RIGHT;
			}
		}
		else if(key==player.getKeymap(Key.UP)){
			if(!entity.isMoving()){
				beginMovementTimer=0;
				beginDir=entity.dir;
				entity.dir=Direction.UP;
			}
		}
		else if(key==player.getKeymap(Key.DOWN)){
			if(!entity.isMoving()){
				beginMovementTimer=0;
				beginDir=entity.dir;
				entity.dir=Direction.DOWN;
			}
		}
		
		else if(key==player.getKeymap(Key.ACTION)){
			if(!entity.isDirectionFree(entity.dir)){
				OverworldObject target=entity.getObjCollidedDir(entity.dir);
				entity.interact(target,player.getId());}
		}
		
		else if(key==player.getKeymap(Key.SKIP)){
			entity.setPosMoveSpeed(entity.runningSpeed);
		}
		
		else if(key==player.getKeymap(Key.CHAT)){
			if(!player.view.cmd.isOn()){
				player.setObj(player.view.cmd);
				player.view.cmd.enterConsole();}
		}
	}
	@Override
	public void onKeyReleased(int key, char chr,PlayerInput player) {
		if(key==player.getKeymap(Key.LEFT)){
			if(entity.getStopMove()==Movement.NONE){
				entity.stopMove(Direction.LEFT);}
		}
		
		else if(key==player.getKeymap(Key.RIGHT)){
			if(entity.getStopMove()==Movement.NONE){
				entity.stopMove(Direction.RIGHT);}
		}
		
		else if(key==player.getKeymap(Key.UP)){
			if(entity.getStopMove()==Movement.NONE){
				entity.stopMove(Direction.UP);}
		}
		
		else if(key==player.getKeymap(Key.DOWN)){
			if(entity.getStopMove()==Movement.NONE){
				entity.stopMove(Direction.DOWN);}
		}
		
		else if(key==player.getKeymap(Key.SKIP)){
			entity.setPosMoveSpeed(entity.walkingSpeed);
		}
	}

	@Override
	public boolean isKeyRepeat(){
		return false;
	}
}
