#include "Player.h"

#include <SDL2/SDL.h>

Player::Player(int x,int y) : Entity(x,y,0.5f){}

void Player::event(SDL_Event* event){
	switch(event->type){//http://wiki.libsdl.org/SDL_Event
		case SDL_KEYDOWN:
			switch(event->key.keysym.sym){
				case SDLK_LEFT:
					this->keyDown.left=true;
					this->moveLeft(1);
					break;
				case SDLK_RIGHT:
					this->keyDown.right=true;
					this->moveRight(1);
					break;
				case SDLK_UP:
					this->keyDown.up=true;
					this->moveUp(1);
					break;
				case SDLK_DOWN:
					this->keyDown.down=true;
					this->moveDown(1);
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

void Player::render(Renderer* r){
	Entity::render(r);
}

void Player::addToList(ListHandler* handler){
	Entity::addToList(handler);
	EventHandleable::addToList(handler);
}

void Player::removeFromList(ListHandler* handler){
	Entity::removeFromList(handler);
	EventHandleable::removeFromList(handler);
}

bool Player::onMovement(int x,int y){return true;}
bool Player::onMove(int xTo,int yTo){return true;}
void Player::onMoveFinished(){
	if(this->keyDown.left)
		this->moveLeft(1);
	else if(this->keyDown.right)
		this->moveRight(1);
	else if(this->keyDown.up)
		this->moveUp(1);
	else if(this->keyDown.down)
		this->moveDown(1);
}
