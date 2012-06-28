package me.EdwJes.debug;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import me.EdwJes.main.GameObject;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.RenderableObject;

public class DebugRenderer extends RenderableObject{
	
	public DebugRenderer(){
		setDepth(100);
	}
	
	@Override
	public void render(Graphics g) {
		List<String> l = new ArrayList<String>();
		l.add("FPS: " + PokemonProject.container.getFPS());
		l.add("GameObject: " + GameObject.list.size());
		l.add("RenderableObject: " + RenderableObject.list.size());
		l.add("Player coords: " + PokemonProject.player.getObj().getXPos() + "," + PokemonProject.player.getObj().getYPos() + " (" + PokemonProject.player.getObj().getXTile() + "," + PokemonProject.player.getObj().getYTile() + ")");
		l.add("Resolution: " +PokemonProject.app.getWidth()+","+PokemonProject.app.getHeight()+" ("+PokemonProject.SCREEN_WIDTH+","+PokemonProject.SCREEN_HEIGHT+")");
		drawList(g,l);
	}

	public void drawList(Graphics g, List<String> list){
		g.setFont(PokemonProject.font);
		for(int i = 0; i < list.size(); i++){
			Color temp = g.getColor();
			g.setColor(Color.gray);
			g.drawString(list.get(i), 0+1, i*18+1);
			g.setColor(temp);
			g.drawString(list.get(i), 0, i*18);
		}
	}
}