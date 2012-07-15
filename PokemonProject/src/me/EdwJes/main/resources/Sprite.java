package me.EdwJes.main.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.fileresourceloader.ImageLoader;

public class Sprite{
	private Sprite(){}
	
	public static String configFilename=PokemonProject.RESOURCES_DIR+"sprites.yml";
	public static HashMap<String,Image> image=new HashMap<String,Image>();
	private static HashMap<String,ArrayList<Image>> imageGroup=new HashMap<String,ArrayList<Image>>();
	private static HashMap<String,Animation> animation=new HashMap<String,Animation>();
	private static HashMap<String,ArrayList<Animation>> animationGroup=new HashMap<String,ArrayList<Animation>>();
	
	private static void testOutput() throws IOException{
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		
		FileOutputStream file=new FileOutputStream(configFilename);
		Yaml yaml=new Yaml(options);
		
		HashMap<String,HashMap<String,Object>> data = new HashMap<String,HashMap<String,Object>>();
		
		HashMap<String,Object> subData=new HashMap<String,Object>();
		subData.put("filename","entity_oldman.png");
		subData.put("path","/resources/images/");
		HashMap<String,Object> spriteSheetData=new HashMap<String,Object>();
		spriteSheetData.put("imageWidth",24);
		spriteSheetData.put("imageHeight",32);
		spriteSheetData.put("horizontalCount",3);
		spriteSheetData.put("verticalCount",3);
		spriteSheetData.put("spacing",0);
		spriteSheetData.put("split",3);
		spriteSheetData.put("imageCount",9);
		HashMap<String,Object> animationData=new HashMap<String,Object>();
		List<Integer> animationSpeedData=new ArrayList<Integer>();
		animationSpeedData.add(0);
		animationData.put("animationSpeed",animationSpeedData);
		spriteSheetData.put("animation",animationData);
		subData.put("spriteSheet",spriteSheetData);
		subData.put("xOffset",0);
		subData.put("yOffset",0);
		
		data.put("OldMan",subData);
		
		file.write(yaml.dump(data).getBytes());
		file.close();
	}
	
