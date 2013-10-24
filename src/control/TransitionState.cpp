#include "TransitionState.h"
#include <SDL2/SDL.h>

TransitionState::TransitionState(State* old_st, State* new_st, SDL_Window* wndw, Animation anim_1, Animation anim_2) : State(wndw), state_1(old_st), state_2(new_st), animation_1(anim_1), animation_2(anim_2){}
	
void TransitionState::init() {
	animation_time = animation_1.time;
	upwards = true;
	elapsed_time = 0;
}

void TransitionState::fall_back() {
	animation_time = animation_2.time;
	upwards = false;
	elapsed_time = 0;
}

void TransitionState::update(int dt) {
	elapsed_time += dt;
	if (elapsed_time > animation_time) {
		if (upwards)
			requestPush(state_2);
		else
			requestPeel();
	}
}

void TransitionState::render(Renderer* r) {
	if(upwards)
		animation_1.render(r);
	else
		animation_2.render(r);
}

void transitionFromState(State* st_1, State* st_2, Animation animation_1, Animation animation_2){
	st_1->requestPush(new TransitionState(st_1, st_2, st_1->window, animation_1, animation_2));
}
