package me.EdwJes.main.overworld.entitycontrol;

import me.EdwJes.main.overworld.entities.Entity;

public class FollowerEntityControl extends EntityControl{
	public Entity follow;
	
	public FollowerEntityControl(Entity entity,Entity follow){
		super(entity);
		follow=this.follow;
	}
	
	@Override
	public void destroy(){
		super.destroy();
		follow=null;
	} 
	
}
