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

	State(SDL_Window* window) : window(window), stateToBePushed(NULL){
		requestsToBePushed = requestsToBePeeled = false;
	};
	
	void requestPush(State* st){
		requestsToBePushed = true;
		stateToBePushed = st;
	}
	
	void requestPeel(){
		requestsToBePeeled = true;
	}
	
	virtual void init()      = 0; //whenever this state is created and pushed on the stack.
	virtual void fall_back() = 0; //whenever this state is falled back upon.
	virtual void fade_in()   = 0;
	virtual void fade_out()  = 0;
};

class TransitionState : public State, public Updatable{
public:
	State* st_1;
	State* st_2;

	bool upward_or_down;
	bool fade;
	//This tells what 'direction' the state transitions. 'true' means a new state is pushed on the stack, 
	//and this is always the case in initiation; otherwise a new TransitionState wouldn't be created, it would be a fallback
	// true : st_1 -> st_2
	// false: st_2 -> st_1
	//Current render function which serves as the render function.
	int animation_time;
	int elapsed_time;

	TransitionState(State* old_st, State* new_st, SDL_Window* wndw) : State(wndw), st_1(old_st), st_2(new_st){}
	
	void init() {
		animation_time = st_1->fade_out_time;
		upward_or_down = true;
		elapsed_time   = 0;
		fade = true;
	}
	
	void fallback() {
		animation_time = st_2->fade_out_time;
		upward_or_down = false;
		elapsed_time = 0;
		fade = true;
	}

	void update(int dt) {
		elapsed_time += dt;
		if (elapsed_time > animation_time) {
			elapsed_time = 0;
			fade = false;
			if(upward_or_down) {
				animation_time = st_2->fade_in_time;
				if(!fade)
					requestPush(st_2);
			}
			else {
				animation_time = st_1->fade_in_time;
				if(!fade)
					requestPeel();
			}
		}
	}
	

	void render(SDL_Renderer* r) {
		if(upward_or_down && fade) //fades out from lower state
			st_1->fade_out();
		if(upward_or_down && !fade)//fades in higher state
			st_2->fade_in();
		if(!upward_or_down && fade)//fades out higher state
			st_2->fade_out();
		else                       //fades in lower state
			st_1->fade_in();       //this all happens in order
	}
};

#endif
