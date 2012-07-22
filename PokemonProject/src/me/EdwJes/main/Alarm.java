package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

public class Alarm extends Updater {
	/*
	 * Alarm, timer.
	 * Currently, if any superclass to the class that makes an alarm
	 * got a alarm with the same number, both alarm functions will be called.
	 * make unique alarm ID's and reserve space.
	 */
	
	static List<Alarm> list = new ArrayList<Alarm>();
	
	private int ticks = 0,maxTicks,loop=0;
	private Updater host;
	
	public Alarm(int maxTicks, Updater host){
		super();
		list.add(this);
		this.maxTicks = maxTicks;
		this.host = host;
	}
	
	public Alarm(int maxTicks,int loop,Updater host){
		super();
		list.add(this);
		this.maxTicks = maxTicks;
		this.host = host;
		this.loop = loop;
	}
	
	@Override public void update(){
		if(ticks == maxTicks){
			host.callAlarm(this);
			if(loop<=0)
				destroy();
			else{
				loop--;
				this.ticks=0;
			}
		}
		else if(ticks < maxTicks) ticks++;
		
		
	}
	
	@Override public void destroy(){
		super.destroy();
		list.remove(this);
	}
	
	public Alarm loop(){
		return new Alarm(maxTicks, host);
	}

}
