package me.EdwJes.main.rooms;

import me.EdwJes.main.GameObject;


public class RoomLoader {
	protected Room currentRoom;
	protected Room previousRoom;
	public Room[] room = new Room[256];
	
	public RoomLoader(){
		room[0] = new InitRoom("Main");
		room[1] = new BattleRoom("TestBattle");
		currentRoom = room[0];
	}
	
	public Room getCurrentRoom(){
		return currentRoom;
	}
	
	public Room getPreviousRoom(){
		return previousRoom;
	}
	
	public Room getRoom(String name){
		for(int i = 0; i < room.length; i++){
			if(room[i].name == name) return room[i];
		}
		return currentRoom;
	}
	
	public void enterRoom(Room rm){
		previousRoom = currentRoom;
		currentRoom = rm;
		for(GameObject o: GameObject.list){
			o.roomTransist(rm);
		}
		currentRoom.init();
	}
	
	public void enterRoom(String name){
		enterRoom(getRoom(name));
	}

}
