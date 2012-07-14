package me.EdwJes.main.Entities;

import java.util.ArrayList;
import me.EdwJes.main.View;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class EntityPlayer extends EntityHuman{

	public EntityPlayer(int xTile, int yTile, ArrayList<Animation> sprite) {
		super(xTile, yTile, sprite);
		setPersistency(true);
		canInteract=true;
	}
	
	@Override
	public void render(Graphics g,View view){
		super.render(g,view);
		/*for(int[] pos:collisionMask.getPoints())
			g.drawRect((getXTile()+pos[0])*tileWidth-view.getDrawX(),(getYTile()+pos[1])*tileHeight-view.getDrawY(), tileWidth, tileHeight);*/
	}
}
