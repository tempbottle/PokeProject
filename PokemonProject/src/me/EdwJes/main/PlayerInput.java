package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.debug.Debug;
import me.EdwJes.main.config.Config;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerInput extends GameObject{
	protected PlayerInputControlObject obj,objPrevious;
	protected static List<PlayerInput> list = new ArrayList<PlayerInput>();
	public int playerId=0;
	private Config config;

	public boolean keyHold[] = new boolean[256]; 
	Alarm alarm1 = new Alarm(120, this);
	
	public PlayerInput(PlayerInputControlObject obj,Config config){
		this.obj=obj;
		list.add(this);
		playerId=list.indexOf(this);
		this.config=config;
	}
	
	public void handleInput(GameContainer container){
		Input input = container.getInput();
		obj.onHandleInput(input);
		
		if(input.isKeyDown(config.getIntArray("KEY_LEFT",playerId))){
			obj.onKeyLeft();
			if(!keyHold[config.getIntArray("KEY_LEFT",playerId)]){
				keyHold[config.getIntArray("KEY_LEFT",playerId)]=true;
				obj.onKeyLeftPressed();}}
		else if(keyHold[config.getIntArray("KEY_LEFT",playerId)]){
			keyHold[config.getIntArray("KEY_LEFT",playerId)]=false;
			obj.onKeyLeftReleased();
		}
		
		if(input.isKeyDown(config.getIntArray("KEY_RIGHT",playerId))){
			obj.onKeyRight();
			if(!keyHold[config.getIntArray("KEY_RIGHT",playerId)]){
				keyHold[config.getIntArray("KEY_RIGHT",playerId)]=true;
				obj.onKeyRightPressed();}}
		else if(keyHold[config.getIntArray("KEY_RIGHT",playerId)]){
			keyHold[config.getIntArray("KEY_RIGHT",playerId)]=false;
			obj.onKeyRightReleased();
		}
		
		if(input.isKeyDown(config.getIntArray("KEY_UP",playerId))){
			obj.onKeyUp();
			if(!keyHold[config.getIntArray("KEY_UP",playerId)]){
				keyHold[config.getIntArray("KEY_UP",playerId)]=true;
				obj.onKeyUpPressed();}}
		else if(keyHold[config.getIntArray("KEY_UP",playerId)]){
			keyHold[config.getIntArray("KEY_UP",playerId)]=false;
			obj.onKeyUpReleased();
		}
		
		if(input.isKeyDown(config.getIntArray("KEY_DOWN",playerId))){
			obj.onKeyDown();
			if(!keyHold[config.getIntArray("KEY_DOWN",playerId)]){
				keyHold[config.getIntArray("KEY_DOWN",playerId)]=true;
				obj.onKeyDownPressed();}}
		else if(keyHold[config.getIntArray("KEY_DOWN",playerId)]){
			keyHold[config.getIntArray("KEY_DOWN",playerId)]=false;
			obj.onKeyDownReleased();
		}
		
		if(input.isKeyDown(config.getIntArray("KEY_ACTION",playerId))){
			obj.onKeyAction();
			if(!keyHold[config.getIntArray("KEY_ACTION",playerId)]){
				keyHold[config.getIntArray("KEY_ACTION",playerId)]=true;
				obj.onKeyActionPressed();}}
		else if(keyHold[config.getIntArray("KEY_ACTION",playerId)]){
			keyHold[config.getIntArray("KEY_ACTION",playerId)]=false;
			obj.onKeyActionReleased();
		}
		
		if(input.isKeyDown(config.getIntArray("KEY_SKIP",playerId))){
			obj.onKeySkip();
			if(!keyHold[config.getIntArray("KEY_SKIP",playerId)]){
				keyHold[config.getIntArray("KEY_SKIP",playerId)]=true;
				obj.onKeySkipPressed();}}
		else if(keyHold[config.getIntArray("KEY_SKIP",playerId)]){
			keyHold[config.getIntArray("KEY_SKIP",playerId)]=false;
			obj.onKeySkipReleased();
		}
		
		if(input.isKeyDown(config.getIntArray("KEY_ENTER",playerId))){
			obj.onKeyEnter();
			if(!keyHold[config.getIntArray("KEY_ENTER",playerId)]){
				keyHold[config.getIntArray("KEY_ENTER",playerId)]=true;
				obj.onKeyEnterPressed();}}
		else if(keyHold[config.getIntArray("KEY_ENTER",playerId)]){
			keyHold[config.getIntArray("KEY_ENTER",playerId)]=false;
			obj.onKeyEnterReleased();
		}
		
		if(input.isKeyDown(config.getIntArray("KEY_CHAT",playerId))){
			obj.onKeyChat();
			if(!keyHold[config.getIntArray("KEY_CHAT",playerId)]){
				keyHold[config.getIntArray("KEY_CHAT",playerId)]=true;
				obj.onKeyChatPressed();}}
		else if(keyHold[config.getIntArray("KEY_CHAT",playerId)]){
			keyHold[config.getIntArray("KEY_CHAT",playerId)]=false;
			obj.onKeyChatReleased();
		}
	}
	
	public void setObj(PlayerInputControlObject obj){
		this.objPrevious=this.obj;
		this.obj=obj;
	}
	
	public PlayerInputControlObject getObj(){
		return obj;
	}
	
	@Override
	public void onUpdate(){
		handleInput(PokemonProject.getContainer());}
	
	@Override public void callAlarm(Alarm alarm){
		super.callAlarm(alarm);
		if(alarm == alarm1)System.out.println("The alarm test was a success!");
	}
}
