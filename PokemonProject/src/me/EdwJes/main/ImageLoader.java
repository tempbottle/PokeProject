package me.EdwJes.main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
//TODO: Fix better code, needs to be more flexible
public class ImageLoader {
	private final String IMAGE_DIR;
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
	public Image[] img = new Image[Name.values().length];
	public Animation[][] anim = new Animation[Name.values().length][4];
	
	public ImageLoader(String dir){
		IMAGE_DIR = PokemonProject.WORK_DIR + dir;
		SpriteSheet sprSheet;
		
		try{
			for(Name n:Name.values()){
				img[n.get()] = new Image(IMAGE_DIR + "trainer_"+n.toString()+".png");
				sprSheet = new SpriteSheet(img[n.get()],n.getWidth(),n.getHeight());
				for(int i=0;i<4;i++)
					anim[n.get()][i]=new Animation(new Image[]{sprSheet.getSprite(0,i),sprSheet.getSprite(1,i),sprSheet.getSprite(0,i),sprSheet.getSprite(2,i)},100);
			}
		}
		catch(SlickException e){
			e.printStackTrace();}
	}
}
