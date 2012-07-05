package me.EdwJes.main.EntityControl;

import org.newdawn.slick.Input;
import me.EdwJes.main.GameObject;
import me.EdwJes.main.OverworldObject;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PlayerInputControlObject;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.Entities.Entity;
import me.EdwJes.main.Entities.Entity.Direction;
import me.EdwJes.main.Entities.Entity.DirectionX;
import me.EdwJes.main.Entities.Entity.DirectionY;
import me.EdwJes.main.Entities.Entity.Movement;
import me.EdwJes.main.config.Config;

public class PlayerInputEntityControl implements PlayerInputControlObject,EntityControl{
	private Entity entity;
	private Config config=PokemonProject.config;
	
	public PlayerInputEntityControl(Entity entity) {
		this.entity=entity;
	}
	//TODO: Lätt-tryck för att vända sig istället för att röra sig direkt när man har vänt sig, dir variabeln. Får göra en timer eller något
	
	@Override
	public void handleInput(Input input,int playerId){
		if(input.isKeyDown(config.getIntArray("KEY_LEFT",playerId)))
			entity.posMoveX(DirectionX.LEFT);
		else if(input.isKeyDown(config.getIntArray("KEY_RIGHT",playerId)))
			entity.posMoveX(DirectionX.RIGHT);
		else if(input.isKeyDown(config.getIntArray("KEY_UP",playerId)))
			entity.posMoveY(DirectionY.UP);
		else if(input.isKeyDown(config.getIntArray("KEY_DOWN",playerId)))
			entity.posMoveY(DirectionY.DOWN);
		
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

	public int configGetKeymap(Config config,int playerId,String key){
		return config.getIntArray(key,playerId);
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
	public void onKeyPressed(int key, char chr,int playerId) {
		if(key==configGetKeymap(config,playerId,"KEY_ACTION")){
			if(!entity.isDirectionFree(entity.dir)){
				OverworldObject target=entity.getObjCollidedDir(entity.dir);
				entity.interact(target);}
		}
		
		else if(key==configGetKeymap(config,playerId,"KEY_SKIP")){
			entity.setPosMoveSpeed(entity.runningSpeed);
		}
		
		else if(key==configGetKeymap(config,playerId,"KEY_CHAT")){
			PlayerInput playerInput=PlayerInput.getPlayerInput(playerId);
			if(!playerInput.view.cmd.isOn()){
				playerInput.setObj(playerInput.view.cmd);
				playerInput.view.cmd.enterConsole();}
		}
		
	}
	@Override
	public void onKeyReleased(int key, char chr,int playerId) {
		if(key==configGetKeymap(config,playerId,"KEY_LEFT")){
			if(entity.getStopMove()==Movement.NONE)
				entity.stopMove(Direction.LEFT);
		}
		
		else if(key==configGetKeymap(config,playerId,"KEY_RIGHT")){
			if(entity.getStopMove()==Movement.NONE)
				entity.stopMove(Direction.RIGHT);
		}
		
		else if(key==configGetKeymap(config,playerId,"KEY_UP")){
			if(entity.getStopMove()==Movement.NONE)
				entity.stopMove(Direction.UP);
		}
		
		else if(key==configGetKeymap(config,playerId,"KEY_DOWN")){
			if(entity.getStopMove()==Movement.NONE)
				entity.stopMove(Direction.DOWN);
		}
		
		else if(key==configGetKeymap(config,playerId,"KEY_SKIP")){
			entity.setPosMoveSpeed(entity.walkingSpeed);
		}
	}
}
