package me.EdwJes.main;

import me.EdwJes.main.ImageLoader.Name;
import me.EdwJes.main.Entities.EntityPlayer;
import me.EdwJes.main.EntityControl.PlayerEntityControl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Player extends GameObject{
	public PlayerInputControlObject obj;
	public int KEY_LEFT   = Input.KEY_LEFT,
	           KEY_RIGHT  = Input.KEY_RIGHT,
	           KEY_UP     = Input.KEY_UP,
	           KEY_DOWN   = Input.KEY_DOWN,
	           KEY_ACTION = Input.KEY_X,
	           KEY_SKIP   = Input.KEY_Z,
	           KEY_PAUSE  = Input.KEY_ENTER,
	           KEY_EXIT   = Input.KEY_ESCAPE;
	public boolean keyHold[] = new boolean[256]; 
	Alarm alarm1 = new Alarm(120, this);
	
	public Player(){
		obj=new PlayerEntityControl(new EntityPlayer(2,6,PokemonProject.IMAGE_LOADER.anim[Name.Brendan.get()]));
	}
	
	public void handleInput(GameContainer container){
		Input input = container.getInput();

		if(input.isKeyDown(KEY_LEFT)){
			obj.onKeyLeft();
			if(!keyHold[KEY_LEFT]){
				keyHold[KEY_LEFT]=true;
				obj.onKeyLeftPressed();}}
		else if(keyHold[KEY_LEFT]){
			keyHold[KEY_LEFT]=false;
			obj.onKeyLeftReleased();
		}
		
		if(input.isKeyDown(KEY_RIGHT)){
			obj.onKeyRight();
			if(!keyHold[KEY_RIGHT]){
				keyHold[KEY_RIGHT]=true;
				obj.onKeyRightPressed();}}
		else if(keyHold[KEY_RIGHT]){
			keyHold[KEY_RIGHT]=false;
			obj.onKeyRightReleased();
		}
		
		if(input.isKeyDown(KEY_UP)){
			obj.onKeyUp();
			if(!keyHold[KEY_UP]){
				keyHold[KEY_UP]=true;
				obj.onKeyUpPressed();}}
		else if(keyHold[KEY_UP]){
			keyHold[KEY_UP]=false;
			obj.onKeyUpReleased();
		}
		
		if(input.isKeyDown(KEY_DOWN)){
			obj.onKeyDown();
			if(!keyHold[KEY_DOWN]){
				keyHold[KEY_DOWN]=true;
				obj.onKeyDownPressed();}}
		else if(keyHold[KEY_DOWN]){
			keyHold[KEY_DOWN]=false;
			obj.onKeyDownReleased();
		}
		
		if(input.isKeyDown(KEY_ACTION)){
			obj.onKeyAction();
			if(!keyHold[KEY_ACTION]){
				keyHold[KEY_ACTION]=true;
				obj.onKeyActionPressed();}}
		else if(keyHold[KEY_ACTION]){
			keyHold[KEY_ACTION]=false;
			obj.onKeyActionReleased();
		}
		
		if(input.isKeyDown(KEY_SKIP)){
			obj.onKeySkip();
			if(!keyHold[KEY_SKIP]){
				keyHold[KEY_SKIP]=true;
				obj.onKeySkipPressed();}}
		else if(keyHold[KEY_SKIP]){
			keyHold[KEY_SKIP]=false;
			obj.onKeySkipReleased();
		}
	}
	
	@Override
	public void onUpdate(){
		handleInput(PokemonProject.container);}
	
	@Override public void callAlarm(Alarm alarm){
		super.callAlarm(alarm);
		if(alarm == alarm1)System.out.println("The alarm test was a success!");
	}
}
