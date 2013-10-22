#ifndef POKEMONPROJECT_PLAYER_H
#define POKEMONPROJECT_PLAYER_H

#include "GameObject.h"
#include "EventHandleable.h"

class Player : public GameObject, public EventHandleable{
private:
	struct{
		char left:1;
		char right:1;
		char up:1;
		char down:1;
	} keyDown;

public:
	Player(int x,int y) : GameObject(x,y,16,16){
		this->keyDown.left=false;
		this->keyDown.right=false;
		this->keyDown.up=false;
		this->keyDown.down=false;
	}

	void render(SDL_Renderer* r);
	void update(int deltaTime);
	void event(SDL_Event* event);
};

#endif
