package me.EdwJes.main;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.EdwJes.debug.Debug;
import me.EdwJes.main.Entities.EntityHuman;
import me.EdwJes.main.rooms.Room;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Console extends RenderableObject implements PlayerInputControlObject{
	
	private boolean isOn = false,keyCTRL=false;
	private String input = "",inputFieldPrefix="> ";
	public char commandPrefix='/';
	private int inputPosition=0;
	private int MAX_ARGUMENTS=32;
	/*private int outputLines = 0;
	private int outputMaxLines = 6;*/
	
	public enum Command{
		test,shit,scale,skit,display,human,fullscreen,enterroom
	}
	
	public Console(){
		setLayer(LAYER_GUI);
		setPersistency(true);
	}
	//TODO: Custom input and output stream classes for server communication in the future
	@Override public void render(Graphics g){
		Font font=PokemonProject.font;
		g.setFont(font);
		int i=0,lineHeight=font.getLineHeight();
		for(ConsoleOutputText text:ConsoleOutputText.list){
			i++;
			int alpha=255;
			if(text.fadeOutTick<ConsoleOutputText.fadeOutTimer||isOn){
				int y=PokemonProject.SCREEN_HEIGHT - lineHeight*2-6 - ((ConsoleOutputText.list.size()-i)*16);
				if(!isOn)
					alpha=255-((int)(((float)text.fadeOutTick/ConsoleOutputText.fadeOutTimer)*255));
				g.setColor(new Color(255,255,255,alpha));
				g.drawString(text.getText(), 6, y);
				g.setColor(Color.white);}
		}
		if(isOn){
			g.setColor(Color.darkGray);
			g.fillRect(0,PokemonProject.SCREEN_HEIGHT-lineHeight-4,PokemonProject.SCREEN_WIDTH,PokemonProject.SCREEN_HEIGHT);
			g.setColor(Color.white);
			g.drawString(inputFieldPrefix + input, 6, PokemonProject.SCREEN_HEIGHT-lineHeight-2);
			int inputPositionWidth=PokemonProject.font.getWidth(inputFieldPrefix + input.substring(0,inputPosition) + " ")-2;
			g.setColor(Color.red);
			g.drawLine(inputPositionWidth+2, PokemonProject.SCREEN_HEIGHT-lineHeight,inputPositionWidth+2, PokemonProject.SCREEN_HEIGHT-4);
			g.setColor(Color.white);
		}
	}
	
	public void enterConsole(){
		isOn = true;
	}
	
	public void closeConsole(){
		inputReset();
		isOn=false;
	}
	
	public void outputConsole(String str){
		new ConsoleOutputText(str);
	}
	
	public void executeCommand(String str){//TODO:Check argument count and type
		if(str!=""&&!str.matches("([ ]*)")){
			onInput(str);
			if(str.charAt(0)==commandPrefix){
				str=str.substring(1);
				String[] command=str.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)",MAX_ARGUMENTS);
				int argumentCount=command.length-1;
				command[0]=command[0].toLowerCase();
				onCommand(command);
				if(commandExists(command[0])){
					switch(Command.valueOf(command[0])){
						case test:
							String _str="";
							for(int i=0;i<command.length;i++)
								_str+="["+i+"]="+command[i]+"; ";
							outputConsole(_str);
							break;
						case shit:
							outputConsole("Yes shit");
							break;
						case scale:
							PokemonProject.setScale(Float.valueOf(command[1].trim()).floatValue(),Float.valueOf(command[1].trim()).floatValue());
							outputConsole("Scaled the screen to "+command[1]+"x");
							break;
						case display:
							try{
								PokemonProject.setDisplayMode(Integer.valueOf(command[1].trim()).intValue(),Integer.valueOf(command[2].trim()).intValue(),PokemonProject.app.isFullscreen());}
							catch(NumberFormatException e){
								e.printStackTrace();}
							catch(SlickException e){
								e.printStackTrace();}
							outputConsole("Resolution set to "+command[1]+"x"+command[2]);
							break;
						case fullscreen:
							try{
								boolean fullscreen=PokemonProject.app.isFullscreen();
								int width,height;
								if(fullscreen){
									width=PokemonProject.WINDOW_WIDTH_INIT;
									height=PokemonProject.WINDOW_HEIGHT_INIT;}
								else{
									width=PokemonProject.getContainer().getScreenWidth();
									height=PokemonProject.getContainer().getScreenHeight();}
								
								PokemonProject.setDisplayMode(width,height,!fullscreen);}
							catch(NumberFormatException e){
								e.printStackTrace();}
							catch(SlickException e){
								e.printStackTrace();}
							outputConsole("Resolution set to "+command[1]+"x"+command[2]);
							break;
						case human:
							new EntityHuman(Integer.valueOf(command[1].trim()).intValue(),Integer.valueOf(command[2].trim()).intValue(),Sprite.getEntity(Sprite.Name.May));
							break;
						case enterroom:
							PokemonProject.roomLoader.enterRoom(PokemonProject.roomLoader.rooms.get(Integer.valueOf(command[1])));
							break;
						default:
							outputConsole("Undefined Command: "+command[0]);
							break;
					}
				}
				else outputConsole("Unknown Command: "+command[0]);
			}
			else outputConsole("> "+str);
		}
	}
	
	
	public boolean commandExists(String str){
		for (Command me : Command.values()) {
			if (me.name().equalsIgnoreCase(str))
				return true;
		}
		return false;
    }

	public String strInsert(String str,String insertStr,int index){
		return str.substring(0,index)+insertStr+str.substring(index);
	}
	
	public String strInsert(String str,char insertStr,int index){
		return str.substring(0,index)+insertStr+str.substring(index);
	}
	
	public String strExclude(String str,int indexFrom,int indexTo){
		return str.substring(0,Math.max(0,indexFrom))+str.substring(Math.min(str.length(),indexTo));
	}
	
	public void inputInsert(String str){
		input=strInsert(input,str,inputPosition);
		inputPositionMove(str.length());
	}
	
	public void inputInsert(char chr){
		input=strInsert(input,chr,inputPosition);
		inputPositionMove(1);
	}
	
	public void inputDelete(int index){
		input=strExclude(input,index,index+1);
		inputPositionSet(index);
	}
	
	public void inputPositionMove(int pos){
		inputPosition=Math.max(Math.min(inputPosition+pos,input.length()),0);
	}
	
	public void inputPositionSet(int pos){
		inputPosition=Math.max(Math.min(pos,input.length()),0);
	}
	
	public void inputReset(){
		input="";
		inputPositionSet(0);
	}
	
	public void onInput(String text){}
	public void onCommand(String[] command){}
	
	// If a string is on the system clipboard, this method returns it;
	// otherwise it returns null.
	public static String getClipboard() {
	    Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

	    try{
	    	if(t!=null&&t.isDataFlavorSupported(DataFlavor.stringFlavor)){
	        	String text = (String)t.getTransferData(DataFlavor.stringFlavor);
	            return text;
	        }
	    }
	    catch(UnsupportedFlavorException e){}
	    catch(IOException e){}
	    return null;
	}

	// This method writes a string to the system clipboard.
	// otherwise it returns null.
	public static void setClipboard(String str) {
	    StringSelection ss = new StringSelection(str);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	}
	
	@Override
	public void onKeyLeft() {}

	@Override
	public void onKeyRight() {}

	@Override
	public void onKeyUp() {}

	@Override
	public void onKeyDown() {}

	@Override
	public void onKeyAction() {}

	@Override
	public void onKeySkip() {}

	@Override
	public void onKeyEnter(){}

	@Override
	public void onKeyChat() {}

	@Override
	public void onKeyLeftPressed() {
		inputPositionMove(-1);
	}

	@Override
	public void onKeyRightPressed() {
		inputPositionMove(1);
	}

	@Override
	public void onKeyUpPressed() {}

	@Override
	public void onKeyDownPressed() {}

	@Override
	public void onKeyActionPressed() {}

	@Override
	public void onKeySkipPressed() {}

	@Override
	public void onKeyEnterPressed() {
		executeCommand(input);
		closeConsole();
		PokemonProject.player.setObj(PokemonProject.player.objPrevious);
	}

	@Override
	public void onKeyChatPressed() {}

	@Override
	public void onKeyLeftReleased() {}

	@Override
	public void onKeyRightReleased() {}

	@Override
	public void onKeyUpReleased() {}

	@Override
	public void onKeyDownReleased() {}

	@Override
	public void onKeyActionReleased() {}

	@Override
	public void onKeySkipReleased() {}

	@Override
	public void onKeyEnterReleased() {}

	@Override
	public void onKeyChatReleased() {}

	@Override
	public GameObject getControlledObject() {
		return null;
	}

	@Override
	public float getXPos() {
		
		return 0;
	}

	@Override
	public float getYPos() {
		
		return 0;
	}

	@Override
	public int getXTile() {
		
		return 0;
	}

	@Override
	public int getYTile() {
		
		return 0;}
	

	@Override
	public void onKeyPressed(int key, char chr) {
		if(isOn){
			if(keyCTRL==true){
				if(key==47)//V
					inputInsert(getClipboard().replaceAll("^[\\x20-\\xFF]"," "));
				else if(key==46){//C
					setClipboard(input);
					outputConsole("Copied to clipboard");}
				else if(key==45){//X
					setClipboard(input);
					inputReset();}
			}
			if(chr>=32&&chr<256){
				inputInsert(chr);}
			else if(key==14)//Backspace
				inputDelete(inputPosition-1);
			else if(key==211)//Delete
				inputDelete(inputPosition);
			else if(key==199)//Home
				inputPositionSet(0);
			else if(key==207)//End
				inputPositionSet(input.length());
			else if(key==29)//CTRL
				keyCTRL=true;
		}
	Debug.console.println(key+", "+chr+"="+((int)chr));
	}

	@Override
	public void onKeyReleased(int key, char chr) {
		if(key==29){//CTRL
			keyCTRL=false;
		}
	}
}

class ConsoleOutputText extends GameObject{
	private String text;
	private Date date;
	public static int fadeOutTimer=1000;
	public int fadeOutTick=0;
	public DateFormat format;
	protected static List<ConsoleOutputText> list = new ArrayList<ConsoleOutputText>();
	
	public ConsoleOutputText(String text){
		list.add(this);
		this.text=text;
	}
	
	public void onUpdate(){
		if(fadeOutTick<fadeOutTimer)
			fadeOutTick++;
	}
	
	public String getText(){
		return text;
	}
	
	public String getDate(){
		return format.format(date);
	}
	
	public void remove(){
		list.remove(this);
	}
	
}