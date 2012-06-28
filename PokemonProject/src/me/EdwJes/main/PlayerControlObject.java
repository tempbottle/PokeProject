package me.EdwJes.main;

import me.EdwJes.main.Entities.Entity;

public interface PlayerControlObject{
	public void onKeyLeft();
	public void onKeyRight();
	public void onKeyUp();
	public void onKeyDown();
	public void onKeyAction();
	public void onKeySkip();
	
	public void onKeyLeftPressed();
	public void onKeyRightPressed();
	public void onKeyUpPressed();
	public void onKeyDownPressed();
	public void onKeyActionPressed();
	public void onKeySkipPressed();
	
	public void onKeyLeftReleased();
	public void onKeyRightReleased();
	public void onKeyUpReleased();
	public void onKeyDownReleased();
	public void onKeyActionReleased();
	public void onKeySkipReleased();
	public Entity getEntity();
}
