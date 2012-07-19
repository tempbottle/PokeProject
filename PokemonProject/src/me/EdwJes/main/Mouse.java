package me.EdwJes.main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class Mouse extends RenderableObject implements MouseListener {
	OverworldObject objSelected=null;
	int objSelectMouseX=-1,objSelectMouseY=-1,mousePosX=0,mousePosY=0;
	final static int BUTTON_LEFT=0,BUTTON_RIGHT=1,BUTTON_MIDDLE=2;
	
	public Mouse(){
		setLayer(LAYER_GUI);
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
					OverworldObject obj=OverworldObject.getObjInPos((int)Math.floor((x/mouseInView.viewXScale+mouseInView.getDrawX())/16),(int)Math.floor((y/mouseInView.viewYScale+mouseInView.getDrawY())/16));
					if(obj!=null){
						objSelected=obj;
						objSelectMouseX=x;
						objSelectMouseY=y;
					}
				}
				break;
				
			case BUTTON_RIGHT:
				View mouseInView=View.getView(x,y);
				OverworldObject obj=OverworldObject.getObjInPos((int)Math.floor((x/mouseInView.viewXScale+mouseInView.getDrawX())/16),(int)Math.floor((y/mouseInView.viewYScale+mouseInView.getDrawY())/16));
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
		objSelected.posSetX((int)Math.floor((x/mouseInView.viewXScale+mouseInView.getDrawX())/16));
		objSelected.posSetY((int)Math.floor((y/mouseInView.viewYScale+mouseInView.getDrawY())/16));
		objSelected=null;
		objSelectMouseX=-1;
		objSelectMouseY=-1;
	}
	
	@Override
	public void mouseMoved(int oldx,int oldy,int newx,int newy){
		mousePosX=newx;
		mousePosY=newy;
	}

	@Override
	public void mouseDragged(int oldx,int oldy,int newx,int newy){
		mousePosX=newx;
		mousePosY=newy;
	}

	@Override
	public void render(Graphics g,View view){
		if(objSelected!=null){
			g.setColor(Color.yellow);
			g.drawRect(objSelected.getXPos()-view.getDrawX(),objSelected.getYPos()-view.getDrawY(),objSelected.collisionMask.getWidth()*OverworldObject.tileWidth,objSelected.collisionMask.getHeight()*OverworldObject.tileHeight);
			g.setColor(new Color(255,128,0,160));
			g.drawRect((int)Math.floor(mousePosX/(OverworldObject.tileWidth*view.viewXScale))*OverworldObject.tileWidth
					  ,(int)(Math.floor(mousePosY/(OverworldObject.tileHeight*view.viewYScale))*OverworldObject.tileHeight)
					  ,OverworldObject.tileWidth
					  ,OverworldObject.tileHeight);
			g.setColor(Color.white);
		}
	}
}
