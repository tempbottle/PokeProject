package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;
import me.EdwJes.main.rooms.Room;

public class GameObject {
	public static List<GameObject> list = new ArrayList<GameObject>();
	protected boolean activated = true;
	Room homeRoom;
	private boolean persistent = false;
	
	public GameObject(){
		homeRoom = PokemonProject.roomLoader.getCurrentRoom();
		list.add(this);
	}
	
	
	public void update(){

	}
	
	public void destroy(){
		list.remove(this);
	}
	
	public void activate(){
		activated = true;
	}
	
	public void deactivate(){
		activated = false;
	}
	
	public boolean isActivated(){
		return activated;
	}
	
	public void switchActivate(){
		activated = !activated;
	}
	
	public void roomTransist(Room newRoom){
		//is called whenever a room is entered. Activate object if the new room is the homeRoom
		if(!isPersistent()) activated = (homeRoom == newRoom);
	}
	
	public void callAlarm(Alarm alarm){}


	public boolean isPersistent() {
		return persistent;
	}


	public void setPersistency(boolean persistent) {
		this.persistent = persistent;
	}
}
