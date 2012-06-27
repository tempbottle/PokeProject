package me.EdwJes.main;

import me.EdwJes.main.Entities.Trainer;
import me.EdwJes.main.EntityControl.PlayerEntity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Player extends GameObject{
	public PlayerControlObject obj=new PlayerEntity(new Trainer(2,6));
	public int KEY_LEFT   = Input.KEY_LEFT,
	           KEY_RIGHT  = Input.KEY_RIGHT,
	           KEY_UP     = Input.KEY_UP,
	           KEY_DOWN   = Input.KEY_DOWN,
	           KEY_ACTION = Input.KEY_X,
	           KEY_SKIP   = Input.KEY_Z,
	           KEY_PAUSE  = Input.KEY_ENTER,
	           KEY_EXIT   = Input.KEY_ESCAPE;
	
	public void handleInput(GameContainer container){
		Input input = container.getInput();

		if(input.isKeyDown(KEY_LEFT)){
			obj.onKeyLeft();}
		
		if(input.isKeyDown(KEY_RIGHT)){
			obj.onKeyRight();}
		
		if(input.isKeyDown(KEY_UP)){
			obj.onKeyUp();}
		
		if(input.isKeyDown(KEY_DOWN)){
			obj.onKeyDown();}
		
		if(input.isKeyDown(KEY_ACTION)){
			obj.onKeyAction();}
		
		if(input.isKeyDown(KEY_SKIP)){
			obj.onKeySkip();}
	}
	
	@Override
	public void onUpdate(){
		handleInput(PokemonProject.container);}
}