	@SuppressWarnings("unchecked")
	public static void load() throws IOException{	
		FileInputStream file=new FileInputStream(configFilename);
		Yaml yaml=new Yaml();
		//TODO: Cast to HashMap with the sprite data Object, if it isn't a map: load default values
		HashMap<String,HashMap<String,Object>> data=(HashMap<String,HashMap<String,Object>>)yaml.load(file);
		file.close();
		//TODO Pre-sets of options
		for(Map.Entry<String,HashMap<String,Object>> sprDat:data.entrySet()){//For every list item of sprite data...
			final String name=sprDat.getKey();
			HashMap<String,Object> sprData=sprDat.getValue();
			
			//Check for required values
			if(!(sprData.containsKey("filename")&&String.class.isInstance(sprData.get("filename"))
			  /*&& sprData.containsKey("name")&&String.class.isInstance(sprData.get("name"))*/)){
				System.out.println("sprite.yml: "+name+" - Missing or wrong type on required value 'filename'");
				continue;
			}
			
			int xOffset=0,yOffset=0,width=0,height=0;
			final String filename=(String)sprData.get("filename");
			
			//Get some optional values, if it doesn't exists or is wrong type: keeps the default value
			if(sprData.containsKey("width")&&Integer.class.isInstance(sprData.get("width")))
				width=(Integer)sprData.get("width");
			
			if(sprData.containsKey("height")&&Integer.class.isInstance(sprData.get("height")))
				height=(Integer)sprData.get("height");
			
			if(sprData.containsKey("xOffset")&&Integer.class.isInstance(sprData.get("xOffset")))
				xOffset=(Integer)sprData.get("xOffset");
			
			if(sprData.containsKey("yOffset")&&Integer.class.isInstance(sprData.get("yOffset")))
				yOffset=(Integer)sprData.get("yOffset");
			
			Image rawImage=null,rawImageOriginal=ImageLoader.loadImage(filename);
			if(xOffset!=0||yOffset!=0){
				if(width==0)width=rawImageOriginal.getWidth()-xOffset;
				if(height==0)height=rawImageOriginal.getHeight()-yOffset;
				rawImage=rawImageOriginal.getSubImage(xOffset, yOffset, width, height);
			}
			else
				rawImage=rawImageOriginal;
			
			//If this is a spriteSheet...
			if(sprData.containsKey("spriteSheet")&&HashMap.class.isInstance(sprData.get("spriteSheet"))){
				int sheetW=0,sheetH=0,spacing=0,imageCount=1;
				HashMap<String,Object> spriteSheetData=(HashMap<String,Object>)sprData.get("spriteSheet");
				
				if(mapContainsKey(spriteSheetData,"spacing",Integer.class))
					spacing=(Integer)spriteSheetData.get("spacing");

				if(mapContainsKey(spriteSheetData,"imageWidth",Integer.class))
					sheetW=(Integer)spriteSheetData.get("imageWidth");
				else if(mapContainsKey(spriteSheetData,"imageXCount",Integer.class))
					sheetW=rawImage.getWidth()/((Integer)spriteSheetData.get("imageXCount"));
				else{
					System.out.println("sprite.yml: "+name+" - Missing or wrong type on required value 'spriteSheet.imageWidth'");
					continue;}
				
				if(mapContainsKey(spriteSheetData,"imageHeight",Integer.class))
					sheetH=(Integer)spriteSheetData.get("imageHeight");
				else if(mapContainsKey(spriteSheetData,"imageYCount",Integer.class))
					sheetH=rawImage.getHeight()/((Integer)spriteSheetData.get("imageYCount"));
				else{
					System.out.println("sprite.yml: "+name+" - Missing or wrong type on required value 'spriteSheet.imageHeight'");
					continue;}
				
				if(mapContainsKey(spriteSheetData,"imageCount",Integer.class))
					imageCount=Math.min((Integer)spriteSheetData.get("imageCount"),(rawImage.getWidth()/sheetW)*(rawImage.getHeight()/sheetH));
				else{
					System.out.println("sprite.yml: "+name+" - Missing or wrong type on required value 'spriteSheet.imageCount'");
					continue;}
					

				SpriteSheet sprSheet = new SpriteSheet(rawImage,sheetW,sheetH,spacing);
				Image[] imgsInSheet=new Image[imageCount];
				for(int i=0,ix=0,iy=0;i<imageCount;i++){						
					if(ix>=sprSheet.getHorizontalCount()){
						ix=0;
						iy++;
						if(iy>=sprSheet.getVerticalCount()){
							System.out.println("sprite.yml: "+name+" - imageCount should not be more than horizontalCount*verticalCount");
							break;}}
					imgsInSheet[i]=sprSheet.getSprite(ix,iy);
					ix++;}
				
				//If this should use the sprite sheet as a animation
				if(spriteSheetData.containsKey("animation")&&HashMap.class.isInstance(spriteSheetData.get("animation"))){
					HashMap<String,Object> animationData=(HashMap<String,Object>)spriteSheetData.get("animation");
					ArrayList<Integer> animationSpeed=new ArrayList<Integer>();
					animationSpeed.add(100);
					boolean loop=true,pingpong=false;

					if(animationData.containsKey("animationSpeed")){
						if(ArrayList.class.isInstance(animationData.get("animationSpeed"))){
							animationSpeed=(ArrayList<Integer>)animationData.get("animationSpeed");
						}
						else if(Integer.class.isInstance(animationData.get("animationSpeed"))){
							animationSpeed.set(0,(Integer)animationData.get("animationSpeed"));
						}
					}
					
					if(animationData.containsKey("loop")&&Boolean.class.isInstance(animationData.get("loop")))
						loop=(Boolean)animationData.get("loop");
					
					if(animationData.containsKey("pingpong")&&Boolean.class.isInstance(animationData.get("pingpong")))
						pingpong=(Boolean)animationData.get("pingpong");

					if(animationData.containsKey("imagesInGroup")){
						int imagesInGroup=1;
						if(Boolean.class.isInstance(animationData.get("imagesInGroup"))&&(Boolean)animationData.get("imagesInGroup")==true){
							imagesInGroup=sprSheet.getHorizontalCount();
							ArrayList<Animation> animList=new ArrayList<Animation>();
							for(int i=0;i<imgsInSheet.length/imagesInGroup;i++){
								Image[] imgsInSheetToGroup=new Image[imagesInGroup];
								for(int j=0;j<imagesInGroup;j++){
									imgsInSheetToGroup[j]=imgsInSheet[i*imagesInGroup+j];
								}
								Animation anim=new Animation(imgsInSheetToGroup,animationSpeed.get(0));
								anim.setLooping(loop);
								anim.setPingPong(pingpong);
								animList.add(anim);
								animationGroup.put(name,animList);}
						}
						else if(Integer.class.isInstance(animationData.get("imagesInGroup"))&&(Integer)animationData.get("imagesInGroup")>0){
							imagesInGroup=(Integer)animationData.get("imagesInGroup");
							ArrayList<Animation> animList=new ArrayList<Animation>();
							for(int i=0;i<imgsInSheet.length/imagesInGroup;i++){
								Image[] imgsInSheetToGroup=new Image[imagesInGroup];
								for(int j=0;j<imagesInGroup;j++){
									imgsInSheetToGroup[j]=imgsInSheet[i*imagesInGroup+j];
								}
								Animation anim=new Animation(imgsInSheetToGroup,animationSpeed.get(0));
								anim.setLooping(loop);
								anim.setPingPong(pingpong);
								animList.add(anim);
								animationGroup.put(name,animList);}
						}
						else{
							System.out.println("sprite.yml: "+name+" - imagesInGroup have unknown value");
							continue;
						}
					}
					else{
						//TODO: Invidual animationSpeed
						Animation anim=new Animation(imgsInSheet,animationSpeed.get(0));
						anim.setLooping(loop);
						anim.setPingPong(pingpong);
						animation.put(name,anim);
					}
				}
				else{//Else if this is not an animation but only a spriteSheet: Put in image group
					imageGroup.put(name,new ArrayList<Image>(Arrays.asList(imgsInSheet)));
				}
				//Free resources, cannot do this apparently because the subimages and the sprite sheet references to the image of origin
				/*try{
					for(Image imgFree:imgsInSheet){
						imgFree.destroy();}
					imgsInSheet=null;}
				catch(SlickException e){e.printStackTrace();}
				try{
					sprSheet.destroy();
					sprSheet=null;}
				catch(SlickException e){e.printStackTrace();}
				try{
					rawImageOriginal.destroy();
					rawImageOriginal=null;
					rawImage=null;}
				catch(SlickException e){e.printStackTrace();}*/
			}
			else{//Else load it as a normal image
				image.put(name,rawImage);
			}
		}
	}
	
