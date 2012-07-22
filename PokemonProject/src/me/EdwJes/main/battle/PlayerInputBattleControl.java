package me.EdwJes.main.battle;

import org.newdawn.slick.Input;
import me.EdwJes.main.PlayerInputControlObject;
import me.EdwJes.main.config.Config;

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
	public void handleInput(Input input, int playerId,Config config) {

	}

	@Override
	public void onKeyPressed(int key, char chr, int playerId,Config config) {

	}

	@Override
	public void onKeyReleased(int key, char chr, int playerId,Config config) {

	}

	@Override
	public boolean isKeyRepeat(){
		return false;
	}
}
