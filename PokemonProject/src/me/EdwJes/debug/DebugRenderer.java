package me.EdwJes.debug;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import me.EdwJes.main.GameObject;
import me.EdwJes.main.OverworldObject;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.RenderableObject;

public class DebugRenderer extends RenderableObject{
	
	public DebugRenderer(){
		setLayer(LAYER_GUI);
		setDepth(-5);
		setPersistency(true);
	}
	
	@Override
	public void render(Graphics g){
		if(Debug.renderDebug){
			List<String> l = new ArrayList<String>();
			l.add("FPS: " + PokemonProject.getFPS());
			l.add("GameObject: " + GameObject.list.size());
			l.add("RenderableObject: " + RenderableObject.list.size());
			l.add("Player coords: " + PokemonProject.player.getObj().getXPos() + "," + PokemonProject.player.getObj().getYPos() + " (" + PokemonProject.player.getObj().getXTile() + "," + PokemonProject.player.getObj().getYTile() + ")");
			l.add("Resolution: " +PokemonProject.app.getWidth()+","+PokemonProject.app.getHeight()+" ("+PokemonProject.SCREEN_WIDTH+","+PokemonProject.SCREEN_HEIGHT+")");
			drawList(g,l);}
		g.setColor(new Color(255, 255, 255, 10));
		for(int xi=0;xi<640;xi+=OverworldObject.getTileWidth())
			g.drawLine(xi, 0, xi, 640);
		for(int yi=0;yi<480;yi+=OverworldObject.getTileHeight())
			g.drawLine(0, yi, 480, yi);
		g.setColor(Color.white);
		
		/* FOR OVERWORLDOBJECT COLLISION MASK DRAW
		 if(isColliding())
			g.setColor(Color.red);
		for(int[] pos:collisionMask.getPoints())
			g.drawRect((getXTile()+pos[0])*tileWidth,(getYTile()+pos[1])*tileHeight,tileWidth,tileHeight);
		g.setColor(Color.white);*/
	}

	public void drawList(Graphics g, List<String> list){
		for(int i = 0; i < list.size(); i++){
			Color temp = g.getColor();
			g.setColor(Color.gray);
			g.drawString(list.get(i), 2+1, 2+i*18+1);
			g.setColor(temp);
			g.drawString(list.get(i), 2, 2+i*18);
		}
	}
}