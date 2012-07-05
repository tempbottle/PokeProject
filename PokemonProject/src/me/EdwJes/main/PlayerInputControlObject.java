package me.EdwJes.main;

import org.newdawn.slick.Input;

public interface PlayerInputControlObject{
	public void handleInput(Input input,int playerId);
	
	public void onKeyPressed(int key,char chr,int playerId);
	public void onKeyReleased(int key,char chr,int playerId);
	
	public float getXPos();
	public float getYPos();
	public int getXTile();
	public int getYTile();
	
}
