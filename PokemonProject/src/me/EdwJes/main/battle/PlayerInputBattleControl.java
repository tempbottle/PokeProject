package me.EdwJes.main.battle;

import org.newdawn.slick.Input;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PlayerInputControlObject;

public class PlayerInputBattleControl implements PlayerInputControlObject{

	@Override
	public float getXPos() {
		return 0;
	}

	@Override
	public float getYPos() {
		return 0;
	}

	@Override
	public int getXTile() {
		return 0;
	}

	@Override
	public int getYTile() {
		return 0;
	}

	@Override
	public boolean isKeyRepeat(){
		return false;
	}

	@Override
	public void handleInput(Input input,PlayerInput player){
		// Auto-generated method stub
		
	}

	@Override
	public void onKeyPressed(int key,char chr,PlayerInput player){
		// Auto-generated method stub
		
	}

	@Override
	public void onKeyReleased(int key,char chr,PlayerInput player){
		// Auto-generated method stub
		
	}
}
