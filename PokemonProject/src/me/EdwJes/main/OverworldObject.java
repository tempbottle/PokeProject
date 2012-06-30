package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;

public class OverworldObject extends RenderableObject{
	protected static List<RenderableObject> list = new ArrayList<RenderableObject>();
	protected int depth = 0;
	public boolean solid=true;

	public OverworldObject(){
		list.add(this);
	}
	
	public void destroy(){
		super.destroy();
		list.remove(this);
	}
	
	@Override
	public void render(Graphics g) {
		
	}
}
