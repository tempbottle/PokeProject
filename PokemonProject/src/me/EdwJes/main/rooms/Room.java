package me.EdwJes.main.rooms;

public class Room {
	
	String name;
	public boolean initiated;
	
	public Room(String name){
		this.name = name;
		initiated=false;
	}
	
	public void init(){
		initiated=true;
	}
	
	

}
