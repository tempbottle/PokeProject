package me.EdwJes.main.Entities;

import me.EdwJes.main.View;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class EntityHuman extends Entity{
	public Animation[] sprite;
	public int spriteOffsetX=0,spriteOffsetY=0;
	
	public EntityHuman(int xTile, int yTile,Animation[] sprite) {
		super(xTile, yTile);
		this.sprite=sprite;
		setPosMoveSpeed(1);
		spriteOffsetX=-(sprite[0].getWidth()-16)/2;
		spriteOffsetY=-(sprite[0].getHeight()-16);
		canInteracted=true;
	}
	
	@Override public void render(Graphics g,View view){
		super.render(g,view);
		
		Animation drawSpr;
		if(dir==Direction.RIGHT)
			drawSpr=sprite[Direction.RIGHT.get()];
		else if(dir==Direction.LEFT)
			drawSpr=sprite[Direction.LEFT.get()];
		else if(dir==Direction.UP)
			drawSpr=sprite[Direction.UP.get()];
		else// if(dir==Direction.DOWN)
			drawSpr=sprite[Direction.DOWN.get()];
			
		if(isMoving())
			g.drawAnimation(drawSpr, getXPos()+spriteOffsetX-view.getDrawX(), getYPos()+spriteOffsetY-view.getDrawY());
		else
			g.drawImage(drawSpr.getImage(0), getXPos()+spriteOffsetX-view.getDrawX(), getYPos()+spriteOffsetY-view.getDrawY());
	}
}
