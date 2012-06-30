package me.EdwJes.main.fileresourceloader;

import java.io.File;

import me.EdwJes.main.PokemonProject;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//TODO: Fix better code, needs to be more flexible
//TODO: Import Pokemon Overworld: http://spriters-resource.com/ds/pokemonblackwhite/owoldgen.png
//TODO: Import Items: http://spriters-resource.com/ds/pokeheartgoldsoulsilver/items.png
//TODO: Import Battle GUI: http://spriters-resource.com/ds/pokeheartgoldsoulsilver/hpbars.png
//TODO: Pokemons: http://spriters-resource.com/ds/pokeheartgoldsoulsilver/pokemonicons.png
public class ImageLoader {
	public final String IMAGE_DIR;
	public final int IMAGE_FILTER=Image.FILTER_NEAREST;
	
	public ImageLoader(String dir){
		IMAGE_DIR = PokemonProject.WORK_DIR + dir;
	}
	
	public boolean fileExists(String filePath){
		return (new File(filePath)).exists();}
	
	public Image loadImage(String filePath) throws SlickException{
		Image img = new Image(IMAGE_DIR + filePath);
		img.setFilter(IMAGE_FILTER);
		return img;
	}
	
	public Image unloadImage(Image img) throws SlickException{
		img.destroy();
		return null;
	}
}
