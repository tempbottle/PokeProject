package me.EdwJes.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

//TODO: Fix better code, needs to be more flexible
public class ImageLoader {
	public final String IMAGE_DIR;
	public final int IMAGE_FILTER=Image.FILTER_NEAREST;
	
	public enum Name{
		Brendan(0,14,22),
		May(1,16,20),
		LittleGirl(2,14,22),
		Red(3,14,22);
		private final int id,w,h;
		Name(int id,int sprWidth,int sprHeight){
			this.id=id;
			this.w=sprWidth;
			this.h=sprHeight;}
		public int get(){
			return this.id;}
		public int getWidth(){
			return this.w;}
		public int getHeight(){
			return this.h;}
	}
	public Map<Name,Image> rawImage = new HashMap<Name,Image>();
	public Map<Name,Animation[]> animatedSprite = new HashMap<Name,Animation[]>();
	
	public ImageLoader(String dir){
		IMAGE_DIR = PokemonProject.WORK_DIR + dir;
		SpriteSheet sprSheet;
		
		try{
			for(Name n:Name.values()){
				String filePath="trainer_"+n.toString()+".png";
				
				rawImage.put(n,loadImage(filePath));
				sprSheet = new SpriteSheet(rawImage.get(n),n.getWidth(),n.getHeight());
				animatedSprite.put(n,animatedSpriteEntity(sprSheet));}}
		catch(SlickException e){
			e.printStackTrace();}
	}
	
	public boolean fileExists(String filePath){
		return (new File(filePath)).exists();}
	
	public Image loadImage(String filePath) throws SlickException{
		Image img = new Image(IMAGE_DIR + filePath);
		img.setFilter(IMAGE_FILTER);
		return img;
	}
	
	public Animation[] animatedSpriteEntity(SpriteSheet sheet){
	Animation[] anim=new Animation[4];
	for(int i=0;i<4;i++)
		anim[i]=new Animation(new Image[]{sheet.getSprite(0,i),sheet.getSprite(1,i),sheet.getSprite(0,i),sheet.getSprite(2,i)},100);
	return anim;
	}
}
