package me.EdwJes.main.EntityControl;

import me.EdwJes.main.Entities.Entity;

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
