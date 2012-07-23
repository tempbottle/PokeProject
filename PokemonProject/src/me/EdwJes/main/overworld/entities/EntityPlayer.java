package me.EdwJes.main.overworld.entities;

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
		/*g.setColor(new Color(255,255,255,128));
		for(int[] pos:collisionMask.getPoints())
			g.fillRect((getXTile()+pos[0])*tileWidth,(getYTile()+pos[1])*tileHeight, tileWidth, tileHeight);
		g.setColor(Color.white);*/
		super.render(g,view);
	}
}
