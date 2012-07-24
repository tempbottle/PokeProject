package me.EdwJes.main.overworld.entities;

import java.util.ArrayList;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.Textbox;
import me.EdwJes.main.View;
import org.newdawn.slick.Animation;

public class EntityTest extends EntityHuman{
	public EntityTest(int xTile, int yTile,ArrayList<Animation> sprite){
		super(xTile, yTile, sprite);
		canInteracted=true;
	}
	
	@Override
	public void onInteracted(Entity interactor,int playerId){
		super.onInteracted(interactor,playerId);
		View playerView=PlayerInput.getPlayerInput(playerId).view;
		Textbox textbox=new Textbox("YES YES YES",playerView){
			@Override
			public void onFinished(){
				posMoveY(5);
			}
		};
		playerView.setTextbox(textbox);
	}
}
