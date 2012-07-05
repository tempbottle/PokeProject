package me.EdwJes.main;

import java.util.Collections;
import java.util.Comparator;
import me.EdwJes.debug.Debug;
import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.FileConfig;
import me.EdwJes.main.fileresourceloader.ImageLoader;
import me.EdwJes.main.Entities.*;
import me.EdwJes.main.EntityControl.PlayerInputEntityControl;
import me.EdwJes.main.rooms.*;
import org.newdawn.slick.*; 

public class PokemonProject extends BasicGame{
	public static AppGameContainer app;
	protected static int WINDOW_WIDTH_INIT = 640;
	protected static int WINDOW_HEIGHT_INIT = 480;
	public static final String TITLE = "Pokemon Project";
	protected static boolean FULLSCREEN_INIT = false;
	public final int FPS = 60;
	Keyboard keyboard = new Keyboard();
	public static int players=2;
	public static final String WORK_DIR = System.getProperty("user.dir");
	public static ImageLoader IMAGE_LOADER;
	public static AngelCodeFont font;
	protected static GameContainer container,container2;
	public static Config config = new FileConfig(WORK_DIR+"/config.yml");
	//Dark font color: #504060, shadow: #D0D0B8
	public static RoomLoader roomLoader;
	
	public PokemonProject(){ 
		super("PokemonProject");} 

	public static void main(String[] args){ 
		try{
		container=createAppGameContainer();
		Debug.console.print(container.getFPS());}
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
	public void init(GameContainer container) throws SlickException{
		PokemonProject.container=container;
		container.setTargetFrameRate(FPS);
		container.setShowFPS(false);
		//TODO: Ful font init för test
		IMAGE_LOADER=new ImageLoader("/resources/images/");
		Sprite.loadAllEntities(IMAGE_LOADER);
		font=new AngelCodeFont(WORK_DIR+"/resources/images/fonts/hgss.fnt", IMAGE_LOADER.loadImage("/fonts/hgss.png"));
		config.loadValues();
		
		roomLoader = new RoomLoader();
		
		View view=new View();
		new Debug();
		
		for(int i=0;i<players;i++){
			Entity playerEntityObj;
			playerEntityObj=new EntityPlayer(2+i,6,Sprite.getEntity(Sprite.Name.Brendan));
			new PlayerInput(new PlayerInputEntityControl(playerEntityObj),view);}
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
				if(o.isActivated())
					o.render(g,view);
		    	}
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
		int views=View.list.size();
		for(View view:View.list)
			view.setScale(((float)app.getWidth()/(float)width)/views,(float)app.getHeight()/(float)height);
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
