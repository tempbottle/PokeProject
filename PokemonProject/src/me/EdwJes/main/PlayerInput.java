package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.debug.Debug;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerInput extends GameObject{
	protected PlayerInputControlObject obj,objPrevious;
	protected static List<PlayerInput> list = new ArrayList<PlayerInput>();
	public int playerId=0;
	public View view;
	Alarm alarm1 = new Alarm(120, this);
	
	public PlayerInput(PlayerInputControlObject obj,View view){
		this.obj=obj;
		this.view=view;
		this.objPrevious=obj;
		list.add(this);
		playerId=list.indexOf(this);
		setPersistency(true);
	}
	
	public static PlayerInput getPlayerInput(int playerId){
		for(PlayerInput obj:list)
			if(obj.playerId==playerId)
				return obj;
		return null;
	}
	
	public void handleInput(GameContainer container){
		Input input = container.getInput();
		obj.handleInput(input,playerId);}
	
	public static void keyPress(int key,char chr){
		if(key==Input.KEY_ESCAPE)
			PokemonProject.container.exit();
		
		else if(key==Input.KEY_F1){
			Debug.renderDebug=!Debug.renderDebug;
		}
		
		for(PlayerInput player:list)
			player.onKeyPressed(key,chr);}
	
	public static void keyRelease(int key,char chr){
		for(PlayerInput player:list)
			player.onKeyReleased(key,chr);}
	
	public void onKeyPressed(int key,char chr){
		obj.onKeyPressed(key,chr,playerId);}
	
	public void onKeyReleased(int key,char chr){
		obj.onKeyReleased(key,chr,playerId);}
		
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
	
	@Override public void callAlarm(Alarm alarm){
		super.callAlarm(alarm);
		if(alarm == alarm1)System.out.println("ALARM TEST FROM PLAYER INPUT "+playerId);
	}
}
