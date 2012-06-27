package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
	protected static List<GameObject> list = new ArrayList<GameObject>();
	
	public GameObject(){
		list.add(this);
		onCreate();}
	
	public void update(){
		onUpdate();
	}
	
	public void destroy(){
		onDestroy();
		list.remove(this);}
	
	public void onCreate(){}
	public void onUpdate(){}
	public void onDestroy(){}
}
