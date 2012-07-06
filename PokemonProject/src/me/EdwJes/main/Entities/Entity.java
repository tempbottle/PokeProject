package me.EdwJes.main.Entities;

import me.EdwJes.main.OverworldObject;
import me.EdwJes.main.View;

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
	private int moveXTile=0,moveYTile=0,movedTiles=0;
	private DirectionX moveXDir=DirectionX.NONE;
	private DirectionY moveYDir=DirectionY.NONE;
	private float moveXOffset=0,moveYOffset=0,posMoveSpeed=2;
	private Movement stopMove=Movement.NONE;
	public float walkingSpeed=1,runningSpeed=2;
	public boolean canMove=true,canInteract=false;
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
		public static Direction valueOf(int i){
			for(Direction value:Direction.values())
				if(value.get()==i)
					return value;
			return Direction.UP;}
	};
	public enum DirectionX{
		LEFT(-1),
		NONE(0),
		RIGHT(1);
		private final int i;
		DirectionX(int i){
			this.i=i;}
		public int get(){
			return this.i;}
		public static DirectionX getDirX(Direction dir){
			if(dir==Direction.LEFT)return DirectionX.LEFT;
			else return DirectionX.RIGHT;}
		public static Direction getDir(DirectionX xDir){
			if(xDir==DirectionX.LEFT)return Direction.LEFT;
			else return Direction.RIGHT;}
		public Direction getDir(){
			return getDir(this);
		}
	};
	public enum DirectionY{
		UP(-1),
		NONE(0),
		DOWN(1);
		private final int i;
		DirectionY(int i){
			this.i=i;}
		public int get(){
			return this.i;}
		public static DirectionY getDirY(Direction dir){
			if(dir==Direction.UP)return DirectionY.UP;
			else return DirectionY.DOWN;}
		public static Direction getDir(DirectionY yDir){
			if(yDir==DirectionY.UP)return Direction.UP;
			else return Direction.DOWN;}
		public Direction getDir(){
			return getDir(this);
		}
	};
	public enum Movement{
			NONE(-1),
			UP(0),
			RIGHT(1),
			DOWN(2),
			LEFT(3),
			ANY(4);
			private final int i;
			Movement(int i){
				this.i=i;}
			public int get(){
				return this.i;}
			public static Movement valueOf(int i){
				for(Movement value:Movement.values())
					if(value.get()==i)
						return value;
				return Movement.NONE;}
	};
	public Direction dir=Direction.RIGHT;
	
	public Entity(int xTile,int yTile/*,EntitySprite sprite*/){
		super(xTile,yTile);
		setLayer(LAYER_OVERWORLD);
	}

	/**
	  * Sets the direction {@link #dir}
	  * 
	  * @param dir Direction to turn to
	  */
	public void turn(Direction dir){
			this.dir=dir;}
	
	/**
	  * Rotates relatively to its direction {@link #dir}
	  * 
	  * @param dir Direction to rotate
	  */
	public void rotate(Direction dir){
		if(dir==Direction.LEFT)
			rotate(-1);
		if(dir==Direction.RIGHT)
			rotate(1);
		if(dir==Direction.UP)
			rotate(2);}
	
	/**
	  * Rotates relatively to its direction {@link #dir}
	  * 
	  * @param turns How many turns it should rotate
	  */
	public void rotate(int turns){
		this.dir=Direction.valueOf((this.dir.get()-turns)%3);}
	
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
	@Override
	public void posMoveX(int xTile){
		Direction dir=this.dir;
		if(xTile>0)
			dir=Direction.RIGHT;
		else if(xTile<0)
			dir=Direction.LEFT;
		
		if(canMove&&!isMoving()){
			this.dir=dir;
			if(isDirectionFree(dir)){
				onMoveTile(xTile,this.moveYTile);
				moveXTile=Math.abs(xTile);
				moveXDir=DirectionX.getDirX(dir);
			}
		}
	}
	
	/**
	  * Moves the entity relatively to it's position
	  * 
	  * @param yTile y value in tiles
	  */
	@Override
	public void posMoveY(int yTile){
		Direction dir=this.dir;
		if(yTile>0)
			dir=Direction.DOWN;
		else if(yTile<0)
			dir=Direction.UP;
		
		if(canMove&&!isMoving()){
			this.dir=dir;
			if(isDirectionFree(dir)){
				onMoveTile(this.moveXTile,yTile);
				moveYTile=Math.abs(yTile);
				moveYDir=DirectionY.getDirY(dir);
			}
		}
	}
	
	/**
	  * Moves horizontal infinitely until stopped
	  * 
	  * @param xTile x value in tiles
	  */
	public void posMoveX(DirectionX xDir){
		Direction dir=this.dir;
		dir=DirectionX.getDir(xDir);
		
		if(canMove){
			if(!isMoving()){
				this.dir=dir;
				if(isDirectionFree(dir)){
					moveXTile=0;
					moveXDir=DirectionX.getDirX(dir);
					onMoveTile(xTile,this.moveYTile);
				}
			}
			else stopMove();
		}
	}
	
	/**
	  * Moves vertical infinitely until stopped
	  * 
	  * @param yTile y value in tiles
	  */
	public void posMoveY(DirectionY yDir){
		Direction dir=this.dir;
		dir=DirectionY.getDir(yDir);
		
		if(canMove){
			if(!isMoving()){
				this.dir=dir;
				if(isDirectionFree(dir)){
					moveYTile=0;
					moveYDir=DirectionY.getDirY(dir);
					onMoveTile(this.moveXTile,yTile);
				}
			}
			else stopMove();
		}
	}
	
	/**
	  * Is the entity moving?
	  * 
	  * @return <tt>true</tt> if moving<br/><tt>false</tt> if standing still 
	  */
	public boolean isMoving(){
		if(moveXDir!=DirectionX.NONE||moveYDir!=DirectionY.NONE)
			return true;
		else
			return false;
	}
	
	/**
	  * Returns the on-going horizontal movement in tiles
	  * 
	  * @return 0 if no movement<br/>
	  */
	public int getMovementX(){
		if(moveXDir!=DirectionX.NONE)
			return Math.max(moveXTile,1);
		else
			return 0;}

	/**
	  * Returns the on-going vertical movement in tiles
	  * 
	  * @return 0 if no movement<br/>
	  */
	public int getMovementY(){
		if(moveYDir!=DirectionY.NONE)
			return Math.max(moveYTile,1);
		else
			return 0;}
	
	/**
	  * Returns the on-going horizontal movement in tiles
	  * 
	  * @return NONE if no movement<br/>else LEFT or RIGHT
	  */
	public DirectionX getMovementXDir(){
		return moveXDir;}

	/**
	  * Returns the on-going vertical movement in tiles
	  * 
	  * @return NONE if no movement<br/>else UP or DOWN
	  */
	public DirectionY getMovementYDir(){
		return moveYDir;}
	
	/**
	  * Returns the x-position of the entity in the room
	  * 
	  * @return x 
	  */
	@Override
	public float getXPos(){
		return xTile*tileWidth+moveXOffset;
	}

	/**
	  * Returns the y-position of the entity in the room
	  * 
	  * @return y 
	  */
	@Override
	public float getYPos(){
		return yTile*tileHeight+moveYOffset;
	}
	
	@Override
	public void onInteracted(Entity target){
		super.onInteracted(target);
		if(target.getXTile()>getXTile())
			dir=Direction.RIGHT;
		else if(target.getXTile()<getXTile())
			dir=Direction.LEFT;
		else if(target.getYTile()>getYTile())
			dir=Direction.DOWN;
		else if(target.getYTile()<getYTile())
			dir=Direction.UP;
	}
	public void onInteract(OverworldObject target){}
	public void onMoveTile(int xTileTo,int yTileTo){
		int x=0,y=0;
		if(dir==Direction.LEFT)	x--;
		else if(dir==Direction.RIGHT)x++;
		else if(dir==Direction.UP)y--;
		else if(dir==Direction.DOWN)y++;
		collisionMask.addTempPoint(x,y);}
	public void onMoveFinished(int xTile,int yTile,int movedTiles){
		collisionMask.clearTempPoints();}

	public Movement getStopMove(){
		return stopMove;}
	
	public void stopMove(){
		stopMove=Movement.ANY;}
	
	public void stopMove(Direction dir){
		stopMove=Movement.valueOf(dir.get());}
	
	private void stopXMovement(){
		moveXOffset=0;
		moveXTile=0;
		moveXDir=DirectionX.NONE;
		stopMove=Movement.NONE;
		onMoveFinished(xTile,yTile,movedTiles);
		movedTiles=0;}
	
	private void stopYMovement(){
		moveYOffset=0;
		moveYTile=0;
		moveYDir=DirectionY.NONE;
		stopMove=Movement.NONE;
		onMoveFinished(xTile,yTile,movedTiles);
		movedTiles=0;}
	
	private void handleMovement(){//TODO: Bug: Walks through entities when both is moving
		if(canMove){
			if(moveXDir!=DirectionX.NONE){
				moveXOffset+=posMoveSpeed*moveXDir.get();
				if(Math.abs(moveXOffset)>=Math.abs(moveXDir.get()*tileWidth)){
					xTile+=moveXDir.get();
					movedTiles++;
					moveXOffset=0;
					if(stopMove==Movement.ANY||stopMove==Movement.valueOf(dir.get())||!isDirectionFree(dir))
						stopXMovement();}
				
				if(moveXTile>0&&movedTiles*tileWidth+Math.abs(moveXOffset)>=moveXTile*tileWidth){
					stopXMovement();}
			}
			else if(moveYDir!=DirectionY.NONE){
				moveYOffset+=posMoveSpeed*moveYDir.get();
				if(Math.abs(moveYOffset)>=Math.abs(moveYDir.get()*tileHeight)){
					yTile+=moveYDir.get();
					movedTiles++;
					moveYOffset=0;
					if(stopMove==Movement.ANY||stopMove==Movement.valueOf(dir.get())||!isDirectionFree(dir))
						stopYMovement();}
				
				if(moveYTile>0&&movedTiles*tileHeight+Math.abs(moveYOffset)>=moveYTile*tileHeight){
					stopYMovement();}
			}
		}
	} 
	
	@Override
	public void update(){
		super.update();
		handleMovement();
		setDepth(Math.round(getYPos())+collisionMask.getMaxY());
	}
	
	@Override
	public void render(Graphics g,View view) {
		super.render(g,view);
		/*Color temp=g.getColor();
		if(isMoving())
			g.setColor(Color.gray);
		g.drawRect(getXPos(),getYPos(),tileWidth-1,tileHeight-1);
		g.setColor(temp);*/
	}
	
	public void interact(OverworldObject target){
		if(canInteract&&target.canInteracted){
			onInteract(target);
			target.onInteracted(this);}
	}
	
	public boolean isDirectionFree(Direction dir){
		int x=getXTile(),y=getYTile();
		if(dir==Direction.LEFT)	x--;
		else if(dir==Direction.RIGHT)x++;
		else if(dir==Direction.UP)y--;
		else if(dir==Direction.DOWN)y++;
		boolean bool=!isColliding(x,y);
		return bool;
	}
	
	public OverworldObject getObjCollidedDir(Direction dir){
		int x=getXTile(),y=getYTile();
		if(dir==Direction.LEFT)	x--;
		else if(dir==Direction.RIGHT)x++;
		else if(dir==Direction.UP)y--;
		else if(dir==Direction.DOWN)y++;
		return getCollidingObj(x,y);
	}
}
