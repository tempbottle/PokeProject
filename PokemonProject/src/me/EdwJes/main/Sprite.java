package me.EdwJes.main;

import java.util.HashMap;
import java.util.Map;

import me.EdwJes.main.fileresourceloader.ImageLoader;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Sprite {
	public static Map<Name,Image> rawImage = new HashMap<Name,Image>();
	private static Map<Name,Animation[]> animatedSpriteEntity = new HashMap<Name,Animation[]>();
	private static Map<Name,Animation> animatedSprite = new HashMap<Name,Animation>();

	
	public static enum Name{
		Brendan(14,22),
		May(16,20),
		LittleGirl(14,22),
		Red(14,22),
		Lyra(27,27);
		
		private final int id,w,h;
		
		Name(int sprWidth,int sprHeight){
			//this.id=values().length;
			this.id=0;
			this.w=sprWidth;
			this.h=sprHeight;}
		
		public int get(){
			return this.id;}
		
		public int getWidth(){
			return w;}
		
		public int getHeight(){
			return h;}
	}
	
	public static void loadAllEntities(ImageLoader loader){
		SpriteSheet sprSheet;
		try{
			for(Name n:Name.values()){
				String filePath="trainer_"+n.toString()+".png";
				
				rawImage.put(n,loader.loadImage(filePath));
				sprSheet = new SpriteSheet(rawImage.get(n),n.getWidth(),n.getHeight());
				animatedSpriteEntity.put(n,getAnimatedSpriteEntity(sprSheet));}}
		catch(SlickException e){
			e.printStackTrace();}
	}
	
	public static Animation[] getEntity(Name name){
		return animatedSpriteEntity.get(name);
	}
	
	public static Animation get(Name name){
		return animatedSprite.get(name);
	}
	
	public static Animation[] getAnimatedSpriteEntity(SpriteSheet sheet){
	Animation[] anim=new Animation[4];
	for(int i=0;i<4;i++)
		anim[i]=new Animation(new Image[]{sheet.getSprite(0,i),sheet.getSprite(1,i),sheet.getSprite(0,i),sheet.getSprite(2,i)},100);
	return anim;
	}
}