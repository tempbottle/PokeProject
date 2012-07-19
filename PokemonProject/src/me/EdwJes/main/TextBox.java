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
	public BorderImage boxImg;
	public Color boxInnerColor;
	public int textSpdNormalDivider=4,textSpdFastDivider=1,currentTextSpd=textSpdNormalDivider;
	private int processTextTick=0,processedTextCount=0;
	private boolean finished=false;
	private PlayerInput player;
	private int rows=3;
	
	public TextBox(String text,BorderImage boxImg,Color boxInnerCol,View view){
		init(text,boxImg,boxInnerCol,view,PlayerInput.getPlayerInput(view));
	}
	
	public TextBox(String text,BorderImage boxImg,Color boxInnerCol,PlayerInput player){
		init(text,boxImg,boxInnerCol,player.view,player);
	}
	
	private void init(String text,BorderImage boxImg,Color boxInnerCol,View view,PlayerInput player){
		this.text=text;
		this.layer=LAYER_GUI;
		this.boxImg=boxImg;
		this.boxInnerColor=boxInnerCol;
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
		player.removeObj(this);
		super.destroy();
	}
	
	@Override
	public void update(){
		super.update();
		
		if(!finished){
			if(processedTextCount<text.length()){
				if(processTextTick>=currentTextSpd){
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
				currentTextSpd=textSpdFastDivider;
		}
	}

	@Override
	public void onKeyReleased(int key, char chr, int playerId,Config config) {
		if(key==config.player.get(playerId).keyMap.get(Key.ACTION)){
			if(currentTextSpd==textSpdFastDivider)
				currentTextSpd=textSpdNormalDivider;}
	}
}