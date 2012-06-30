package me.EdwJes.main.Entities;

import me.EdwJes.main.OverworldObject;
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
public class Entity extends OverworldObject{
	protected int xTile,yTile,moveXTile=0,moveYTile=0;
	private float moveXOffset=0,moveYOffset=0,posMoveSpeed=2;
	public float walkingSpeed=1,runningSpeed=2;
	public boolean canMove=true,canInteract=true,canInteracted=true;
	public enum Direction{
		UP(0),
		RIGHT(1),
		DOWN(2),
		LEFT(3);
		private final int i;
		Direction(int i){
			this.i=i;}
		public int get(){
			return this.i;}
	};
	public Direction dir=Direction.RIGHT;
	
	public Entity(int xTile,int yTile/*,EntitySprite sprite*/){
		this.xTile=xTile;
		this.yTile=yTile;
		setLayer(LAYER_OVERWORLD);
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
		if(canMove&&!isMoving()){
			onMoveTile(xTile,this.yTile);
			moveXTile=xTile;
			
			if(xTile>0)
				dir=Direction.RIGHT;
			else if(xTile<0)
				dir=Direction.LEFT;
			
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
		if(canMove&&!isMoving()){
			onMoveTile(this.xTile,yTile);
			moveYTile=yTile;
			
			if(yTile>0)
				dir=Direction.DOWN;
			else if(yTile<0)
				dir=Direction.UP;
			
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
	public void onMoveFinished(int xTile,int yTile){}

	private void handleMovement(){
		if(canMove){
			if(getMovementX()!=0){
				moveXOffset+=posMoveSpeed*Math.signum(moveXTile);
				if(Math.abs(moveXOffset)>=Math.abs(moveXTile)*tileWidth){
					xTile+=moveXTile;
					onMoveFinished(xTile,yTile);
					moveXOffset=0;
					moveXTile=0;}
			}
			else if(getMovementY()!=0){
				moveYOffset+=posMoveSpeed*Math.signum(moveYTile);
				if(Math.abs(moveYOffset)>=Math.abs(moveYTile)*tileHeight){
					yTile+=moveYTile;
					onMoveFinished(xTile,yTile);
					moveYOffset=0;
					moveYTile=0;}
			}
		}
	} 
	
	@Override
	public void update(){
		super.update();
		handleMovement();
		setDepth(Math.round(getYPos()));//TODO: +bounding box height to measure the bottom y instead of top y
	}
	
	@Override
	public void render(Graphics g) {
		/*Color temp=g.getColor();
		if(isMoving())
			g.setColor(Color.gray);
		g.drawRect(getXPos(),getYPos(),tileWidth-1,tileHeight-1);
		g.setColor(temp);*/
	}
	
	public void interact(Entity target){
		onInteract(target);
		target.onInteracted(this);
	}
}
