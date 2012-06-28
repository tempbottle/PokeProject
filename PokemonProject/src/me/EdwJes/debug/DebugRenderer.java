package me.EdwJes.debug;

import java.util.ArrayList;
import java.util.List;

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
		l.add("Player coords: " + PokemonProject.player.obj.getEntity().getXPos() + "," + PokemonProject.player.obj.getEntity().getYPos() + " (" + PokemonProject.player.obj.getEntity().getXTile() + "," + PokemonProject.player.obj.getEntity().getYTile() + ")");
		drawList(g,l);
	}

	public void drawList(Graphics g, List<String> list){
		g.setFont(PokemonProject.font);
		for(int i = 0; i < list.size(); i++){
			g.drawString(list.get(i), 0, i*18);
		}
	}
}