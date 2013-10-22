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
	Player(int x,int y);

	void render(SDL_Renderer* r);
	void update(int deltaTime);
	void event(SDL_Event* event);
};

#endif
