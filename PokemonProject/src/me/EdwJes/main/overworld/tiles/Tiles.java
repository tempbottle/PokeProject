package me.EdwJes.main.overworld.tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import me.EdwJes.main.PokemonGame;
import me.EdwJes.main.RenderableObject;
import me.EdwJes.main.View;
import me.EdwJes.main.fileresourceloader.ImageLoader;
import me.EdwJes.main.overworld.OverworldObject;

public class Tiles extends RenderableObject{
	
	private List<Tile> list=new ArrayList<Tile>();
	public static Map<String,Image> rawImage = new HashMap<String,Image>();
	public static Map<String,SpriteSheet> sprSheet = new HashMap<String,SpriteSheet>();
	public Image TESTTILE;
	
	public Tiles(){
		layer=LAYER_BELOWNORMAL;
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
					g.drawImage(tile.tileImage, (tile.x+ix)*OverworldObject.tileWidth, (tile.y+iy)*OverworldObject.tileHeight);
		}
	}
	
	public void LOAD_TESTING(){
		String name="NAMEOFTILESET";
		rawImage.put(name,ImageLoader.loadImage(PokemonGame.IMAGES_DIR+"/tiles/FRLG/ground.png"));
		sprSheet.put(name,new SpriteSheet(rawImage.get(name),rawImage.get(name).getWidth(),rawImage.get(name).getHeight()));
		TESTTILE=sprSheet.get(name).getSubImage(16, 16, 16, 16);
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