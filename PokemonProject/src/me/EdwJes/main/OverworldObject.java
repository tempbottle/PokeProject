package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;
import me.EdwJes.main.Entities.Entity;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class OverworldObject extends RenderableObject{
	public static List<OverworldObject> list = new ArrayList<OverworldObject>();
	protected int xTile,yTile,depth = 0;
	public boolean solid=true;
	public CollisionMask collisionMask = new CollisionMask(0,0);
	public final static int tileWidth=16,tileHeight=16;
	public boolean canInteracted=false;
	public Image imageSpr;

	public OverworldObject(int xTile,int yTile){
		list.add(this);
		this.xTile=xTile;
		this.yTile=yTile;
	}
	
	public void update(){
		super.update();
		collisionMask.setLocation(xTile,yTile);
	}
	
	public void destroy(){
		super.destroy();
		list.remove(this);
	}
	
	@Override
	public void render(Graphics g,View view) {
		if(imageSpr!=null)
			g.drawImage(imageSpr, getXPos()-view.getDrawX(), getYPos()-view.getDrawY());
		/*g.setColor(Color.red);
		g.drawRect((collisionMask.getMinX())*tileWidth,(collisionMask.getMinY())*tileHeight,(collisionMask.getMaxX()-collisionMask.getMinX()+1)*tileWidth,(collisionMask.getMaxY()-collisionMask.getMinY()+1)*tileHeight);
		g.setColor(Color.white);*/
	}
	
	/**
	  * Sets the x value of the entity in tiles
	  * 
	  * @param xTile x value in tiles
	  */
	public void posSetX(int xTile){
		this.xTile=xTile;}
	
	/**
	  * Sets the y value of the entity in tiles
	  * 
	  * @param yTile y value in tiles
	  */
	public void posSetY(int yTile){
		this.yTile=yTile;}
	
	public void posMoveX(int xTile){
		if(!isObjInPos(this.xTile+xTile,this.yTile))
			this.xTile+=xTile;
	}
	
	public void posMoveY(int yTile){
		if(!isObjInPos(this.xTile,this.yTile+yTile))
			this.yTile+=yTile;
	}
	
	public float getXPos(){
		return xTile*tileWidth;
	}

	/**
	  * Returns the y-position of the entity in the room
	  * 
	  * @return y 
	  */
	public float getYPos(){
		return yTile*tileHeight;
	}
	
	/**
	  * Returns the x-tile following the grid of the entity in the room
	  * 
	  * @return x 
	  */
	public int getXTile(){
		return xTile;
	}

	/**
	  * Returns the y-tile following the grid of the entity in the room
	  * 
	  * @return y 
	  */
	public int getYTile(){
		return yTile;
	}
	
	public static int getTileWidth(){
		return tileWidth;
	}
	
	public static int getTileHeight(){
		return tileHeight;
	}
	
	public static boolean isObjInPos(int xTile,int yTile){
		for(OverworldObject o:list){
			if(!o.isActivated())continue;
			if(o.collisionMask.occupies(xTile,yTile))
				return true;}
		return false;
	}
	
	public static boolean isShapeColliding(CollisionMask mask){
		for(OverworldObject o:list){
			if(o.collisionMask==mask||!o.isActivated())continue;
			if(mask.inside(o.collisionMask))
				return true;}
		return false;
	}
	
	public boolean isColliding(){
		for(OverworldObject o:list){
			if(o==this||!o.isActivated())continue;
			if(collisionMask.inside(o.collisionMask))
				return true;}
		return false;
	}
	
	public boolean isColliding(int x,int y){
		CollisionMask mask=new CollisionMask(0,0);
		mask.setX(x);
		mask.setY(y);
		for(OverworldObject o:list){
			if(o==this||!o.isActivated())continue;
			if(mask.inside(o.collisionMask))
				return true;}
		return false;
	}
	
	public static OverworldObject getObjInPos(int xTile,int yTile){
		for(OverworldObject o:list){
			if(!o.isActivated())continue;
			if(o.collisionMask.occupies(xTile,yTile))
				return o;}
		return null;
	}
	
	public boolean isCollidingObj(OverworldObject obj){
		if(collisionMask.inside(obj.collisionMask))
			return true;
		return false;
	}
	
	public OverworldObject getCollidingObj(){
		for(OverworldObject o:list){
			if(o==this||!o.isActivated())continue;
			if(collisionMask.inside(o.collisionMask))
				return o;}
		return null;
	}
	
	public OverworldObject getCollidingObj(int x,int y){
		CollisionMask mask=new CollisionMask(x,y);
		for(OverworldObject o:list){
			if(o==this||!o.isActivated())continue;
			if(mask.inside(o.collisionMask))
				return o;}
		return null;
	}
	
	public void onInteracted(Entity interactor){}
	public void onInteracted(Entity interactor,PlayerInput player){}
	
}
