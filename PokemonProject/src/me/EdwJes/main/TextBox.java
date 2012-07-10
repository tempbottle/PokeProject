package me.EdwJes.main;

import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.Config.Key;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TextBox extends RenderableObject implements PlayerInputControlObject{
	private String text,processedText="";
	public TextboxImage boxImg;
	public Color boxInnerColor;
	public int textSpeedDivider=4;
	private int processTextTick=0,processedTextCount=0;
	private boolean finished=false;
	private PlayerInput player;
	private int rows=3;
	
	public TextBox(String text,View view){
		init(text,view,PlayerInput.getPlayerInput(view));
	}
	
	public TextBox(String text,PlayerInput player){
		init(text,player.view,player);
	}
	
	private void init(String text,View view,PlayerInput player){
		this.text=text;
		this.layer=LAYER_GUI;
		this.boxImg=new TextboxImage(view.textboxImage,7,4,18,4);//TODO: Textbox style to be defined in configuration file, make new class named TextboxStyle and a new yml containing the style information in the textbox resource folder
		this.boxInnerColor=new Color(248,248,248,176);
		this.view=view;
		this.player=player;
		player.setObj(this);
	}
	
	@Override
	public void render(Graphics g,View view) {
		Font font=g.getFont();
		float drawRight=view.viewWidth-boxImg.rightW,drawTop=font.getLineHeight()*rows;
		
		//BACKGROUND
		for(int ix=boxImg.leftW;ix<drawRight;ix+=boxImg.innerW){
			g.drawImage(boxImg.topSide,view.getDrawScreenX(ix),view.getDrawScreenY(view.viewHeight-drawTop-boxImg.borderH));
			g.drawImage(boxImg.bottomSide,view.getDrawScreenX(ix),view.getDrawScreenY(view.viewHeight-boxImg.bottomH));}
		for(int iy=(int)(view.viewHeight-drawTop-boxImg.bottomH);iy<view.viewHeight-boxImg.bottomH;iy+=boxImg.innerH){
			g.drawImage(boxImg.sideLeft,view.getDrawScreenX(0),view.getDrawScreenY(iy));
			g.drawImage(boxImg.sideRight,view.getDrawScreenX(drawRight),view.getDrawScreenY(iy));}
		g.setColor(boxInnerColor);
		g.fillRect(view.getDrawScreenX(boxImg.leftW),view.getDrawScreenY(view.viewHeight-drawTop-boxImg.bottomH),drawRight-boxImg.leftW,drawTop);
		g.setColor(Color.white);
		
		//Text
		g.drawString(processedText, view.getDrawScreenX(boxImg.leftW),view.getDrawScreenY(view.viewHeight-drawTop-boxImg.bottomH));
		//TOP LEFT BORDER
		g.drawImage(boxImg.topLeft,view.getDrawScreenX(0),view.getDrawScreenY(view.viewHeight-drawTop-boxImg.borderH));
		//BOTTOM LEFT BORDER
		g.drawImage(boxImg.bottomLeft, view.getDrawScreenX(0), view.getDrawScreenY(view.viewHeight-boxImg.bottomH));
		//TOP RIGHT BORDER
		g.drawImage(boxImg.topRight, view.getDrawScreenX(drawRight), view.getDrawScreenY(view.viewHeight-drawTop-boxImg.borderH));
		//BOTTOM RIGHT BORDER
		g.drawImage(boxImg.bottomRight, view.getDrawScreenX(drawRight), view.getDrawScreenY(view.viewHeight-boxImg.bottomH));
	}

/*	@Override
	public void onKeyActionPressed() {
		PokemonProject.player.setObj(PokemonProject.player.objPrevious);
	}*/

	@Override
	public void destroy(){
		player.setObj(PlayerInput.getPlayerInput(view).objPrevious);
		super.destroy();
	}
	
	@Override
	public void update(){
		super.update();
		
		if(!finished){
			if(processedTextCount<text.length()){
				if(processTextTick>=textSpeedDivider){
					processTextTick=0;
					addProcessedText(1);}
				else
					processTextTick++;
			}
			else finished=true;
		}
	}
	
	public void addProcessedText(int i) {
		processedTextCount=Math.max(Math.min(processedTextCount+i,text.length()),0);
		processedText=text.substring(0,processedTextCount);
	}
	
	public void setProcessedText(int i) {
		processedTextCount=Math.max(Math.min(i,text.length()),0);
		processedText=text.substring(0,processedTextCount);
	}

	@Override
	public float getXPos() {
		return 0;
	}

	@Override
	public float getYPos() {
		return 0;
	}

	@Override
	public int getXTile() {
		return 0;
	}

	@Override
	public int getYTile() {
		return 0;
	}

	@Override
	public void handleInput(Input input, int playerId,Config config) {

	}

	@Override
	public void onKeyPressed(int key, char chr, int playerId,Config config) {
		/*PlayerInput player=PlayerInput.getPlayerInput(playerId);
		player.setObj(player.objPrevious);*/
		if(key==config.player.get(playerId).keyMap.get(Key.ACTION)){
			if(finished)
				destroy();
			else
				addProcessedText(2);
		}
	}

	@Override
	public void onKeyReleased(int key, char chr, int playerId,Config config) {
		
	}
}

