package me.EdwJes.main;


import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class Keyboard implements KeyListener {
	
	public Keyboard(){
		
	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {

	}

	@Override
	public void keyPressed(int key, char chr) {
		PlayerInput.keyPress(key, chr);
	}

	
	@Override
	public void keyReleased(int key, char chr) {
		PlayerInput.keyRelease(key, chr);
	}

}
