package me.EdwJes.main.config;

import java.util.HashMap;
import org.newdawn.slick.Input;

public abstract class Config {
	private HashMap<String,int[]> CONFIG_INT_ARRAY = new HashMap<String, int[]>();
	private HashMap<String,Integer> CONFIG_INT = new HashMap<String, Integer>();
	private HashMap<String,String> CONFIG_STR = new HashMap<String, String>();
	private HashMap<String,Boolean> CONFIG_BOOL = new HashMap<String, Boolean>();

	public void loadValues(){
		CONFIG_INT_ARRAY.put("KEY_LEFT",   new int[]{Input.KEY_LEFT,  Input.KEY_NUMPAD4});
		CONFIG_INT_ARRAY.put("KEY_RIGHT",  new int[]{Input.KEY_RIGHT, Input.KEY_NUMPAD6});
        CONFIG_INT_ARRAY.put("KEY_UP",     new int[]{Input.KEY_UP,    Input.KEY_NUMPAD8});
        CONFIG_INT_ARRAY.put("KEY_DOWN",   new int[]{Input.KEY_DOWN,  Input.KEY_NUMPAD5});
        CONFIG_INT_ARRAY.put("KEY_ACTION", new int[]{Input.KEY_X,     Input.KEY_NUMPAD0});
        CONFIG_INT_ARRAY.put("KEY_SKIP",   new int[]{Input.KEY_Z,     Input.KEY_NUMPADCOMMA});
        CONFIG_INT_ARRAY.put("KEY_ENTER",  new int[]{Input.KEY_ENTER, Input.KEY_NUMPADENTER});
        CONFIG_INT_ARRAY.put("KEY_EXIT",   new int[]{Input.KEY_ESCAPE,Input.KEY_PAUSE});
	    CONFIG_INT_ARRAY.put("KEY_CHAT",   new int[]{Input.KEY_T,     Input.KEY_INSERT});
	}
	
	public Integer getInt(String key) {
		return CONFIG_INT.get(key);
	}
	
	public Integer getIntArray(String key,int index) {
		return (CONFIG_INT_ARRAY.get(key))[index];
	}
	
	public String getStr(String key) {
		return CONFIG_STR.get(key);
	}
	
	public Boolean getBool(String key) {
		return CONFIG_BOOL.get(key);
	}
}