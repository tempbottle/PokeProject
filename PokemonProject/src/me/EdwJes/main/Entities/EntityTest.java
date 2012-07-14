package me.EdwJes.main.Entities;

import java.util.ArrayList;
import me.EdwJes.main.PlayerInput;
import org.newdawn.slick.Animation;

public class EntityTest extends EntityHuman{
	public EntityTest(int xTile, int yTile,ArrayList<Animation> sprite){
		super(xTile, yTile, sprite);
		canInteracted=true;
	}
	
	@Override
	public void onInteracted(Entity interactor,int playerId){
		super.onInteracted(interactor,playerId);
		//View.getView(0).cmd.outputConsole("YES YES YES");
		PlayerInput.getPlayerInput(playerId).view.createTextbox("YES YES YES");
	}
}
