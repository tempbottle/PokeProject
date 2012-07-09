package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.debug.Debug;
import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.Config.GlobalKey;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerInput extends Updater{
	protected PlayerInputControlObject obj,objPrevious;
	public static List<PlayerInput> list = new ArrayList<PlayerInput>();
	public int playerId=0;
	public View view;
	public static Config config;
	public String playerName="Anon";
	
	public PlayerInput(PlayerInputControlObject obj,View view,Config config){
		this.obj=obj;
		this.view=view;
		this.objPrevious=obj;
		PlayerInput.config=config;
		list.add(this);
		playerId=list.indexOf(this);
	}
	
	public static PlayerInput getPlayerInput(int playerId){
		for(PlayerInput obj:list)
			if(obj.playerId==playerId)
				return obj;
		return null;
	}
	
	public static PlayerInput getPlayerInput(String playerName){
		for(PlayerInput obj:list)
			if(obj.playerName==playerName)
				return obj;
		return null;
	}
	
	public void handleInput(GameContainer container){
		Input input = container.getInput();
		obj.handleInput(input,playerId,config);}
	
	public static void keyPress(int key,char chr){
		if(key==PokemonProject.config.game.keyMap.get(GlobalKey.EXIT))
			PokemonProject.container.exit();
		
		else if(key==PokemonProject.config.game.keyMap.get(GlobalKey.DEBUGRENDERING)){
			Debug.renderDebug=!Debug.renderDebug;
		}
		
		for(PlayerInput player:list)
			player.onKeyPressed(key,chr);}
	
	public static void keyRelease(int key,char chr){
		for(PlayerInput player:list)
			player.onKeyReleased(key,chr);}
	
	public void onKeyPressed(int key,char chr){
		obj.onKeyPressed(key,chr,playerId,config);}
	
	public void onKeyReleased(int key,char chr){
		obj.onKeyReleased(key,chr,playerId,config);}
		
	public void setObj(PlayerInputControlObject obj){
		this.obj=obj;
	}
	
	public PlayerInputControlObject getObj(){
		return obj;
	}
	
	@Override
	public void update(){
		super.update();
		handleInput(PokemonProject.getContainer());}
}
