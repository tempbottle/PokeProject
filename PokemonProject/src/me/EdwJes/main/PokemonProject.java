package me.EdwJes.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.EdwJes.debug.Debug;
import me.EdwJes.main.ImageLoader.Name;
import me.EdwJes.main.Entities.*;

import org.newdawn.slick.*; 

public class PokemonProject extends BasicGame{
	
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = 480;
	public static final String TITLE = "Pokemon Project";
	public static final boolean FULLSCREEN = false;
	public final int FPS = 60;
	public static GameContainer container;
	Keyboard keyboard = new Keyboard();
	public static Player player;
	public static final String WORK_DIR = System.getProperty("user.dir");
	public static ImageLoader IMAGE_LOADER;
	public static AngelCodeFont font;
	//Dark font color: #504060, shadow: #D0D0B8
	
	public PokemonProject(){ 
		super("PokemonProject");} 

	public static void main(String[] args){ 
		try{
			AppGameContainer app=new AppGameContainer(new PokemonProject());
			app.setDisplayMode(WINDOW_WIDTH,WINDOW_HEIGHT,FULLSCREEN);
			app.setVSync(true);
			app.setTitle(TITLE);
			//app.setIcon(String something);
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
		//TODO: Ful font init för test
		font =new AngelCodeFont(WORK_DIR+"/resources/fonts/main.fnt", new Image(WORK_DIR+"/resources/fonts/main.png"));
		IMAGE_LOADER=new ImageLoader("/resources/images/");
		new Debug();
		new EntityHuman(4,4,IMAGE_LOADER.anim[Name.May.get()]);
		new EntityHuman(1,5,IMAGE_LOADER.anim[Name.May.get()]);
		player=new Player();
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