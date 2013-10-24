#ifndef POKEMONPROJECT_TRANSITIONSTATE_H
#define POKEMONPROJECT_TRANSITIONSTATE_H
#include "State.h"

typedef struct {
	void (*render)(Renderer*);
	int time;
} Animation;

class TransitionState : public State, public Updatable{
public:
	State* state_1;
	State* state_2;
	
	TransitionState(State* old_st, State* new_st, SDL_Window* wndw, Animation anim_1, Animation anim_2);
	
	void init();
	void fall_back();
	void update(int dt);
	void render(Renderer* r);
private:
	int elapsed_time;
	int animation_time;
	bool upwards;
	Animation animation_1;
	Animation animation_2;
	//This tells what 'direction' the state transitions. 'true' means a new state is pushed on the stack. 
	//False means this state is fell back upon.
	// true : st_1 -> st_2
	// false: st_2 -> st_1
	
};

void transitionFromState(State* st_1, State* st_2, Animation animation_1, Animation animation_2);

#endif
