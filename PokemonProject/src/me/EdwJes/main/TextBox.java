package me.EdwJes.main;

import me.EdwJes.main.config.Config;

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
	public void render(Graphics g,View view) {
		Font font=g.getFont();
		g.setColor(Color.darkGray);
		g.fillRect(4, view.viewHeight-font.getLineHeight()*2-4-view.getDrawScreenX(), view.viewWidth-8, font.getLineHeight()*2-view.getDrawScreenY());
		g.setColor(Color.white);
		g.drawString(text, 2-view.getDrawScreenX(), view.viewHeight-font.getLineHeight()*2-2-view.getDrawScreenY());
		
	}

/*	@Override
	public void onKeyActionPressed() {
		PokemonProject.player.setObj(PokemonProject.player.objPrevious);
	}*/

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
}
