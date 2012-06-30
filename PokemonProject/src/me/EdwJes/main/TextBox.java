package me.EdwJes.main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class TextBox extends RenderableObject implements PlayerInputControlObject{
	private String text;
	
	public TextBox(String text){
		this.text=text;
	}
	
	@Override
	public void render(Graphics g) {
		Font font=g.getFont();
		g.setColor(Color.darkGray);
		g.fillRect(4, PokemonProject.SCREEN_HEIGHT-font.getLineHeight()*2-4, PokemonProject.SCREEN_WIDTH-8, font.getLineHeight()*2);
		g.setColor(Color.white);
		g.drawString(text, 2, PokemonProject.SCREEN_HEIGHT-font.getLineHeight()*2-2);
		
	}

	@Override
	public void onKeyLeft() {
	}

	@Override
	public void onKeyRight() {
	}

	@Override
	public void onKeyUp() {
	}

	@Override
	public void onKeyDown() {
	}

	@Override
	public void onKeyAction() {

	}

	@Override
	public void onKeySkip() {
	}

	@Override
	public void onKeyEnter() {
	}

	@Override
	public void onKeyChat() {
	}

	@Override
	public void onKeyLeftPressed() {
	}

	@Override
	public void onKeyRightPressed() {
	}

	@Override
	public void onKeyUpPressed() {
	}

	@Override
	public void onKeyDownPressed() {
	}

	@Override
	public void onKeyActionPressed() {
		PokemonProject.player.setObj(PokemonProject.player.objPrevious);
	}

	@Override
	public void onKeySkipPressed() {
	}

	@Override
	public void onKeyEnterPressed() {
	}

	@Override
	public void onKeyChatPressed() {
	}

	@Override
	public void onKeyLeftReleased() {
	}

	@Override
	public void onKeyRightReleased() {
	}

	@Override
	public void onKeyUpReleased() {
	}

	@Override
	public void onKeyDownReleased() {
	}

	@Override
	public void onKeyActionReleased() {
	}

	@Override
	public void onKeySkipReleased() {
	}

	@Override
	public void onKeyEnterReleased() {
	}

	@Override
	public void onKeyChatReleased() {
	}

	@Override
	public void onKeyPressed(int key, char chr) {
	}

	@Override
	public void onKeyReleased(int key, char chr) {	
	}

	@Override
	public GameObject getControlledObject() {
		return null;
	}

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

}
