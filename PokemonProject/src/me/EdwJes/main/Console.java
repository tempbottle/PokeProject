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

import me.EdwJes.main.Entities.EntityHuman;
import me.EdwJes.main.config.Config;
import me.EdwJes.main.config.Config.Key;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Console extends RenderableObject implements PlayerInputControlObject{
	
	private boolean isOn = false,keyCTRL=false;
	private String input = "",inputFieldPrefix="> ";
	public char commandPrefix='/';
	private int inputPosition=0;
	private int MAX_ARGUMENTS=32;
	public View view;
	/*TODO:Output lines in console and limit text to boundaries of the window + black transparent box under console 
	 * private int outputLines = 0;
	 * private int outputMaxLines = 6;*/
	
	public static enum Command{
		helpold,
		help,
		test(new Arg[]{new Arg("arg",Arg.Type.STRING,Arg.Flag.INFINITE)}),
		shit(new Arg[]{new Arg("roomId",Arg.Type.INTEGER)}),
		scale(new Arg[]{new Arg("scale",Arg.Type.DOUBLE)}),
		skit,
		display(new Arg[]{new Arg("width",Arg.Type.INTEGER),new Arg("height",Arg.Type.INTEGER)}),
		human(new Arg[]{new Arg("x",Arg.Type.INTEGER),new Arg("y",Arg.Type.INTEGER)}),
		fullscreen(new Arg[]{new Arg("fullscreen",Arg.Type.BOOLEAN,Arg.Flag.OPTIONAL)}),
		room(new Arg[]{new Arg("roomId",Arg.Type.INTEGER)});
		private Arg[] args;
		Command(Arg[] args){
			this.args=args;}
		Command(){
			this.args=new Arg[]{};}
		public Arg[] getArgs(){
			return args;}
		public Arg getArg(int index){
			return args[index];}
	}
	
	public Console(){
		setLayer(LAYER_GUI);
		setPersistency(true);
	}
	//TODO: Custom input and output stream classes for server communication in the future
	@Override public void render(Graphics g,View view){
		int i=0,lineHeight=g.getFont().getLineHeight();
		for(ConsoleOutputText text:ConsoleOutputText.list){
			i++;
			int alpha=255;
			if(text.fadeOutTick<ConsoleOutputText.fadeOutTimer||isOn){
				int y=(int)view.viewHeight - lineHeight*2-6 - ((ConsoleOutputText.list.size()-i)*16);
				if(!isOn)
					alpha=255-((int)(((float)text.fadeOutTick/ConsoleOutputText.fadeOutTimer)*255));
				g.setColor(new Color(255,255,255,alpha));
				g.drawString(text.getText(), 6+view.getDrawScreenX(), y+view.getDrawScreenY());
				g.setColor(Color.white);}
		}
		if(isOn){
			g.setColor(Color.darkGray);
			g.fillRect(0-view.getDrawScreenX(),view.viewHeight-lineHeight-4-view.getDrawScreenY(),view.viewWidth-view.getDrawScreenX(),view.viewHeight-view.getDrawScreenY());
			g.setColor(Color.white);
			g.drawString(inputFieldPrefix + input, 6-view.getDrawScreenX(), view.viewHeight-lineHeight-2-view.getDrawScreenY());
			int inputPositionWidth=g.getFont().getWidth(inputFieldPrefix + input.substring(0,inputPosition))+2;
			g.setColor(Color.red);
			g.drawLine(inputPositionWidth+2-view.getDrawScreenX(), view.viewHeight-lineHeight-view.getDrawScreenY(),inputPositionWidth+2-view.getDrawScreenX(), view.viewHeight-4-view.getDrawScreenY());
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
	
	public boolean isOn(){
		return isOn;
	}
	
	public void executeCommand(String str){//TODO:Check argument count and type
		if(str!=""&&!str.matches("([ ]*)")){
			onInput(str);
			if(str.charAt(0)==commandPrefix){
				str=str.substring(1);
				String[] input=str.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)",MAX_ARGUMENTS);
				int argumentCount=input.length-1;
				input[0]=input[0].toLowerCase();
				onCommand(input);
				if(commandExists(input[0])){
					Command command=Command.valueOf(Command.class,input[0]);
					
					boolean isInfinite=false;
					int countedArg=0,countedRequiredArg=0,isWrongType=0;
					ARGLOOP: for(Arg arg:command.getArgs()){
						countedArg++;
						if(argumentCount>=countedArg){
							if(arg.getType()==Arg.Type.INTEGER){
								try{Integer.parseInt(input[countedArg]);}
								catch(NumberFormatException e){isWrongType=countedArg;break;}}
							else if(arg.getType()==Arg.Type.DOUBLE){
								try{Double.parseDouble(input[countedArg]);}
								catch(NumberFormatException e){isWrongType=countedArg;break;}}
							else if(arg.getType()==Arg.Type.BOOLEAN){
								switch(input[countedArg]){
									case"on":
									case"true":
									case"1":
										input[countedArg]="true";
										break;
									case"off":
									case"false":
									case"0":
										input[countedArg]="true";
										break;
									default:	
										isWrongType=countedArg;
										break ARGLOOP;}
							}
						}
						if (arg.getFlag()==Arg.Flag.NONE){
							countedRequiredArg++;}
						else if(arg.getFlag()==Arg.Flag.INFINITE){
							isInfinite=true;
							break ARGLOOP;}
					}
					if(isWrongType!=0){
						outputConsole("Wrong type on argument "+isWrongType);outputConsole("Syntax: "+commandGetSyntax(command));}
					else if((argumentCount<=countedArg&&argumentCount>=countedRequiredArg)||(isInfinite&&argumentCount>=countedRequiredArg)){
						executeRawCommand(command,input,argumentCount);
					}
					else if(argumentCount>countedArg){outputConsole("Too many arguments, expecting"+countedRequiredArg);outputConsole("Syntax: "+commandGetSyntax(command));}
					else{outputConsole("Too few arguments, expecting "+countedRequiredArg);outputConsole("Syntax: "+commandGetSyntax(command));}
				}
				else outputConsole("Unknown Command: "+input[0]);
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
	
	public String commandGetSyntax(Command command){
		String str=command.name()+"(";
		for(Arg arg:command.getArgs()){
			if(arg.getFlag()==Arg.Flag.OPTIONAL)
				str+="<"+arg.getType().toStringShort()+" "+arg.getName()+">, ";
			else if(arg.getFlag()==Arg.Flag.INFINITE)
				str+="<"+arg.getType().toStringShort()+" "+arg.getName()+" ... ";
			else 
				str+="["+arg.getType().toStringShort()+" "+arg.getName()+"], ";
		}
		return str+")";
    }

	public void executeRawCommand(Command command,String[] arg,int argumentCount){

		switch(command){
			case helpold:
				String __str="";
				for(Command cmd:Command.values()){
					__str+=cmd.toString()+", ";
				}
				outputConsole(__str);
				break;
			case help:
				for(Command cmd:Command.values()){
					outputConsole(commandGetSyntax(cmd));
				}
				break;
			case test:
				String _str="";
				for(int i=0;i<arg.length;i++)
					_str+="["+i+"]="+arg[i]+"; ";
				outputConsole(_str);
				break;
			case shit:
				outputConsole("Yes shit");
				break;
			case scale:
				view.setScale(Float.valueOf(arg[1].trim()).floatValue(),Float.valueOf(arg[1].trim()).floatValue());
				outputConsole("Scaled the screen to "+arg[1]+"x");
				break;
			case display:
				try{
					PokemonProject.setDisplayMode(Integer.valueOf(arg[1].trim()).intValue(),Integer.valueOf(arg[2].trim()).intValue(),PokemonProject.app.isFullscreen());}
				catch(NumberFormatException e){
					e.printStackTrace();}
				catch(SlickException e){
					e.printStackTrace();}
				outputConsole("Resolution set to "+arg[1]+"x"+arg[2]);
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
				outputConsole("Resolution set to "+arg[1]+"x"+arg[2]);
				break;
			case human:
				new EntityHuman(Integer.valueOf(arg[1].trim()).intValue(),Integer.valueOf(arg[2].trim()).intValue(),Sprite.getEntity(Sprite.Name.May));
				break;
			case room:
				PokemonProject.roomLoader.enterRoom(PokemonProject.roomLoader.rooms.get(Integer.valueOf(arg[1])));
				break;
			default:
				outputConsole("Undefined Command: "+arg[0]);
				break;
		}
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
	public void onKeyPressed(int key, char chr,int playerId,Config config) {
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
				inputInsert(chr);
				input=input.replace("(!M)","MEGAFANTASTICMEGALUSPECTACULARYESYESYES");}
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
			else if(key==config.player.get(playerId).keyMap.get(Key.LEFT)){
				inputPositionMove(-1);}
			else if(key==config.player.get(playerId).keyMap.get(Key.RIGHT)){
				inputPositionMove(1);}
			else if(key==config.player.get(playerId).keyMap.get(Key.ENTER)){
				executeCommand(input);
				closeConsole();
				PlayerInput player=PlayerInput.getPlayerInput(playerId);
				player.setObj(player.objPrevious);}
		}
	//Debug.console.println(key+", "+chr+"="+((int)chr));
	}

	@Override
	public void onKeyReleased(int key, char chr,int playerId,Config config) {
		if(key==29){//CTRL
			keyCTRL=false;
		}
	}

	@Override
	public void handleInput(Input input, int playerId,Config config) {

	}
}

class ConsoleOutputText extends Updater{
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
	
	@Override
	public void update(){
		super.update();
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

class Arg{
	static enum Type{
		INTEGER("int"),
		STRING("str"),
		DOUBLE("double"),
		BOOLEAN("bool");
		private String shortName;
		Type(String shortName){
			this.shortName=shortName;}
		public String toStringShort(){
			return shortName;}
		}
	static enum Flag{
		NONE,
		OPTIONAL,
		INFINITE;}
	private String name;
	private Type type;
	private Flag flag;
	Arg(String name,Type type,Flag flag){
		this.name=name;
		this.type=type;
		this.flag=flag;}
	Arg(String name,Type type){
		this.name=name;
		this.type=type;
		this.flag=Flag.NONE;}
	public Type getType(){
		return type;}
	public Flag getFlag(){
		return flag;}
	public String getName(){
		return name;}
}