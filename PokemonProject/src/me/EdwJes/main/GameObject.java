package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;
import me.EdwJes.main.rooms.Room;

public class GameObject extends Updater{
	public static List<GameObject> list = new ArrayList<GameObject>();
	Room homeRoom;
	private boolean persistent = false;
	
	public GameObject(){
		list.add(this);
		homeRoom = PokemonProject.roomLoader.getCurrentRoom();
	}
	
	@Override
	public void update(){
		super.update();
	}
	
	@Override
	public void destroy(){
		super.destroy();
		list.remove(this);
	}
	
	public void roomTransist(Room newRoom){
		//is called whenever a room is entered. Activate object if the new room is the homeRoom
		if(!isPersistent()) activated = (homeRoom == newRoom);
	}
	
	public Room getRoom(){
		return homeRoom;}
	
	public boolean isPersistent() {
		return persistent;
	}


	public void setPersistency(boolean persistent) {
		this.persistent = persistent;
	}
	
	public void callAlarm(Alarm alarm){}
	
	public static List<GameObject> getObjectInRoom(Room room){
		List<GameObject> _list=new ArrayList<GameObject>();
		for(GameObject obj:list)
			if(obj.homeRoom==room)
				_list.add(obj);
		return _list;
	}
}
