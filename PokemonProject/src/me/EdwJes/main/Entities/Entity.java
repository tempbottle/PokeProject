package me.EdwJes.main.Entities;

import me.EdwJes.main.RenderableObject;
import org.newdawn.slick.Graphics;

/**
* Entity Model Object.
* 
* <P>No comment
*  
* <P>Testing links, class: {@link BigDecimal} 
* See constructor {@link #posMoveX(int)} for more information.
*  
* @author Lolirofle
* @version 1.0
*/
public class Entity extends RenderableObject{
	protected int xTile,yTile,moveXTile=0,moveYTile=0;
	private float moveXOffset=0,moveYOffset=0,posMoveSpeed=2;
	
	public Entity(int xTile,int yTile/*,EntitySprite sprite*/){
		this.xTile=xTile;
		this.yTile=yTile;
	}

	
	/**
	  * Changes the smooth movement speed when the {@link #posMoveX} and {@link #posMoveY} contructors are used
	  * 
	  * @param speed Float-point movement in pixels per update
	  */
	public void setPosMoveSpeed(float speed){
		posMoveSpeed=speed;}
	
	/**
	  * Returns the smooth movement speed when the {@link #posMoveX} and {@link #posMoveY} contructors are used
	  * 
	  * @return Float-point movement in pixels per update
	  */
	public float getPosMoveSpeed(){
		return posMoveSpeed;}
	
	/**
	  * Moves the entity relatively to it's position
	  * 
	  * @param xTile x value in tiles
	  */
	public boolean posMoveX(int xTile){
		if(!isMoving()){
			moveXTile=xTile;
			onMoveTile(xTile,this.yTile);
			return true;}
		else
			return false;
	}
	
	/**
	  * Moves the entity relatively to it's position
	  * 
	  * @param yTile y value in tiles
	  */
	public boolean posMoveY(int yTile){
		if(!isMoving()){
			moveYTile=yTile;
			onMoveTile(this.xTile,yTile);
			return true;}
		else
			return false;
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
	
	/**
	  * Is the entity moving?
	  * 
	  * @return <tt>true</tt> if moving<br/><tt>false</tt> if standing still 
	  */
	public boolean isMoving(){
		if(moveXTile!=0||moveYTile!=0)
			return true;
		else
			return false;
	}
	
	/**
	  * Returns the on-going horizontal movement in tiles
	  * 
	  * @return 0 if no movement<br/><0 if left<br/>>0 if right 
	  */
	public int getMovementX(){
		return moveXTile;}

	/**
	  * Returns the on-going vertical movement in tiles
	  * 
	  * @return 0 if no movement<br/><0 if up<br/>>0 if down 
	  */
	public int getMovementY(){
		return moveYTile;}
	
	/**
	  * Returns the x-position of the entity in the room
	  * 
	  * @return x 
	  */
	public float getXPos(){
		return xTile*tileWidth+moveXOffset;
	}

	/**
	  * Returns the y-position of the entity in the room
	  * 
	  * @return y 
	  */
	public float getYPos(){
		return yTile*tileHeight+moveYOffset;
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
	
	public void onInteracted(Entity interactor){}
	public void onInteract(Entity target){}
	public void onMoveTile(int xTileTo,int yTileTo){}
	public void onUpdate(){}
	public void onRender(Graphics g){}
	
	@Override
	public void update(){
		onUpdate();
		if(getMovementX()!=0){
			if(Math.abs(moveXOffset)<Math.abs(moveXTile*tileWidth))
				moveXOffset+=posMoveSpeed*Math.signum(moveXTile);
			else{
				xTile+=moveXTile;
				moveXTile=0;
				moveXOffset=0;}
		}
		else if(getMovementY()!=0){
			if(Math.abs(moveYOffset)<Math.abs(moveYTile*tileHeight))
				moveYOffset+=posMoveSpeed*Math.signum(moveYTile);
			else{
				yTile+=moveYTile;
				moveYTile=0;
				moveYOffset=0;}
		}
	}
	
	@Override
	public void render(Graphics g) {
		onRender(g);
		g.drawRect(getXPos(),getYPos(),tileWidth-1,tileHeight-1);
	}
}
