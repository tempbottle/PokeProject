package me.EdwJes.main.rooms;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.GameObject;


public class RoomLoader {
	protected Room currentRoom;
	protected Room previousRoom;
	public List<Room> rooms = new ArrayList<Room>();
	
	public RoomLoader(){
		rooms.add(new InitRoom("Main"));
		rooms.add(new BattleRoom("TestBattle"));
		currentRoom = rooms.get(0);
	}
	
	public Room getCurrentRoom(){
		return currentRoom;
	}
	
	public Room getPreviousRoom(){
		return previousRoom;
	}
	
	public Room getRoom(String name){
		for(int i = 0; i < rooms.size(); i++){
			if(rooms.get(i).name == name) return rooms.get(i);
		}
		return currentRoom;
	}
	
	public void enterRoom(Room rm){
		previousRoom = currentRoom;
		currentRoom = rm;
		for(GameObject o: GameObject.list){
			o.roomTransist(rm);
		}
		if(!currentRoom.entered)currentRoom.init();
		else currentRoom.revisit();
	}
	
	public void enterRoom(String name){
		enterRoom(getRoom(name));
	}

}
