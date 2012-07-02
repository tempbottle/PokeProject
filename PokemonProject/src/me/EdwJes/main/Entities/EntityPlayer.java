package me.EdwJes.main.Entities;

import org.newdawn.slick.Animation;

public class EntityPlayer extends EntityHuman{

	public EntityPlayer(int xTile, int yTile, Animation[] sprite) {
		super(xTile, yTile, sprite);
		setPersistency(true);
		canInteract=true;
	}

}
