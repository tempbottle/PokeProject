package me.EdwJes.debug;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import me.EdwJes.main.GameObject;
import me.EdwJes.main.OverworldObject;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.RenderableObject;
import me.EdwJes.main.View;

public class DebugRenderer extends RenderableObject{
	
	public DebugRenderer(){
		setLayer(LAYER_GUI);
		setDepth(-5);
		setPersistency(true);
	}
	
	@Override
	public void render(Graphics g,View view){
		if(Debug.renderDebug){
			PlayerInput player=PlayerInput.getPlayerInput(0);
			List<String> l = new ArrayList<String>();
			l.add("FPS: " + PokemonProject.getFPS());
			l.add("GameObject: " + GameObject.list.size());
			l.add("RenderableObject: " + RenderableObject.list.size());
			l.add("Player coords: " + player.getObj().getXPos() + "," + player.getObj().getYPos() + " (" + player.getObj().getXTile() + "," + player.getObj().getYTile() + ")");
			l.add("Resolution: " +PokemonProject.app.getWidth()+","+PokemonProject.app.getHeight()+" ("+player.view.viewWidth+","+player.view.viewHeight+")");
			l.add("View: " +view.getDrawX()+","+view.getDrawY());
			drawList(g,l,view);}
		g.setColor(new Color(255, 255, 255, 10));
		
		for(int xi=0;xi<640;xi+=OverworldObject.getTileWidth())
			g.drawLine(xi-view.getDrawX(), 0-view.getDrawY(), xi-view.getDrawX(), 480-view.getDrawY());
		for(int yi=0;yi<480;yi+=OverworldObject.getTileHeight())
			g.drawLine(0-view.getDrawX(), yi-view.getDrawY(), 640-view.getDrawX(), yi-view.getDrawY());
		g.setColor(Color.white);
		
		/* FOR OVERWORLDOBJECT COLLISION MASK DRAW
		 if(isColliding())
			g.setColor(Color.red);
		for(int[] pos:collisionMask.getPoints())
			g.drawRect((getXTile()+pos[0])*tileWidth,(getYTile()+pos[1])*tileHeight,tileWidth,tileHeight);
		g.setColor(Color.white);*/
	}

	public void drawList(Graphics g, List<String> list,View view){
		for(int i = 0; i < list.size(); i++){
			g.drawString(list.get(i), 2-view.getDrawScreenX(), (2+i*18)-view.getDrawScreenY());
		}
	}
}