package me.EdwJes.main.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

public class FileConfig extends Config{
	private String configPath;
	
	public FileConfig(String configPath){
		super();
		this.configPath=configPath;
	}
	
	public FileConfig(){
		super();
	}
	
	@Override
	public void loadValues(){
		super.loadValues();

		try{
			InputStream input = new FileInputStream(new File(configPath));
			Yaml yaml=new Yaml();
			Config config=yaml.loadAs(input,Config.class);
			load(config);
		}
		catch(FileNotFoundException e1){
			e1.printStackTrace();}
	}
	
	public void saveValues(){
		try{
			FileWriter fstream = new FileWriter(configPath);
			BufferedWriter out=new BufferedWriter(fstream);
			Yaml yaml=new Yaml();
			out.write(yaml.dump(this));
			out.close();}
		catch(IOException e){e.printStackTrace();}
	}
}
