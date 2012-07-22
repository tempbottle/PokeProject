package me.EdwJes.main;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import me.EdwJes.Main;
import me.EdwJes.main.fileresourceloader.ImageLoader;
import me.EdwJes.main.resources.Sprite;
import me.EdwJes.main.rooms.*;
import org.newdawn.slick.*; 

public class PokemonGame implements Game{//TODO: Use Locale for all in-game strings or some other kind of language implementation
	private static final String TITLE = "Pokemon Project";
	Keyboard keyboard = new Keyboard();
	Mouse mouse;
	//public static final String WORK_DIR = System.getProperty("user.dir");
	public static final String RESOURCES_DIR = "resources/";
	public static final String IMAGES_DIR = RESOURCES_DIR+"images/";
	public static AngelCodeFont font;
	//Dark font color: #504060, shadow: #D0D0B8
	public static RoomLoader roomLoader;
	
	public PokemonGame(){ 
		super();
	}

	@Override
	public void init(GameContainer container) throws SlickException{
		//TODO: Ful font init för test

		try{
			Sprite.load();}
		catch(IOException e){
			e.printStackTrace();
			System.out.println(e.toString());}

		try{
			font=new AngelCodeFont("resources/images/fonts/hgss.fnt", ImageLoader.loadImage(IMAGES_DIR+"fonts/hgss.png"));}
		catch(SlickException e1){
			e1.printStackTrace();}
		
		container.setDefaultFont(font);
		
		for(int i=0;i<(Main.getConfig().game.views<1?Main.getConfig().getPlayers():Main.getConfig().game.views);i++)
			new View();
		View.alignViews();
		
		roomLoader = new RoomLoader();
		roomLoader.enterRoom(roomLoader.getCurrentRoom());
		
		container.getInput().addKeyListener(keyboard);
		mouse=new Mouse();
		container.getInput().addMouseListener(mouse);
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
	    	float xScale=((float)Main.getConfig().game.windowWidth/(float)container.getWidth()),
	    	      yScale=((float)Main.getConfig().game.windowHeight/(float)container.getHeight());
	    	g.scale(xScale*view.viewXScale,yScale*view.viewYScale);
	    	g.setClip(Math.round(view.viewXOffset*view.viewXScale), Math.round(view.viewYOffset*view.viewYScale), Math.round(view.viewWidth*view.viewXScale), Math.round(view.viewHeight*view.viewYScale));
	    	
		    for(RenderableObject o : RenderableObject.list){
		    	if(o.view==null||o.view==view){
		    		if(o.isActivated()){
		    			float translateX,translateY;
		    			if(o.getLayer()==RenderableObject.LAYER_GUI){
		    				translateX=-view.viewXOffset;
		    				translateY=-view.viewYOffset;
		    			}
		    			else{
		    				translateX=view.getDrawX()-view.viewXOffset;
		    				translateY=view.getDrawY()-view.viewYOffset;
		    			}
		    			g.translate(-translateX,-translateY);
		    			o.render(g,view);
		    			g.translate(translateX,translateY);
		    		}
		    	}
		    }
		    g.scale(1/(xScale*view.viewXScale),1/(yScale*view.viewYScale));
		}
	    for(RenderableWindowObject o : RenderableWindowObject.list){
	    	o.render(g);
	    }
	}
	
	private void handleInput(GameContainer container) throws SlickException {
		
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
		//for(View view:View.list){
			//view.setScale(((float)app.getWidth()/(float)width),(float)app.getHeight()/(float)height);
			//view.viewXScale=(float)app.getWidth()/(float)width;
			//view.viewYScale=(float)app.getHeight()/(float)height;
		//}
		((AppGameContainer)Main.getContainer()).setDisplayMode(width,height,fullscreen);
		View.alignViews();
	}
	
	public static int getFPS(){
		return Main.getContainer().getFPS();
	}

	@Override
	public boolean closeRequested(){
		return true;
	}

	@Override
	public String getTitle(){
		return TITLE;
	}
}
