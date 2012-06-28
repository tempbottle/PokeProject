package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;

public abstract class RenderableObject extends GameObject{
	protected static List<RenderableObject> list = new ArrayList<RenderableObject>();
	protected int depth = 0;
	public final static int tileWidth=16,tileHeight=16;
	
	public abstract void render(Graphics g);
	
	public RenderableObject(){
		list.add(this);
	}
	
	public void setDepth(int depth){
		this.depth=depth;
	}
}
