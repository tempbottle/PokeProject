package me.EdwJes.main.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigData{
	public Map<Integer,Player> players=new HashMap<Integer,Player>();
	public Game game=new Game();
	
	public static class Player{
		public static enum InputType{
			KEYBOARD,
			MOUSE,
			JOYSTICK;}
		public Map<String,Integer> keyMapping = new HashMap<String,Integer>();
		public String spriteSheet;
		public InputType inputType;
		public int inputId;
		public String name;
		public Player(){}
	}
	
	public static class Game{
		public Map<String,Integer> keyMapping = new HashMap<String,Integer>();
		public boolean debugMode;
		public boolean sound;
		public boolean music;
		public int windowWidth;
		public int windowHeight;
		public int screenWidth;
		public int screenHeigh;
		public boolean fullscreen;
		public float xScale;
		public float yScale;
		public int FPS;
		public String resourceFolder;
	}
}