package me.EdwJes.main;

import org.newdawn.slick.Input;

public interface PlayerInputControlObject{
	public void handleInput(Input input,PlayerInput playerInput);
	
	public void onKeyPressed(int key,char chr,PlayerInput playerInput);
	public void onKeyReleased(int key,char chr,PlayerInput playerInput);
	
	public float getXPos();
	public float getYPos();
	public int getXTile();
	public int getYTile();
	
	public boolean isKeyRepeat();
	
}
