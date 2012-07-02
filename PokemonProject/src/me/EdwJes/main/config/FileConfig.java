package me.EdwJes.main.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import me.EdwJes.main.config.ConfigData.InputType;

import org.ho.yaml.Yaml;
import org.ho.yaml.YamlConfig;
import org.ho.yaml.YamlEncoder;

public class FileConfig extends Config{

	public FileConfig(String configPath){
		super();
		try{
			File file=new File(configPath);
			OutputStream out=new FileOutputStream(file);
			YamlConfig config=new YamlConfig();
			
			YamlEncoder configWrite=new YamlEncoder(out,config);
			configWrite.setIndentAmount("  ");
			configWrite.setMinimalOutput(true);

			ConfigData p= new ConfigData();
			p.inputId=0;
			p.inputType=InputType.KEYBOARD;
			p.spriteSheet="lala.png";
	
			configWrite.writeObject(p);
			config.dump(p, file);
			}
		catch(FileNotFoundException e){e.printStackTrace();}
	}
}
