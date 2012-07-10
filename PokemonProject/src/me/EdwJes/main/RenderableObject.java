package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;

public abstract class RenderableObject extends GameObject{
	protected static List<RenderableObject> list = new ArrayList<RenderableObject>();
	public View view=null;
	public final static int LAYER_GUI=1000,LAYER_NORMAL=10,LAYER_BACKGROUND=-10,LAYER_BELOWNORMAL=-5;
	protected int depth=0,layer=LAYER_NORMAL;
	
	public abstract void render(Graphics g,View view);
	
	public RenderableObject(){
		list.add(this);
	}
	
	public void destroy(){
		super.destroy();
		list.remove(this);
	}
	
	public void setDepth(int depth){
		this.depth=depth;
	}
	
	public void setLayer(int layer){
		this.layer=layer;
	}

	public static List<RenderableObject> getList() {
		return list;
	}

	public static void setList(List<RenderableObject> list) {
		RenderableObject.list = list;
	}
	
	public static String strWrap(String in,int len) {
		in=in.trim();
		if(in.length()<len) return in;
		if(in.substring(0, len).contains("\n"))
		return in.substring(0, in.indexOf("\n")).trim() + "\n\n" + strWrap(in.substring(in.indexOf("\n") + 1), len);
		int place=Math.max(Math.max(in.lastIndexOf(" ",len),in.lastIndexOf("\t",len)),in.lastIndexOf("-",len));
		return in.substring(0,place).trim()+"\n"+strWrap(in.substring(place),len);
		}
}
