package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.Entities.Entity;

public class View extends Updater{
	public static List<View> list=new ArrayList<View>();
	
	public int viewId=0;
	
	private float drawX=0;
	private float drawY=0;
	
	private float drawX_followMargin=0;
	private float drawY_followMargin=0;
	
	public boolean drawX_minEnabled=false;
	public float drawX_min=0;
	public boolean drawX_maxEnabled=false;
	public float drawX_max=0;
	public boolean drawY_minEnabled=false;
	public float drawY_min=0;
	public boolean drawY_maxEnabled=false;
	public float drawY_max=1000;
	
	public float viewXOffset=0;
	public float viewYOffset=0;
	
	public float viewWidth=320;
	public float viewHeight=240;
	public float viewXScale=2f;
	public float viewYScale=2f;
	
	public Entity followsObject=null;
	
	public Console cmd;
	
	public View(){
		list.add(this);
		viewId=list.indexOf(this);
	}
	
	public void destroy(){
		super.destroy();
		list.remove(this);
	}
	
	public static View getView(int id){
		for(View view:list)
			if(view.viewId==id)
				return view;
		return null;
	}
	
	public static int countViews(){
		return list.size();
	}
	
	public static int[] alignViews(int viewCount,int windowWidth,int windowHeight){
		double
			windowRatioX=(double)windowWidth/(double)windowHeight,
			xCount=viewCount,
			yCount=1,
			xCountPrevious=xCount,
			yCountPrevious=yCount;
		while(Math.ceil(xCount)/Math.ceil(yCount)>windowRatioX){
			xCountPrevious=xCount;
			yCountPrevious=yCount;
			yCount++;
			xCount=viewCount/yCount;}
		if(Math.ceil(xCountPrevious)/Math.ceil(yCountPrevious)-windowRatioX<windowRatioX-(Math.ceil(xCount)/Math.ceil(yCount))){
			xCount=xCountPrevious;
			yCount=yCountPrevious;}
			
		return new int[]{(int)Math.ceil(xCount),(int)Math.ceil(yCount),(int)Math.ceil(windowWidth/Math.ceil(xCount)),(int)Math.ceil(windowHeight/Math.ceil(yCount))};
	}
	
	public static void alignViews(){
		int[] values=alignViews(countViews(),PokemonProject.app.getWidth(),PokemonProject.app.getHeight());
		
		/*for(int i=1;i<20;i++){
			int[] str=alignViews(i,PokemonProject.app.getWidth(),PokemonProject.app.getHeight());
			System.out.println(i+": "+str[0]+" x "+str[1]+" = "+(str[0]*str[1])+" ["+str[2]+"x,"+str[3]+"y]");}*/
		
		int i=0;
		//System.out.println(values[0]+" "+values[1]+" "+values[2]+" "+values[3]+" ");
		for(View view:list){
			i++;
			int row=(int)Math.ceil((i-1)/values[0]),column=(i-1)%(values[0]);
			view.viewXOffset=(column*values[2])/view.viewXScale;
			view.viewYOffset=(row*values[3])/view.viewYScale;
			view.viewWidth=(float)values[2]/view.viewXScale;
			view.viewHeight=(float)values[3]/view.viewYScale;
			System.out.println(view.viewXOffset+" "+view.viewYOffset+" "+view.viewWidth+" "+view.viewHeight+" ");
		}
	}
	
	public void setScale(float xscale,float yscale){
		viewXOffset*=viewXScale/xscale;
		viewYOffset*=viewYScale/yscale;
		/*viewWidth*=viewXScale/xscale;
		viewHeight*=viewYScale/yscale;*/
		viewXScale=xscale*((float)PokemonProject.WINDOW_WIDTH_INIT/(float)PokemonProject.app.getWidth());
		viewYScale=yscale*((float)PokemonProject.WINDOW_HEIGHT_INIT/(float)PokemonProject.app.getHeight());
		viewWidth=Math.round(PokemonProject.app.getWidth()/xscale);
		viewHeight=Math.round(PokemonProject.app.getHeight()/yscale);
	}
	
	public void moveX(float x){
		if(Math.signum(x)==1){
			if(drawX_maxEnabled)
				drawX=Math.min(drawX+x,drawX_max-PokemonProject.container.getWidth());
			else
				drawX+=x;
		}
		else if(Math.signum(x)==-1){
			if(drawX_minEnabled)
				drawX=Math.max(drawX+x,drawX_min);
			else
				drawX+=x;
		}
	}	
		
	public void moveY(float y){
		if(Math.signum(y)==1){
			if(drawY_maxEnabled)
				drawY=Math.min(drawY+y,drawY_max-PokemonProject.container.getHeight());
			else
				drawY+=y;
		}
		else if(Math.signum(y)==-1){
			if(drawY_minEnabled)
				drawY=Math.max(drawY+y,drawY_min);
			else
				drawY+=y;
		}	
	}
	
	public void setX(float x){
		if(Math.signum(x)==1){
			if(drawX_maxEnabled)
				drawX=Math.min(x,drawX_max-PokemonProject.container.getWidth());
			else
				drawX=x;
		}
		else if(Math.signum(x)==-1){
			if(drawX_minEnabled)
				drawX=Math.max(x,drawX_min);
			else
				drawX=x;
		}	
	}
	
	public void setY(float y){
		if(Math.signum(y)==1){
			if(drawY_maxEnabled)
				drawY=Math.min(y,drawY_max-viewHeight);
			else
				drawY=y;
		}
		else if(Math.signum(y)==-1){
			if(drawY_minEnabled)
				drawY=Math.max(y,drawY_min);
			else
				drawY=y;
		}	
	}
	
	public float getDrawX(){
		return drawX-viewXOffset;
	}

	public float getDrawY(){
		return drawY-viewYOffset;
	}
	
	public float getDrawScreenX(float x){
		return x+viewXOffset;
	}

	public float getDrawScreenY(float y){
		return y+viewYOffset;
	}
	
	@Override
	public void update(){
		if(followsObject!=null){
			setX(followsObject.getXPos()-(viewWidth/2)+drawX_followMargin);
			setY(followsObject.getYPos()-(viewHeight/2)+drawY_followMargin);
		}
	}
}
