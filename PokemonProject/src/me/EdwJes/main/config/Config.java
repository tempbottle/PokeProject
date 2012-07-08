package me.EdwJes.main.config;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.newdawn.slick.Input;

public class Config{
	public List<Player> player=new Vector<Player>(2,1);
	public Game game=new Game();
	
	public static enum InputType{
		KEYBOARD,
		MOUSE,
		JOYSTICK;}
	public static enum Key{
		LEFT,
		RIGHT,
		UP,
		DOWN,
		ACTION,
		SKIP,
		ENTER,
		CHAT;
		public boolean isLike(Key key){
			return this.toString()==key.toString();}
	}
	public static enum GlobalKey{
		EXIT,
		SCREENSHOT,
		DEBUGRENDERING,
		FULLSCREEN;
		public boolean isLike(GlobalKey key){
			return this.toString()==key.toString();}
	}
	
	public static class Player{
		public Map<Key,Integer> keyMap;
		public String spriteSheet;
		public InputType inputType;
		public int inputId;
		public String defaultName;
		public Player(){}
	}
	
	public static class Game{
		public Map<GlobalKey,Integer> keyMap;
		public boolean debugMode;
		public boolean sound;
		public boolean music;
		public int windowWidth;
		public int windowHeight;
		public boolean fullscreen;
		public float scale;
		public int FPS;
		public String resourceFolder;
		public int players;
		public int views;
	}
	
	public void load(Config config){
		this.player=config.player;
		this.game=config.game;
	}
	
	public void loadDefaultValues(){
		loadValues();
	}
	
	public void loadValues(){
		game.keyMap=new EnumMap<GlobalKey,Integer>(GlobalKey.class);
		game.keyMap.put(GlobalKey.SCREENSHOT,Input.KEY_F3);
		game.keyMap.put(GlobalKey.DEBUGRENDERING,Input.KEY_F1);
		game.keyMap.put(GlobalKey.FULLSCREEN,Input.KEY_F4);
		game.keyMap.put(GlobalKey.EXIT,Input.KEY_ESCAPE);
		game.debugMode=false;
		game.sound=true;
		game.music=true;
		game.players=1;
		game.views=-1;
		game.resourceFolder="/resources/";
		game.windowWidth=640;
		game.windowHeight=480;
		game.fullscreen=false;
		game.scale=2f;
		game.FPS=60;
		
		Player _player=new Player();
		player.add(_player);
		_player.keyMap=new EnumMap<Key,Integer>(Key.class);
		_player.keyMap.put(Key.LEFT,   Input.KEY_LEFT);
		_player.keyMap.put(Key.RIGHT,  Input.KEY_RIGHT);
		_player.keyMap.put(Key.UP,     Input.KEY_UP);
		_player.keyMap.put(Key.DOWN,   Input.KEY_DOWN);
		_player.keyMap.put(Key.ACTION, Input.KEY_X);
		_player.keyMap.put(Key.SKIP,   Input.KEY_Z);
		_player.keyMap.put(Key.ENTER,  Input.KEY_ENTER);
		_player.keyMap.put(Key.CHAT,   Input.KEY_T);
		_player.inputId=0;
		_player.inputType=InputType.KEYBOARD;
		_player.spriteSheet="entity_Brendan.png";
		_player.defaultName="Brendan";
		
		_player=new Player();
		player.add(_player);
		_player.keyMap=new EnumMap<Key,Integer>(Key.class);
		_player.keyMap.put(Key.LEFT,   Input.KEY_NUMPAD4);
		_player.keyMap.put(Key.RIGHT,  Input.KEY_NUMPAD6);
		_player.keyMap.put(Key.UP,     Input.KEY_NUMPAD8);
		_player.keyMap.put(Key.DOWN,   Input.KEY_NUMPAD5);
		_player.keyMap.put(Key.ACTION, Input.KEY_NUMPAD0);
		_player.keyMap.put(Key.SKIP,   Input.KEY_NUMPADCOMMA);
		_player.keyMap.put(Key.ENTER,  Input.KEY_NUMPADENTER);
		_player.keyMap.put(Key.CHAT,   Input.KEY_INSERT);
		_player.inputId=0;
		_player.inputType=InputType.KEYBOARD;
		_player.spriteSheet="entity_May.png";
		_player.defaultName="May";	
	}
	
	public void saveValues(){}
	
	public int getPlayers(){
		return Math.min(game.players,player.size());
	}
}