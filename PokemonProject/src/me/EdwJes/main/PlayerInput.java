package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.Main;
import me.EdwJes.debug.Debug;
import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.Config.GlobalKey;
import me.EdwJes.main.config.Config.Key;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
* PlayerInput Class
*  
* <P>Supposed to take care of the input by controlling a {@link PlayerInputControlObject}
* <P>Each PlayerInput object is one player that have their own keys based on the configuration data {@link Config.player.get(playerId).keyMap} in {@link Config} that loads at game start.
*  
* @author Lolirofle
* @version 1.0
*/
public class PlayerInput extends Updater{//TODO: Maybe should store the input data in PlayerInput, import it instead of always have to access config
	private List<PlayerInputControlObject> objs=new ArrayList<PlayerInputControlObject>();
	public static List<PlayerInput> list = new ArrayList<PlayerInput>();
	private int playerId=0;
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
		getObj().handleInput(input,this);}
	
	/**
	 * The global keyPress that affects every PlayerInput and sends it to each PlayerInputControlObject
	 * @param key
	 * @param chr
	 */
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
	
	/**
	 * The global keyRelase that affects every PlayerInput and sends it to each PlayerInputControlObject
	 * @param key
	 * @param chr
	 */
	public static void keyRelease(int key,char chr){
		for(PlayerInput player:list)
			player.onKeyReleased(key,chr);}
	
	public void onKeyPressed(int key,char chr){
		getObj().onKeyPressed(key,chr,this);}
	
	public void onKeyReleased(int key,char chr){
		getObj().onKeyReleased(key,chr,this);}
	
	/**
	 * <P>Sets the PlayerInputControlObject that should be controlled by this PlayerInput, puts it to the first position of the control list and makes it the object that it should control.
	 * <P>A normal use of this would be that this executes when the PlayerInputControlObject is created and then calls {@link #removeObj} when destroyed.     
	 * @param obj The PlayerInputControlObject
	 */
	public void setObj(PlayerInputControlObject obj){
		this.objs.add(0,obj);
		
		if(this.objs.get(0).isKeyRepeat())
			Main.getContainer().getInput().enableKeyRepeat();
		else
			Main.getContainer().getInput().disableKeyRepeat();
	}
	
	/**
	 * Removes a PlayerInputControlObject from the list. If this object is first on the list, the control will be handled to the next entry on the list.
	 * @param obj The PlayerInputControlObject
	 */
	public void removeObj(PlayerInputControlObject obj){
		this.objs.remove(obj);
		
		if(this.objs.get(0).isKeyRepeat())
			Main.getContainer().getInput().enableKeyRepeat();
		else
			Main.getContainer().getInput().disableKeyRepeat();
	}

	/**
	 * Returns which PlayerInputControlObject it controls right now
	 * @return obj The PlayerInputControlObject
	 */
	public PlayerInputControlObject getObj(){
		return objs.get(0);
	}
	
	@Override
	public void update(){
		super.update();
		handleInput(Main.getContainer());}
	
	public int getKeymap(Key key){
		return config.player.get(playerId).keyMap.get(key);
	}
	
	public int getId(){
		return playerId;
	}
}