	private static <K,V> boolean mapContainsKey(Map<K,Object> map,K key,Class<V> VType){
		return map.containsKey(key)&&VType.isInstance(map.get(key));
	} 
	
/*	@SuppressWarnings("unchecked")
	public static <K,V> V mapGetKey(Map<K,Object> map,K key,Class<V> VType){
		Object valueObj=map.get(key);
		if(map.containsKey(key)&&VType.isInstance(valueObj))
			return (V)valueObj;
		else
			return null;
	} 
	
	public static Map<String,Object> getDefault(){
		return null;
	}*/
	
	public static Image getImage(String name){
		if(image.containsKey(name))
			return image.get(name);
		else
			return ImageLoader.nullImage;
	}
	
	public static Animation getAnimation(String name){
		if(animation.containsKey(name))
			return animation.get(name);
		else
			return ImageLoader.nullAnimation;
	}
	
	public static ArrayList<Image> getImageGroup(String name){
		//if(imageGroup.containsKey(spriteName))
			return imageGroup.get(name);
		//else
		//	return ImageLoader.nullImage;
	}
	
	public static ArrayList<Animation> getAnimationGroup(String name){
		if(animationGroup.containsKey(name))
			return animationGroup.get(name);
		else{
			ArrayList<Animation> animList=new ArrayList<Animation>();
			animList.add(ImageLoader.nullAnimation);
			return animList;}
	}
	
	public static HashMap<String,ArrayList<Animation>> listAnimationGroup(){
			return new HashMap<String,ArrayList<Animation>>(animationGroup);
	}
	
	public static HashMap<String,ArrayList<Image>> listImageGroup(){
		return new HashMap<String,ArrayList<Image>>(imageGroup);
}
	
}
