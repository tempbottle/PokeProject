package me.EdwJes.main.tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import me.EdwJes.main.OverworldObject;
import me.EdwJes.main.RenderableObject;
import me.EdwJes.main.View;
import me.EdwJes.main.fileresourceloader.ImageLoader;

public class Tiles extends RenderableObject{
	
	private List<Tile> list=new ArrayList<Tile>();
	public static Map<String,Image> rawImage = new HashMap<String,Image>();
	public static Map<String,SpriteSheet> sprSheet = new HashMap<String,SpriteSheet>();
	public Image TESTTILE;
	
	public Tiles(){
		layer=LAYER_OVERWORLD_GROUND;
	}
	
	public List<Tile> getTiles(){
		return list;}
	
	public void destroy(){
		super.destroy();
		for(SpriteSheet spr:sprSheet.values()){
			try{spr.destroy();}
			catch(SlickException e){e.printStackTrace();}}
		sprSheet=null;
		
		for(Image img:rawImage.values()){
			try{img.destroy();}
			catch(SlickException e){e.printStackTrace();}}
		rawImage=null;
		list.clear();
	}
	
	@Override
	public void render(Graphics g,View view) {
		for(Tile tile:getTiles()){
			for(int ix=0;ix<tile.w;ix++)
				for(int iy=0;iy<tile.h;iy++)
					g.drawImage(tile.tileImage, (tile.x+ix)*OverworldObject.tileWidth-view.getDrawX(), (tile.y+iy)*OverworldObject.tileHeight-view.getDrawY());
		}
	}
	
	public void LOAD_TESTING(ImageLoader loader){
		String name="NAMEOFTILESET";
		try{
			rawImage.put(name,loader.loadImage("/tiles/FRLG/ground.png"));
			sprSheet.put(name,new SpriteSheet(rawImage.get(name),rawImage.get(name).getWidth(),rawImage.get(name).getHeight()));
			TESTTILE=sprSheet.get(name).getSubImage(16, 16, 16, 16);
		}catch(SlickException e){
			e.printStackTrace();}
	}
	
	public Tile tileAdd(Image img,int x,int y){
		Tile tile=new Tile(img,x,y);
		list.add(tile);
		return tile;}
	
	public Tile tileAdd(Image img,int x,int y,int w,int h){
		Tile tile=new Tile(img,x,y,w,h);
		list.add(tile);
		return tile;}
	
	public void tileRemove(Tile tile){
		tile.tileImage=null;
		list.remove(tile);}
}

class Tile {
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