package me.EdwJes.main.Entities;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private List<int[]> path=new ArrayList<int[]>();
	
	public Path(int beginX,int beginY){
		addPoint(beginX,beginY);
	}
	
	public void setPoint(int index,int x,int y){
		path.set(index,new int[]{x,y});}
	
	public void addPoint(int x,int y){
		path.add(new int[]{x,y});}
	
	public int[] getPoint(int index){
		return path.get(index);}
	
	public int getIndexOfPoint(int x,int y){
		return path.indexOf(new int[]{x,y});}
	
	public int getLastIndexOfPoint(int x,int y){
		return path.lastIndexOf(new int[]{x,y});}
}
