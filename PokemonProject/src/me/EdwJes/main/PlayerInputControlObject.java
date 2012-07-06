package me.EdwJes.main;

import me.EdwJes.main.config.Config;
import org.newdawn.slick.Input;

public interface PlayerInputControlObject{
	public void handleInput(Input input,int playerId,Config config);
	
	public void onKeyPressed(int key,char chr,int playerId,Config config);
	public void onKeyReleased(int key,char chr,int playerId,Config config);
	
	public float getXPos();
	public float getYPos();
	public int getXTile();
	public int getYTile();
	
}
