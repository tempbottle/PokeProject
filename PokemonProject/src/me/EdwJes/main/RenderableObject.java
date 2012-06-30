package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;

public abstract class RenderableObject extends GameObject{
	protected static List<RenderableObject> list = new ArrayList<RenderableObject>();
	protected int depth=0,layer=0;
	public final static int tileWidth=16,tileHeight=16;
	public final static int LAYER_GUI=1000,LAYER_OVERWORLD=10,LAYER_BACKGROUND=1;
	
	public abstract void render(Graphics g);
	
	public RenderableObject(){
		list.add(this);
	}
	
	public void destroy(){
		super.destroy();
		list.remove(this);
	}
	
	
	public static int getTileWidth(){
		return tileWidth;
	}
	
	public static int getTileHeight(){
		return tileHeight;
	}
	
	public void setDepth(int depth){
		this.depth=depth;
	}
	
	public void setLayer(int layer){
		this.layer=layer;
	}
}
