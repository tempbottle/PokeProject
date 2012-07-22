package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;

public abstract class RenderableWindowObject extends GameObject{
	protected static List<RenderableWindowObject> list = new ArrayList<RenderableWindowObject>();
	
	public RenderableWindowObject(){
		list.add(this);
	}
	
	public void destroy(){
		super.destroy();
		list.remove(this);
	}
	
	public abstract void render(Graphics g);

}
