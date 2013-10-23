#ifndef POKEMONPROJECT_STATE_H
#define POKEMONPROJECT_STATE_H

#include <SDL2/SDL.h>
#include "Renderable.h"
#include "Updatable.h"

/*
* State.h | Jesper Fridefors | jesper.fridefors@gmail.com
* States are handled via a stack. A state transition can happen in two ways: either a new state
* is pushed on the stack or a stack is popped off and you fall back on a previous state.
* Author: Jesper Fridefors
*
*/
class State : public Renderable{
public:
	SDL_Window* window;
	bool requestsToBePushed;
	bool requestsToBePeeled;
	State* stateToBePushed;
	int fade_in_time;
	int fade_out_time;

	State(SDL_Window* window);
	void requestPush(State* st);
	void requestPeel();
	
	virtual void init()      = 0; //whenever this state is created and pushed on the stack.
	virtual void fall_back() = 0; //whenever this state is falled back upon.
};

#endif
