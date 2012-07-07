package me.EdwJes.main;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.FileConfig;
import me.EdwJes.main.fileresourceloader.ImageLoader;
import me.EdwJes.main.rooms.*;
import org.newdawn.slick.*; 

public class PokemonProject extends BasicGame{
	public static AppGameContainer app;
	public static final String TITLE = "Pokemon Project";
	Keyboard keyboard = new Keyboard();
	public static final String WORK_DIR = System.getProperty("user.dir");
	public static ImageLoader IMAGE_LOADER;
	public static AngelCodeFont font;
	protected static GameContainer container,container2;
	public static Config config;
	protected static int WINDOW_WIDTH_INIT = 640;
	protected static int WINDOW_HEIGHT_INIT = 480;
	protected static boolean FULLSCREEN_INIT = false;
	public static int FPS = 60;
	//Dark font color: #504060, shadow: #D0D0B8
	public static RoomLoader roomLoader;
	
	public PokemonProject(){ 
		super(TITLE);} 

	public static void main(String[] args){
		config = new FileConfig(WORK_DIR+"/config.yml");
		if((new File(WORK_DIR+"/config.yml")).exists())
			config.loadValues();
		else{
			config.loadDefaultValues();
			config.saveValues();}
		
		WINDOW_WIDTH_INIT = config.game.windowWidth;
		WINDOW_HEIGHT_INIT = config.game.windowHeight;
		FULLSCREEN_INIT = config.game.fullscreen;
		FPS = config.game.FPS;
		
		try{
		container=createAppGameContainer();}
		catch(SlickException e){ 
			e.printStackTrace();}
	}
	
	public static AppGameContainer createAppGameContainer() throws SlickException{
		app=new AppGameContainer(new ScalableGame(new PokemonProject(), WINDOW_WIDTH_INIT, WINDOW_HEIGHT_INIT, FULLSCREEN_INIT));
		app.setVSync(true);
		app.setTitle(TITLE);
		app.setDefaultFont(font);
		//app.setIcon(String something);
		app.start();
		return app;
	}

	@Override
	public void init(GameContainer container) throws SlickException{
		PokemonProject.container=container;
		container.setTargetFrameRate(FPS);
		container.setShowFPS(false);
		//TODO: Ful font init för test
		IMAGE_LOADER=new ImageLoader("/resources/images/");
		Sprite.loadAllEntities(IMAGE_LOADER);
		font=new AngelCodeFont(WORK_DIR+"/resources/images/fonts/hgss.fnt", IMAGE_LOADER.loadImage("/fonts/hgss.png"));
		
		for(int i=0;i<(config.game.views<1?config.getPlayers():config.game.views);i++)
			new View();
		View.alignViews();
		
		roomLoader = new RoomLoader();
		roomLoader.enterRoom(roomLoader.getCurrentRoom());
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException{
		g.setFont(font);
	    Collections.sort(RenderableObject.list, new Comparator<Object>(){

	        public int compare(Object o1, Object o2) {
	        	RenderableObject v1 = (RenderableObject) o1;
	        	RenderableObject v2 = (RenderableObject) o2;
	        	int layerDif=v1.layer - v2.layer;
	        	if(layerDif==0)
	        		return(v1.depth - v2.depth);
	        	else
	        		return(layerDif);
	        }});
	    
	    for(View view:View.list){
	    	g.scale(view.viewXScale,view.viewYScale);
	    	g.setClip(Math.round(view.viewXOffset*view.viewXScale), Math.round(view.viewYOffset*view.viewYScale), Math.round((view.viewXOffset+view.viewWidth)*view.viewXScale), Math.round((view.viewYOffset+view.viewHeight)*view.viewYScale));
	    	
		    for(RenderableObject o : RenderableObject.list){
				if(o.isActivated()){
					o.render(g,view);
		    	}
		    }
		    g.scale(1/view.viewXScale,1/view.viewYScale);
		}
	}
	
	private void handleInput(GameContainer container) throws SlickException {
		container.getInput().addKeyListener(keyboard);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		handleInput(container);
		container.setDefaultFont(font);
		
		for(int i=0;i<Updater.list.size();i++){
			if(Updater.list.get(i).isActivated())
				Updater.list.get(i).update();}
	}
	
	public static void setDisplayMode(int width,int height,boolean fullscreen) throws SlickException{
		for(View view:View.list){
			view.setScale(((float)app.getWidth()/(float)width),(float)app.getHeight()/(float)height);
			//view.viewXScale=(float)app.getWidth()/(float)width;
			//view.viewYScale=(float)app.getHeight()/(float)height;
		}
		app.setDisplayMode(width,height,fullscreen);
	}
	
	public static int getScreenWidth(){
		int w=0;
		for(View view:View.list)
			w+=view.viewWidth;
		return w;
	}
	
	public static int getScreenHeight(){
		int h=0;
		for(View view:View.list)
			h+=view.viewHeight;
		return h;
	}
	
	public static int getFPS(){
		return container.getFPS();
	}
	
	public static GameContainer getContainer(){
		return container;
	}
}