class TextboxImage{
	/*     TextboxImage Variables Layout
	 * ╔═══════════╤═══════════╤═══════════╗ ←╤
	 * ║0,0        |1,0        |2,0        ║  |
	 * ║topLeft    |topSide    |topRight   ║ ◄| 0: topH
	 * ║           |           |           ║  |
	 * ╟-----------┼-----------┼-----------╢ ←╪
	 * ║0,1        |1,1        |2,1        ║  |
	 * ║sideLeft   |inner      |sideRight  ║ ◄| 1: innerH
	 * ║           |           |           ║  |
	 * ╟-----------┼-----------┼-----------╢ ←╪
	 * ║0,2        |1,2        |2,2        ║  |
	 * ║bottomLeft |bottomSide |bottomRight║ ◄| 2: bottomH
	 * ║           |           |           ║  |
	 * ╚═══════════╧═══════════╧═══════════╝ ←╧
	 * ↑_____▲_____↑_____▲_____↑_____▲_____↑  Y-axis
	 * ║0: leftW   ║1: innerW  ║2: rightW  ║ X-axis
	 * */
	Image image,topLeft,topRight,bottomLeft,bottomRight,sideLeft,sideRight,topSide,bottomSide,inner;
	int leftW,topH,rightW,bottomH,innerW,innerH,borderH,borderW,imgW,imgH;
	public TextboxImage(Image img,int leftW,int topH,int rightW,int bottomH){
		image=img;
		imgW=img.getWidth();
		imgH=img.getHeight();
		
		this.leftW=leftW;
		this.rightW=rightW;
		this.topH=topH;
		this.bottomH=bottomH;
		
		this.borderW=leftW+rightW;
		this.borderH=topH+bottomH;
		
		this.innerW=imgW-borderW;
		this.innerH=imgH-borderH;
		
		topLeft=img.getSubImage(0,0,leftW,topH);
		sideLeft=img.getSubImage(0,topH,leftW,imgH-topH-bottomH);
		bottomLeft=img.getSubImage(0,imgH-bottomH,leftW,bottomH);

		topRight=img.getSubImage(imgW-rightW,0,rightW,topH);
		sideRight=img.getSubImage(imgW-rightW,topH,rightW,imgH-topH-bottomH);
		bottomRight=img.getSubImage(imgW-rightW,imgH-bottomH,rightW,bottomH);
		
		topSide=img.getSubImage(leftW,0,imgW-leftW-rightW,topH);
		inner=img.getSubImage(leftW,topH, imgW-leftW-rightW, imgH-topH-bottomH);
		bottomSide=img.getSubImage(leftW,imgH-bottomH,imgW-leftW-rightW,bottomH);
	}
	
	public void destroy(){
		try{
			image.destroy();
			image=null;
			topLeft.destroy();
			topLeft=null;
			topRight.destroy();
			topRight=null;
			bottomLeft.destroy();
			bottomLeft=null;
			bottomRight.destroy();
			bottomRight=null;
			sideLeft.destroy();
			sideLeft=null;
			sideRight.destroy();
			sideRight=null;
			topSide.destroy();
			topSide=null;
			bottomSide.destroy();
			bottomSide=null;
			inner.destroy();
			inner=null;}
		catch(SlickException e){
			e.printStackTrace();
		}
	}
}