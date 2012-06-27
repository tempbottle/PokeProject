package me.EdwJes.main;

import me.EdwJes.main.Entities.Entity;

public interface PlayerControlObject{
	public void onKeyLeft();
	public void onKeyRight();
	public void onKeyUp();
	public void onKeyDown();
	public void onKeyAction();
	public void onKeySkip();
	public Entity getEntity();
}
