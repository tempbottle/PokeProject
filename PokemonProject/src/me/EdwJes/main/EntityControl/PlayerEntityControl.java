package me.EdwJes.main.EntityControl;

import org.newdawn.slick.Input;

import me.EdwJes.main.GameObject;
import me.EdwJes.main.PlayerInputControlObject;
import me.EdwJes.main.PokemonProject;
import me.EdwJes.main.Entities.Entity;

public class PlayerEntityControl implements PlayerInputControlObject,EntityControl{
	private Entity entity;
	
	public PlayerEntityControl(Entity entity) {
		this.entity=entity;
	}
	//TODO: L�tt-tryck f�r att v�nda sig ist�llet f�r att r�ra sig direkt n�r man har v�nt sig, dir variabeln. F�r g�ra en timer eller n�got 
	@Override
	public void onKeyLeft(){
		entity.posMoveX(-1);}
	
	@Override
	public void onKeyRight(){
		entity.posMoveX(1);}

	public void onKeyUp(){
		entity.posMoveY(-1);}
	
	@Override
	public void onKeyDown(){
		entity.posMoveY(1);}

	@Override
	public void onKeyAction() {

	}

	@Override
	public void onKeySkip() {
		
	}

	@Override
	public GameObject getControlledObject() {
		return entity;
	}

	@Override
	public void onKeyLeftPressed() {
		
	}

	@Override
	public void onKeyRightPressed() {

	}

	@Override
	public void onKeyUpPressed() {

	}

	@Override
	public void onKeyDownPressed() {

	}

	@Override
	public void onKeyActionPressed() {

	}

	@Override
	public void onKeySkipPressed() {
		entity.setPosMoveSpeed(entity.runningSpeed);
	}

	@Override
	public void onKeyLeftReleased() {
	}

	@Override
	public void onKeyRightReleased() {
	}

	@Override
	public void onKeyUpReleased() {
	}

	@Override
	public void onKeyDownReleased() {
	}

	@Override
	public void onKeyActionReleased() {

	}

	@Override
	public void onKeySkipReleased() {
		entity.setPosMoveSpeed(entity.walkingSpeed);
	}
	@Override
	public float getXPos() {
		return entity.getXPos();
	}
	@Override
	public float getYPos() {
		return entity.getYPos();
	}
	@Override
	public int getXTile() {
		return entity.getXTile();
	}
	@Override
	public int getYTile() {
		return entity.getYTile();
	}
	@Override
	public void onKeyEnter() {
		
	}
	@Override
	public void onKeyEnterPressed() {
		
		
	}
	@Override
	public void onKeyEnterReleased() {
		
	}
	@Override
	public void onKeyChat() {
	
	}
	@Override
	public void onKeyChatPressed(){
		PokemonProject.player.setObj(PokemonProject.cmd);
		PokemonProject.cmd.isOn=true;
	}
	@Override
	public void onKeyChatReleased(){
	}
	@Override
	public void onHandleInput(Input input) {
	}
	@Override
	public void onKeyPressed(int key, char chr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onKeyReleased(int key, char chr) {
		// TODO Auto-generated method stub
		
	}
}
