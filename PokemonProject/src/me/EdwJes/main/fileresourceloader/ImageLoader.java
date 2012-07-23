package me.EdwJes.main.fileresourceloader;

import java.io.File;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;

//TODO: Fix better code, needs to be more flexible
//TODO: Import Pokemon Overworld: http://spriters-resource.com/ds/pokemonblackwhite/owoldgen.png
//TODO: Import Items: http://spriters-resource.com/ds/pokeheartgoldsoulsilver/items.png
//TODO: Import Battle GUI: http://spriters-resource.com/ds/pokeheartgoldsoulsilver/hpbars.png
//TODO: Pokemons: http://spriters-resource.com/ds/pokeheartgoldsoulsilver/pokemonicons.png
public abstract class ImageLoader {
	public static final int IMAGE_FILTER=Image.FILTER_NEAREST;
	public static final Image nullImage=getNullImage();
	public static final Animation nullAnimation=new Animation(new Image[]{nullImage},100);
	
	private static Image getNullImage(){
		ImageBuffer img=new ImageBuffer(32,32);
		for(int ix=0;ix<32;ix++)
			for(int iy=0;iy<32;iy++)
				img.setRGBA(ix, iy, 0, 0, 255, 96);
		return img.getImage(IMAGE_FILTER);
	}
	
	public static boolean fileExists(String filePath){
		return (new File(filePath)).exists();}
	
	public static Image loadImage(String filePath){
		Image img;
		try{
			img = new Image(filePath,false,IMAGE_FILTER);}
		catch(SlickException e){
			System.out.println("File: \"" + filePath+"\" don't exist");
			img = nullImage;
			System.out.println(e.toString());
		}
		return img;
	}
	
	public static Image unloadImage(Image img) throws SlickException{
		img.destroy();
		return null;
	}
}
