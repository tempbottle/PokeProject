package me.EdwJes.main.EntityControl;

import org.newdawn.slick.Input;
import me.EdwJes.main.GameObject;
import me.EdwJes.main.OverworldObject;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PlayerInputControlObject;
import me.EdwJes.main.Entities.Entity;
import me.EdwJes.main.Entities.Entity.Direction;
import me.EdwJes.main.Entities.Entity.DirectionX;
import me.EdwJes.main.Entities.Entity.DirectionY;
import me.EdwJes.main.Entities.Entity.Movement;
import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.Config.Key;

public class PlayerInputEntityControl extends EntityControl implements PlayerInputControlObject{
	private int beginMovementTimer=0,beginMovementTime=10;
	
	public PlayerInputEntityControl(Entity entity) {
		super(entity);
	}
	//TODO: Lätt-tryck för att vända sig istället för att röra sig direkt när man har vänt sig, dir variabeln. Får göra en timer eller något
	
	@Override
	public void handleInput(Input input,int playerId,Config config){
		if(input.isKeyDown(configGetKeymap(config,playerId,Key.LEFT))){
			if(beginMovementTimer<beginMovementTime)
				beginMovementTimer++;
			else
			entity.posMoveX(DirectionX.LEFT);}
		else if(input.isKeyDown(configGetKeymap(config,playerId,Key.RIGHT))){
			if(beginMovementTimer<beginMovementTime)
				beginMovementTimer++;
			else
			entity.posMoveX(DirectionX.RIGHT);}
		else if(input.isKeyDown(configGetKeymap(config,playerId,Key.UP))){
			if(beginMovementTimer<beginMovementTime)
				beginMovementTimer++;
			else
			entity.posMoveY(DirectionY.UP);}
		else if(input.isKeyDown(configGetKeymap(config,playerId,Key.DOWN))){
			if(beginMovementTimer<beginMovementTime)
				beginMovementTimer++;
			else
				entity.posMoveY(DirectionY.DOWN);}
		
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
		if(key==configGetKeymap(config,playerId,Key.ACTION)){
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
}
