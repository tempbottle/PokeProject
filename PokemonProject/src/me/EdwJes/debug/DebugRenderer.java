package me.EdwJes.debug;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import me.EdwJes.Main;
import me.EdwJes.main.GameObject;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PokemonGame;
import me.EdwJes.main.RenderableObject;
import me.EdwJes.main.Updater;
import me.EdwJes.main.View;
import me.EdwJes.main.overworld.OverworldObject;

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
			l.add("FPS: " + PokemonGame.getFPS());
			l.add("Updaters: " + Updater.list.size());
			l.add("GameObjects: " + GameObject.list.size());
			l.add("RenderableObjects: " + RenderableObject.list.size());
			l.add("Player coords: " + player.getObj().getXPos() + "," + player.getObj().getYPos() + " (" + player.getObj().getXTile() + "," + player.getObj().getYTile() + ")");
			l.add("Resolution: " +Main.getContainer().getWidth()+","+Main.getContainer().getHeight()+" ("+player.view.viewWidth+","+player.view.viewHeight+")");
			l.add("View: " +view.getDrawX()+","+view.getDrawY());
			drawList(g,l);}
		g.setColor(new Color(255, 255, 255, 10));
		
		for(int xi=0;xi<640;xi+=OverworldObject.getTileWidth())
			g.drawLine(xi, 0, xi, 480);
		for(int yi=0;yi<480;yi+=OverworldObject.getTileHeight())
			g.drawLine(0, yi, 640, yi);
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
			g.drawString(list.get(i), 2, 2+i*18);
		}
	}
}