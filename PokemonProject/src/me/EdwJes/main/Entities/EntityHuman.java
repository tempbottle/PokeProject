package me.EdwJes.main.Entities;

import java.util.ArrayList;
import me.EdwJes.main.View;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class EntityHuman extends Entity{
	public ArrayList<Animation> sprite;
	public int spriteOffsetX=0,spriteOffsetY=0;
	
	public EntityHuman(int xTile, int yTile,ArrayList<Animation> arrayList) {
		super(xTile, yTile);
		this.sprite=arrayList;
		setPosMoveSpeed(1);
		spriteOffsetX=-(arrayList.get(0).getWidth()-16)/2;
		spriteOffsetY=-(arrayList.get(0).getHeight()-16);
		canInteracted=true;
	}
	
	@Override public void render(Graphics g,View view){
		super.render(g,view);
		
		Animation drawSpr;
		if(dir==Direction.RIGHT)
			drawSpr=sprite.get(Direction.RIGHT.get());
		else if(dir==Direction.LEFT)
			drawSpr=sprite.get(Direction.LEFT.get());
		else if(dir==Direction.UP)
			drawSpr=sprite.get(Direction.UP.get());
		else// if(dir==Direction.DOWN)
			drawSpr=sprite.get(Direction.DOWN.get());
			
		if(isMoving())
			g.drawAnimation(drawSpr, getXPos()+spriteOffsetX-view.getDrawX(), getYPos()+spriteOffsetY-view.getDrawY());
		else
			g.drawImage(drawSpr.getImage(0), getXPos()+spriteOffsetX-view.getDrawX(), getYPos()+spriteOffsetY-view.getDrawY());
	}
}
