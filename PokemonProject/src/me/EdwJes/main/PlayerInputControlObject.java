package me.EdwJes.main;

import org.newdawn.slick.Input;

public interface PlayerInputControlObject{
	public void onKeyLeft();
	public void onKeyRight();
	public void onKeyUp();
	public void onKeyDown();
	public void onKeyAction();
	public void onKeySkip();
	public void onKeyEnter();
	public void onKeyChat();
	
	public void onKeyLeftPressed();
	public void onKeyRightPressed();
	public void onKeyUpPressed();
	public void onKeyDownPressed();
	public void onKeyActionPressed();
	public void onKeySkipPressed();
	public void onKeyEnterPressed();
	public void onKeyChatPressed();
	
	public void onKeyLeftReleased();
	public void onKeyRightReleased();
	public void onKeyUpReleased();
	public void onKeyDownReleased();
	public void onKeyActionReleased();
	public void onKeySkipReleased();
	public void onKeyEnterReleased();
	public void onKeyChatReleased();
	
	public void onKeyPressed(int key,char chr);
	public void onKeyReleased(int key,char chr);
	
	public GameObject getControlledObject();
	
	public float getXPos();
	public float getYPos();
	public int getXTile();
	public int getYTile();
	
}
