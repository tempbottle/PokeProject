package me.EdwJes.main.config;

import java.io.FileWriter;
import java.io.IOException;
import me.EdwJes.main.config.ConfigData.Player;
import me.EdwJes.main.config.ConfigData.Player.InputType;
import com.esotericsoftware.yamlbeans.YamlWriter;

public class FileConfig extends Config{

	public FileConfig(String configPath){
		super();
		try{
			ConfigData config= new ConfigData();
			
			Player player=new Player();
			config.players.put(1,player);
			player.inputId=0;
			player.inputType=InputType.KEYBOARD;
			player.spriteSheet="lala.png";
			player.keyMapping.put("LEFT",0);
			player.keyMapping.put("RIGHT",1);
			player.keyMapping.put("UP",1);
			player.keyMapping.put("DOWN",1);
			player.keyMapping.put("ACTION",1);
			player.keyMapping.put("RUN",1);
			player.keyMapping.put("PAUSE",1);
			player.keyMapping.put("EXIT",1);
			
			player=new Player();
			config.players.put(2,player);
			player.inputId=1;
			player.inputType=InputType.KEYBOARD;
			player.spriteSheet="lalas.png";
			
			config.game.keyMapping.put("SCREENSHOT",0);
			config.game.keyMapping.put("DEBUGRENDERING",0);
			config.game.keyMapping.put("FULLSCREEN",0);
			config.game.debugMode=true;
			config.game.sound=true;
			config.game.music=true;
			config.game.resourceFolder="C:/";
			
			YamlWriter writer = new YamlWriter(new FileWriter(configPath));
			writer.getConfig().setClassTag("Config", ConfigData.class);
			writer.getConfig().setClassTag("Player", Player.class);
			writer.write(config);
			writer.close();
			}
		catch(IOException e){e.printStackTrace();}
	}
}
