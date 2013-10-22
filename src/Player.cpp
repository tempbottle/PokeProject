#include "Player.h"
#include <SDL2/SDL.h>

#include "geom2d/Rectangle.h"

Player::Player(int x,int y) : GameObject(x,y,(Rectangle){16,16}){
	this->keyDown.left=false;
	this->keyDown.right=false;
	this->keyDown.up=false;
	this->keyDown.down=false;
}

void Player::render(SDL_Renderer* renderer){
	SDL_SetRenderDrawColor(renderer,128,192,255,255);
	SDL_Rect rect = {this->x,this->y,(int)this->collisionBox.width,(int)this->collisionBox.height};
	SDL_RenderFillRect(renderer,&rect);
}

void Player::update(int deltaTime){
	if(this->keyDown.left)
		this->x-=2;
	if(this->keyDown.right)
		this->x+=2;
	if(this->keyDown.up)
		this->y-=2;
	if(this->keyDown.down)
		this->y+=2;
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