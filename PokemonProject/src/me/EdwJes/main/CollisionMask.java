package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

public class CollisionMask {
	private int x=0,y=0,xCenter,yCenter;
	private List<int[]> tiles=new ArrayList<int[]>();
	private List<Integer> tempTileIndex=new ArrayList<Integer>();
	
	public CollisionMask(int xTile1,int yTile1,int xTile2,int yTile2){
		addRect(xTile1,yTile1,xTile2,yTile2);}
	
	public CollisionMask(int xTile,int yTile){
		addPoint(xTile,yTile);}
	
	public CollisionMask(CollisionMask mask){
		this.tiles=mask.tiles;}
	
	public CollisionMask(){}
	
	public void addRect(int x1,int y1,int x2,int y2){
		for(int ix=x1;ix<=x2;ix++)
			for(int iy=y1;iy<=y2;iy++)
				tiles.add(new int[]{ix,iy});}
	
	public void addPoint(int x,int y){
		tiles.add(new int[]{x,y});}
	
	public void addTempPoint(int x,int y){
		tiles.add(new int[]{x,y});
		tempTileIndex.add(tiles.size());}
	
	public int getX(){
		return x;}
	
	public int getY(){
		return y;}
	
	public void setX(int x){
		if(this.x!=x){
			onMove();
			this.x=x;}}
	
	public void setY(int y){
		if(this.y!=y){
			onMove();
			this.y=y;}}
	
	public void setLocation(int x,int y){
		setX(x);
		setY(y);}
	
	public void onMove(){
		clearTempTiles();
	}
	
	public void clearTempTiles(){
		if(tempTileIndex.size()>0){
			for(Integer tmpIndex:tempTileIndex)
				tiles.remove(tmpIndex);
			tempTileIndex.clear();}
	}
	
	public int getMinX(){
		int i=tiles.get(0)[0];
		for(int[] point:tiles)
			i=Math.min(point[0],i);
		return i+x;}
	
	public int getMinY(){
		int i=tiles.get(0)[1];
		for(int[] point:tiles)
			i=Math.min(point[1],i);
		return i+y;}
	
	public int getMaxX(){
		int i=tiles.get(0)[0];
		for(int[] point:tiles)
			i=Math.max(point[0],i);
		return i+x;}
	
	public int getMaxY(){
		int i=tiles.get(0)[1];
		for(int[] point:tiles)
			i=Math.max(point[1],i);
		return i+y;}
	
	public List<int[]> getPoints(){
		return tiles;}
	
	public int getPointCount(){
		return tiles.size();}
	
	public int[] getPoint(int index){
		return tiles.get(index);}
	
	public int indexOf(int x,int y){
		return tiles.indexOf(new int[]{x,y});}
	
	public int indexOfLast(int x,int y){
		return tiles.lastIndexOf(new int[]{x,y});}
	
	public void removePoint(int index){
		tiles.remove(index);}
	
	public void clearPoints(int index){
		tiles.clear();}
	
	public boolean hasVertex(int x,int y){
		return indexOf(x-this.x,y-this.y)==-1?false:true;}
	
	public boolean includes(int x,int y){
		return indexOf(x,y)==-1?false:true;}
	
	public boolean intersects(CollisionMask mask){
		if(getMaxX()>=mask.getMinX()&&getMinX()<=mask.getMaxX()&&getMaxY()>=mask.getMinY()&&getMinY()<=mask.getMaxY())
			return true;
		else
			return false;
	}
}
