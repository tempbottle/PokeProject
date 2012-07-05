package me.EdwJes.main.Entities;

import me.EdwJes.main.View;
import org.newdawn.slick.Animation;

public class EntityTest extends EntityHuman{
	public EntityTest(int xTile, int yTile,Animation[] sprite){
		super(xTile, yTile, sprite);
		canInteracted=true;
	}
	
	@Override
	public void onInteracted(Entity interactor){
		super.onInteracted(interactor);
		View.getView(0).cmd.outputConsole("YES YES YES");
	}
}
