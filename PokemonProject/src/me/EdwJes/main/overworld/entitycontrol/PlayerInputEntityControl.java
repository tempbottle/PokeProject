package me.EdwJes.main.overworld.entitycontrol;

import org.newdawn.slick.Input;
import me.EdwJes.main.GameObject;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PlayerInputControlObject;
import me.EdwJes.main.config.Config;
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
	public void handleInput(Input input,int playerId,Config config){
		this.input=input;
		
		if(input.isKeyDown(configGetKeymap(config,playerId,Key.LEFT))){
			if(beginMovementTimer>=beginMovementTime||beginDir==Direction.LEFT){
				entity.posMoveX(DirectionX.LEFT);}
			else
				beginMovementTimer++;}
		else if(input.isKeyDown(configGetKeymap(config,playerId,Key.RIGHT))){
			if(beginMovementTimer>=beginMovementTime||beginDir==Direction.RIGHT){
				entity.posMoveX(DirectionX.RIGHT);}
			else
				beginMovementTimer++;}
		else if(input.isKeyDown(configGetKeymap(config,playerId,Key.UP))){
			if(beginMovementTimer>=beginMovementTime||beginDir==Direction.UP){
				entity.posMoveY(DirectionY.UP);}
			else
				beginMovementTimer++;}
		else if(input.isKeyDown(configGetKeymap(config,playerId,Key.DOWN))){
			if(beginMovementTimer>=beginMovementTime||beginDir==Direction.DOWN){
				entity.posMoveY(DirectionY.DOWN);}
			else
				beginMovementTimer++;}
		
		if(input.isKeyPressed(Input.KEY_F5)){
			PlayerInput player=PlayerInput.getPlayerInput(playerId);
			if(player.view.followsObject==null)
				player.view.followsObject=entity;
			else
				player.view.followsObject=null;
		}
	}

	public GameObject getControlledObject() {
		return entity;
	}

	private int configGetKeymap(Config config,int playerId,Key key){
		return config.player.get(playerId).keyMap.get(key);
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
	public void onKeyPressed(int key, char chr,int playerId,Config config) {
		if(key==configGetKeymap(config,playerId,Key.LEFT)){
			if(!entity.isMoving()){
				beginMovementTimer=0;
				beginDir=entity.dir;
				entity.dir=Direction.LEFT;
			}
		}
		else if(key==configGetKeymap(config,playerId,Key.RIGHT)){
			if(!entity.isMoving()){
				beginMovementTimer=0;
				beginDir=entity.dir;
				entity.dir=Direction.RIGHT;
			}
		}
		else if(key==configGetKeymap(config,playerId,Key.UP)){
			if(!entity.isMoving()){
				beginMovementTimer=0;
				beginDir=entity.dir;
				entity.dir=Direction.UP;
			}
		}
		else if(key==configGetKeymap(config,playerId,Key.DOWN)){
			if(!entity.isMoving()){
				beginMovementTimer=0;
				beginDir=entity.dir;
				entity.dir=Direction.DOWN;
			}
		}
		
		else if(key==configGetKeymap(config,playerId,Key.ACTION)){
			if(!entity.isDirectionFree(entity.dir)){
				OverworldObject target=entity.getObjCollidedDir(entity.dir);
				entity.interact(target,playerId);}
		}
		
		else if(key==configGetKeymap(config,playerId,Key.SKIP)){
			entity.setPosMoveSpeed(entity.runningSpeed);
		}
		
		else if(key==configGetKeymap(config,playerId,Key.CHAT)){
			PlayerInput playerInput=PlayerInput.getPlayerInput(playerId);
			if(!playerInput.view.cmd.isOn()){
				playerInput.setObj(playerInput.view.cmd);
				playerInput.view.cmd.enterConsole();}
		}
	}
	@Override
	public void onKeyReleased(int key, char chr,int playerId,Config config) {
		if(key==configGetKeymap(config,playerId,Key.LEFT)){
			if(entity.getStopMove()==Movement.NONE){
				entity.stopMove(Direction.LEFT);}
		}
		
		else if(key==configGetKeymap(config,playerId,Key.RIGHT)){
			if(entity.getStopMove()==Movement.NONE){
				entity.stopMove(Direction.RIGHT);}
		}
		
		else if(key==configGetKeymap(config,playerId,Key.UP)){
			if(entity.getStopMove()==Movement.NONE){
				entity.stopMove(Direction.UP);}
		}
		
		else if(key==configGetKeymap(config,playerId,Key.DOWN)){
			if(entity.getStopMove()==Movement.NONE){
				entity.stopMove(Direction.DOWN);}
		}
		
		else if(key==configGetKeymap(config,playerId,Key.SKIP)){
			entity.setPosMoveSpeed(entity.walkingSpeed);
		}
	}

	@Override
	public boolean isKeyRepeat(){
		return false;
	}
}
