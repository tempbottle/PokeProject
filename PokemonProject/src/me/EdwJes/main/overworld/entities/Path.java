package me.EdwJes.main.overworld.entities;

import java.util.ArrayList;
import java.util.List;

public class Path {//TODO: Some kind of A* search path system too
	private List<int[]> path=new ArrayList<int[]>();
	private boolean reverse=false;
	private Mode mode=Mode.TURNREVERSE;
	private int pathPosIndex=0;
	
	public enum Mode{
		LOOP,
		STOP,
		TURNREVERSE;
	}

	public Path(int beginX,int beginY){
		addPoint(beginX,beginY);
	}
	
	public void setPoint(int index,int x,int y){
		path.set(index,new int[]{x,y});}
	
	public void addPoint(int x,int y){
		path.add(new int[]{x,y});}
	
	public int[] getPoint(int index){
		return path.get(index);}
	
	public int countPoints(){
		return path.size();}
	
	public int getIndexOfPoint(int x,int y){
		return path.indexOf(new int[]{x,y});}
	
	public int getLastIndexOfPoint(int x,int y){
		return path.lastIndexOf(new int[]{x,y});}
	
	public int[] addPathIndex(int pos){
		if(mode!=Mode.STOP){
			pathPosIndex=(pathPosIndex+pos*(reverse?-1:1))%countPoints();
		}
		return getPoint(pathPosIndex);
	}
}
