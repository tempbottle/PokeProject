package me.EdwJes.main.rooms;

import me.EdwJes.debug.Debug;
import me.EdwJes.main.Console;
import me.EdwJes.main.PlayerInput;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.View;
import me.EdwJes.main.Entities.Entity;
import me.EdwJes.main.Entities.EntityPlayer;
import me.EdwJes.main.EntityControl.PlayerInputEntityControl;
import me.EdwJes.main.resources.Sprite;

public class InitRoom extends Room {
	
	public InitRoom(String name){
		super(name);
	}
	
	public void init(){
		super.init();
		
		for(View view:View.list){
			view.cmd=new Console();
			view.cmd.view=view;}
		
		for(int i=0;i<PokemonProject.config.getPlayers();i++){
			Entity playerEntityObj;
			playerEntityObj=new EntityPlayer(2+i,6,Sprite.getAnimationGroup(PokemonProject.config.player.get(i).sprite));
			View view;
			if(i>View.countViews()-1)
				view=View.list.get(0);
			else{
				view=View.list.get(i);
				view.followsObject=playerEntityObj;}
			new PlayerInput(new PlayerInputEntityControl(playerEntityObj),view,PokemonProject.config);}
		
		if(PokemonProject.config.game.debugMode)
			new Debug();
		
		PokemonProject.roomLoader.enterRoom("Main");
	}

}
