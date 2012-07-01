package me.EdwJes.main.rooms;

public class Room {
	
	String name;
	public boolean initiated;
	public boolean entered = false;
	
	public Room(String name){
		this.name = name;
		initiated=false;
	}
	
	public void init(){
		initiated=true;
		entered = true;
	}
	
	public void revisit(){
	}
	
	

}
