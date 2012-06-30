package me.EdwJes.main.rooms;

public class BattleRoom extends Room {

	public BattleRoom(String name){
		super(name);
	}
	
	@Override public void init(){
		System.out.println("Entered Room");
	}
}
