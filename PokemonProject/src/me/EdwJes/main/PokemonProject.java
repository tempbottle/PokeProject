package me.EdwJes.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.EdwJes.debug.Debug;
import me.EdwJes.main.ImageLoader.Name;
import me.EdwJes.main.Entities.*;

import org.newdawn.slick.*; 

public class PokemonProject extends BasicGame{
	public static AppGameContainer app;
	protected static int WINDOW_WIDTH_INIT = 640;
	protected static int WINDOW_HEIGHT_INIT = 480;
	public static int SCREEN_WIDTH = 320;
	public static int SCREEN_HEIGHT = 240;
	public static final String TITLE = "Pokemon Project";
	protected static boolean FULLSCREEN_INIT = false;
	protected static float XSCALE = 2f;
	protected static float YSCALE = 2f;
	public final int FPS = 60;
	public static GameContainer container;
	Keyboard keyboard = new Keyboard();
	public static Console cmd = new Console();
	public static PlayerInput player;
	public static final String WORK_DIR = System.getProperty("user.dir");
	public static ImageLoader IMAGE_LOADER;
	public static AngelCodeFont font;
	//Dark font color: #504060, shadow: #D0D0B8
	
	public PokemonProject(){ 
		super("PokemonProject");} 

	public static void main(String[] args){ 
		try{
		AppGameContainer app=createAppGameContainer();}
		catch(SlickException e){ 
			e.printStackTrace();}
	}
	
	public static AppGameContainer createAppGameContainer() throws SlickException{
		app=new AppGameContainer(new ScalableGame(new PokemonProject(), WINDOW_WIDTH_INIT, WINDOW_HEIGHT_INIT, false));
		app.setDisplayMode(WINDOW_WIDTH_INIT,WINDOW_HEIGHT_INIT,FULLSCREEN_INIT);
		app.setVSync(true);
		app.setTitle(TITLE);
		app.setDefaultFont(font);
		//app.setIcon(String something);
		app.start();
		return app;
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException{
		g.scale(XSCALE,YSCALE);
		//TODO: Depth beroende på Y-koordinater så att man hamnar bakom saker
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
		IMAGE_LOADER=new ImageLoader("/resources/images/");
		font=new AngelCodeFont(WORK_DIR+"/resources/images/fonts/main.fnt", IMAGE_LOADER.loadImage("/fonts/main.png"));
		new Debug();
		new EntityHuman(4,4,IMAGE_LOADER.animatedSprite.get(Name.May));
		new EntityHuman(1,5,IMAGE_LOADER.animatedSprite.get(Name.May));
		player=new PlayerInput();
	}

	private void handleInput(GameContainer container) throws SlickException {
		container.getInput().addKeyListener(keyboard);
	}
	
	public static void setScale(float xscale,float yscale){
		XSCALE=xscale*((float)WINDOW_WIDTH_INIT/(float)app.getWidth());
		YSCALE=yscale*((float)WINDOW_HEIGHT_INIT/(float)app.getHeight());
		SCREEN_WIDTH=Math.round(app.getWidth()/xscale);
		SCREEN_HEIGHT=Math.round(app.getHeight()/yscale);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		handleInput(container);
		
		List<GameObject> l = GameObject.list;
		for(int i=0;i<l.size();i++){
			l.get(i).update();}
	}
	
	public static void setDisplayMode(int width,int height,boolean fullscreen) throws SlickException{
		setScale((float)app.getWidth()/(float)width,(float)app.getHeight()/(float)height);
		app.setDisplayMode(width,height,fullscreen);
		Debug.console.println(XSCALE);
	}
}