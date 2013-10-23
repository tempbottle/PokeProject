#include "Player.h"
#include <SDL2/SDL.h>
#include <iostream>
#include "geom2d/Rectangle.h"
#include "Renderable.h"

Player::Player(int x,int y) : GameObject(x,y,(Rectangle){1,1}),faceDirection(0.0f),moveDirection(0.0f),moveSpeed(1.0f),moveTileSpeed(1),isMoving(false),renderXOffset(0.0f),renderYOffset(0.0f){
	this->keyDown.left=false;
	this->keyDown.right=false;
	this->keyDown.up=false;
	this->keyDown.down=false;
}

void Player::render(SDL_Renderer* renderer){
	SDL_SetRenderDrawColor(renderer,128,192,255,255);
	SDL_Rect rect = {
		this->x*Renderable::tileSize + (int)this->renderXOffset,
		this->y*Renderable::tileSize + (int)this->renderYOffset,
		(int)this->collisionBox.width * Renderable::tileSize,
		(int)this->collisionBox.height * Renderable::tileSize};
	SDL_RenderFillRect(renderer,&rect);
}

void Player::update(int deltaTime){
	if(this->isMoving){
		if(renderXOffset>moveTileSpeed)
			renderXOffset-=moveTileSpeed;
		else if(-renderXOffset>moveTileSpeed)
			renderXOffset+=moveTileSpeed;
		else
			renderXOffset=0.0f;
		

		if(renderYOffset>moveTileSpeed)
			renderYOffset-=moveTileSpeed;
		else if(-renderYOffset>moveTileSpeed)
			renderYOffset+=moveTileSpeed;
		else
			renderYOffset=0.0f;
		
		if(renderXOffset==0.0f && renderYOffset==0.0f)
			this->isMoving=false;
	}else{
		if(this->keyDown.left){
			this->x-=moveTileSpeed;
			this->isMoving=true;
			renderXOffset=Renderable::tileSize;
		}
		if(this->keyDown.right){
			this->x+=moveTileSpeed;
			this->isMoving=true;
			renderXOffset=-Renderable::tileSize;
		}
		if(this->keyDown.up){
			this->y-=moveTileSpeed;
			this->isMoving=true;
			renderYOffset=Renderable::tileSize;
		}
		if(this->keyDown.down){
			this->y+=moveTileSpeed;
			this->isMoving=true;
			renderYOffset=-Renderable::tileSize;
		}
	}
}

void Player::event(SDL_Event* event){
	switch(event->type){//http://wiki.libsdl.org/SDL_Event
		case SDL_KEYDOWN:
			switch(event->key.keysym.sym){
				case SDLK_LEFT:
					this->keyDown.left=true;
					break;
				case SDLK_RIGHT:
					this->keyDown.right=true;
					break;
				case SDLK_UP:
					this->keyDown.up=true;
					break;
				case SDLK_DOWN:
					this->keyDown.down=true;
					break;
			}
			break;
		case SDL_KEYUP:
			switch(event->key.keysym.sym){
				case SDLK_LEFT:
					this->keyDown.left=false;
					break;
				case SDLK_RIGHT:
					this->keyDown.right=false;
					break;
				case SDLK_UP:
					this->keyDown.up=false;
					break;
				case SDLK_DOWN:
					this->keyDown.down=false;
					break;
			}
			break;
	}
}
