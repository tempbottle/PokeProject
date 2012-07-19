package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

public abstract class Updater {
	public static List<Updater> list = new ArrayList<Updater>();
	protected boolean activated = true;
	
	public Updater(){
		list.add(this);
	}

	public void destroy(){
		list.remove(this);
	}
	
	//TODO: Debugging to see which objects that the garbage collector didn't remove
	@Override
	public void finalize(){
		System.out.println("Destroyed object of "+this.getClass().toString());
	}
	
	public void update(){}
	
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
}
