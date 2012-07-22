package me.EdwJes.main;

import me.EdwJes.main.overworld.OverworldObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class Mouse extends RenderableWindowObject implements MouseListener {
	OverworldObject objSelected=null;
	int objSelectMouseX=-1,objSelectMouseY=-1,mousePosX=0,mousePosY=0;
	final static int BUTTON_LEFT=0,BUTTON_RIGHT=1,BUTTON_MIDDLE=2;
	
	public Mouse(){
		
	}

	@Override
	public void setInput(Input input){

	}

	@Override
	public boolean isAcceptingInput(){
		return true;
	}

	@Override
	public void inputEnded(){

	}

	@Override
	public void inputStarted(){

	}

	@Override
	public void mouseWheelMoved(int change){

	}

	@Override
	public void mouseClicked(int button,int x,int y,int clickCount){
		
	}

	@Override
	public void mousePressed(int button,int x,int y){
		switch(button){
			case BUTTON_LEFT:
				if(objSelected!=null){
					objMove(x,y);
				}
				else
				{
					View mouseInView=View.getView(x,y);
					OverworldObject obj=OverworldObject.getObjInPos((int)(Math.floor((x/mouseInView.viewXScale+mouseInView.getDrawX()-mouseInView.viewXOffset)/OverworldObject.tileWidth)),(int)(Math.floor((y/mouseInView.viewYScale+mouseInView.getDrawY()-mouseInView.viewYOffset)/OverworldObject.tileHeight)));
					if(obj!=null){
						objSelected=obj;
						objSelectMouseX=x;
						objSelectMouseY=y;
					}
				}
				break;
				
			case BUTTON_RIGHT:
				View mouseInView=View.getView(x,y);
				OverworldObject obj=OverworldObject.getObjInPos((int)(Math.floor((x/mouseInView.viewXScale+mouseInView.getDrawX()-mouseInView.viewXOffset)/OverworldObject.tileWidth)),(int)(Math.floor((y/mouseInView.viewYScale+mouseInView.getDrawY()-mouseInView.viewYOffset)/OverworldObject.tileHeight)));
				if(obj!=null){
					obj.destroy();
				}
				break;
		}
	}

	@Override
	public void mouseReleased(int button,int x,int y){
		switch(button){
			case BUTTON_LEFT:
				if(objSelected!=null&&x!=objSelectMouseX&&y!=objSelectMouseY){
					objMove(x,y);
				}
				break;
		}
	}

	public void objMove(int x,int y){
		View mouseInView=View.getView(x,y);
		objSelected.posSetX((int)(Math.floor((x/mouseInView.viewXScale+mouseInView.getDrawX()-mouseInView.viewXOffset)/OverworldObject.tileWidth)));
		objSelected.posSetY((int)(Math.floor((y/mouseInView.viewYScale+mouseInView.getDrawY()-mouseInView.viewYOffset)/OverworldObject.tileHeight)));
		objSelected=null;
		objSelectMouseX=-1;
		objSelectMouseY=-1;
	}
	
	@Override
	public void mouseMoved(int oldx,int oldy,int newx,int newy){
		if(objSelected!=null){
			mousePosX=newx;
			mousePosY=newy;}
	}

	@Override
	public void mouseDragged(int oldx,int oldy,int newx,int newy){
		if(objSelected!=null){
			mousePosX=newx;
			mousePosY=newy;}
	}

	@Override
	public void render(Graphics g){
		if(objSelected!=null){
			View mouseInView=View.getView(mousePosX,mousePosY);
			float tileW=OverworldObject.tileWidth*mouseInView.viewXScale,tileH=OverworldObject.tileHeight*mouseInView.viewYScale;
			g.setColor(Color.yellow);
			g.drawRect((objSelected.getXPos()-mouseInView.getDrawX())*mouseInView.viewXScale,(objSelected.getYPos()-mouseInView.getDrawY())*mouseInView.viewYScale,objSelected.collisionMask.getWidth()*OverworldObject.tileWidth*mouseInView.viewXScale,objSelected.collisionMask.getHeight()*OverworldObject.tileHeight*mouseInView.viewYScale);
			g.setColor(new Color(255,128,0,160));
			g.drawRect((int)(Math.floor((mousePosX+((mouseInView.getDrawX()*mouseInView.viewXScale)%tileW))/tileW)*tileW)-((mouseInView.getDrawX()*mouseInView.viewXScale)%tileW)
					  ,(int)(Math.floor((mousePosY+((mouseInView.getDrawY()*mouseInView.viewYScale)%tileH))/tileH)*tileH)-((mouseInView.getDrawY()*mouseInView.viewYScale)%tileH)
					  ,tileW
					  ,tileH);
			g.setColor(Color.white);
		}
	}
}
