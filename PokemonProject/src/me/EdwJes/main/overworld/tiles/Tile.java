package me.EdwJes.main.overworld.tiles;

import org.newdawn.slick.Image;

public class Tile{
	int x,y,w=0,h=0,depth=0;
	Image tileImage;
	
	public Tile(Image img,int x,int y){
		this.tileImage=img;
		this.x=x;
		this.y=y;}
	
	public Tile(Image img,int x,int y,int w,int h){
		this.tileImage=img;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;}
}
