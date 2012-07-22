package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.Main;
import me.EdwJes.debug.Debug;
import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.Config.GlobalKey;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class PlayerInput extends Updater{
	private List<PlayerInputControlObject> objs=new ArrayList<PlayerInputControlObject>();
	public static List<PlayerInput> list = new ArrayList<PlayerInput>();
	public int playerId=0;
	public View view;
	public static Config config;
	
	public PlayerInput(PlayerInputControlObject obj,View view,Config config){
		this.objs.add(obj);
		this.view=view;
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
	
	/*public static PlayerInput getPlayerInput(String playerName){
		for(PlayerInput obj:list)
			if(obj.playerName==playerName)
				return obj;
		return null;
	}*/
	
	public static PlayerInput getPlayerInput(View view){
		for(PlayerInput obj:list)
			if(obj.view==view)
				return obj;
		return null;
	}
	
	public void handleInput(GameContainer container){
		Input input = container.getInput();
		getObj().handleInput(input,playerId,config);}
	
	public static void keyPress(int key,char chr){
		if(key==Main.getConfig().game.keyMap.get(GlobalKey.EXIT))
			Main.getContainer().exit();
		
		else if(key==Main.getConfig().game.keyMap.get(GlobalKey.DEBUGRENDERING)){
			Debug.renderDebug=!Debug.renderDebug;
		}
		
		else if(key==Main.getConfig().game.keyMap.get(GlobalKey.FULLSCREEN)){
			try{
				boolean fullscreen=Main.getContainer().isFullscreen();
				int width,height;
				if(fullscreen){
					width=Main.getConfig().game.windowWidth;
					height=Main.getConfig().game.windowHeight;}
				else{
					width=Main.getContainer().getScreenWidth();
					height=Main.getContainer().getScreenHeight();}
				
				PokemonGame.setDisplayMode(width,height,!fullscreen);}
			catch(SlickException e){
				e.printStackTrace();}
		}
		
		for(PlayerInput player:list)
			player.onKeyPressed(key,chr);}
	
	public static void keyRelease(int key,char chr){
		for(PlayerInput player:list)
			player.onKeyReleased(key,chr);}
	
	public void onKeyPressed(int key,char chr){
		getObj().onKeyPressed(key,chr,playerId,config);}
	
	public void onKeyReleased(int key,char chr){
		getObj().onKeyReleased(key,chr,playerId,config);}
		
	public void setObj(PlayerInputControlObject obj){
		this.objs.add(0,obj);
		
		if(this.objs.get(0).isKeyRepeat())
			Main.getContainer().getInput().enableKeyRepeat();
		else
			Main.getContainer().getInput().disableKeyRepeat();
	}
	
	public void removeObj(PlayerInputControlObject obj){
		this.objs.remove(obj);
		
		if(this.objs.get(0).isKeyRepeat())
			Main.getContainer().getInput().enableKeyRepeat();
		else
			Main.getContainer().getInput().disableKeyRepeat();
	}
	
	public PlayerInputControlObject getObj(){
		return objs.get(0);
	}
	
	@Override
	public void update(){
		super.update();
		handleInput(Main.getContainer());}
}
