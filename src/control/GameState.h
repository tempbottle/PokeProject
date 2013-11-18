#ifndef POKEMONPROJECT_GAMESTATE_H
#define POKEMONPROJECT_GAMESTATE_H

#include "control/State.h"
#include "control/ListHandler.h"
#include "geom2d/Vector.h"

class Texture;

#define INITIAL_GAMEWINDOW_WIDTH 480
#define INITIAL_GAMEWINDOW_HEIGHT 360

/*
* Game State handles the whole game
*
* Author: Lolirofle
*/
class GameState : public State,public ListHandler{
public:
	GameState();
	~GameState();

	Texture* texture=NULL;
	Vector<float> view;

	void render(Renderer* r);
	void event(SDL_Event* events);

	void addToList(ListHandler* handler);
	void removeFromList(ListHandler* handler);

	void init(){}
	void fall_back(){}
};

#endif
