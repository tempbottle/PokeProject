package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

public class Updater {
	public static List<Updater> list = new ArrayList<Updater>();
	protected boolean activated = true;
	
	public Updater(){
		list.add(this);
	}

	public void destroy(){
		list.remove(this);
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
