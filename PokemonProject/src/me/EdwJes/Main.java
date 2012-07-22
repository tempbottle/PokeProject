package me.EdwJes;

import java.io.File;
import me.EdwJes.main.PokemonGame;
import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.FileConfig;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class Main{
	private static Config config;
	private static AppGameContainer container;
	private static final String TITLE="Pokemon Project";
	
	public static void main(String[] args){
		
		config = new FileConfig("config.yml");
		if((new File("config.yml")).exists())
			config.loadValues();
		else{
			config.loadDefaultValues();
			config.saveValues();}
		
		try{
		container=createAppGameContainer();
		container.start();}
		catch(SlickException e){ 
			e.printStackTrace();}
	}
	
	public static Config getConfig(){
		return config;
	}
	
	public static AppGameContainer createAppGameContainer() throws SlickException{
		AppGameContainer app;
		app=new AppGameContainer(new ScalableGame(new PokemonGame(), config.game.windowWidth, config.game.windowHeight, false),config.game.windowWidth,config.game.windowHeight,config.game.fullscreen);
		app.setVSync(config.game.vsync);
		app.setUpdateOnlyWhenVisible(false);
		app.setAlwaysRender(config.game.renderUnfocused);
		app.setTitle(TITLE);
		app.setShowFPS(false);
		app.setTargetFrameRate(config.game.FPS);
		return app;
	}
	
	public static GameContainer getContainer(){
		return container;
	}
}
