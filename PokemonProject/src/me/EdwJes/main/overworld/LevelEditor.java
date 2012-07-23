package me.EdwJes.main.overworld;

import org.newdawn.slick.Input;
import me.EdwJes.main.GameObject;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PlayerInputControlObject;

public class LevelEditor extends GameObject implements PlayerInputControlObject{
	public LevelEditor(){
		//Image shit=PokemonProject.roomLoader. .TESTTILE;
	}
	
	@Override
	public float getXPos(){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYPos(){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getXTile(){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getYTile(){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isKeyRepeat(){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleInput(Input input,PlayerInput playerInput){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPressed(int key,char chr,PlayerInput playerInput){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyReleased(int key,char chr,PlayerInput playerInput){
		// TODO Auto-generated method stub
		
	}
}
