package me.EdwJes.main.Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class EntityHuman extends Entity{
	public Animation[] sprite;
	
	public EntityHuman(int xTile, int yTile,Animation[] sprite) {
		super(xTile, yTile);
		this.sprite=sprite;
		setPosMoveSpeed(1);
	}
	
	public void onRender(Graphics g){
		if(isMoving()){
			if(dir==Direction.RIGHT)
				g.drawAnimation(sprite[Direction.RIGHT.get()], getXPos(), getYPos());
			else if(dir==Direction.LEFT)
				g.drawAnimation(sprite[Direction.LEFT.get()], getXPos(), getYPos());
			else if(dir==Direction.DOWN)
				g.drawAnimation(sprite[Direction.DOWN.get()], getXPos(), getYPos());
			else if(dir==Direction.UP)
				g.drawAnimation(sprite[Direction.UP.get()], getXPos(), getYPos());}
		else{
			if(dir==Direction.RIGHT)
				g.drawImage(sprite[Direction.RIGHT.get()].getImage(0), getXPos(), getYPos());
			else if(dir==Direction.LEFT)
				g.drawImage(sprite[Direction.LEFT.get()].getImage(0), getXPos(), getYPos());
			else if(dir==Direction.DOWN)
				g.drawImage(sprite[Direction.DOWN.get()].getImage(0), getXPos(), getYPos());
			else if(dir==Direction.UP)
				g.drawImage(sprite[Direction.UP.get()].getImage(0), getXPos(), getYPos());}
	}

}
