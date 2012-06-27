package me.EdwJes.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.EdwJes.debug.Debug;
import me.EdwJes.main.Entities.*;

import org.newdawn.slick.*; 

public class PokemonProject extends BasicGame{
	
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = 480;
	public static final boolean FULLSCREEN = false;
	public final int FPS = 60;
	public static GameContainer container;
	Keyboard keyboard = new Keyboard();
	public static Player player=new Player();

	public PokemonProject(){ 
		super("PokemonProject");} 

	public static void main(String[] args){ 
		try{
			AppGameContainer app=new AppGameContainer(new PokemonProject());
			app.setDisplayMode(WINDOW_WIDTH,WINDOW_HEIGHT,FULLSCREEN);
			app.start();} 
		catch(SlickException e){ 
			e.printStackTrace();}}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException{
	    Collections.sort(RenderableObject.list, new Comparator<Object>(){

	        public int compare(Object o1, Object o2) {
	        	RenderableObject v1 = (RenderableObject) o1;
	        	RenderableObject v2 = (RenderableObject) o2;
	           return (v1.depth - v2.depth);
	        }});

		
		for(RenderableObject o : RenderableObject.list){
			o.render(g);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException{
		PokemonProject.container=container;
		container.setTargetFrameRate(FPS);
		container.setShowFPS(false);
		new Debug();
		new Trainer(4,4);
		new Trainer(1,5);
	}

	private void handleInput(GameContainer container) throws SlickException {
		container.getInput().addKeyListener(keyboard);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		handleInput(container);
		
		List<GameObject> l = GameObject.list;
		for(int i=0;i<l.size();i++){
			l.get(i).update();}
	}
}