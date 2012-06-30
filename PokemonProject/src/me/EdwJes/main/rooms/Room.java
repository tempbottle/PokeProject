package me.EdwJes.main.rooms;

public class Room {
	
	String name;
	public boolean entered = false;
	
	public Room(String name){
		this.name = name;
	}
	
	public void init(){
		entered = true;
	}
	
	public void revisit(){
		
	}
	
	

}
