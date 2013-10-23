
#include "State.h"
#include <SDL2/SDL.h>

State::State(SDL_Window* window) : window(window), stateToBePushed(NULL){
	requestsToBePushed = requestsToBePeeled = false;
}

void State::requestPush(State* st){
	requestsToBePushed = true;
	stateToBePushed = st;
}

void State::requestPeel(){
	requestsToBePeeled = true;
}

State* State::getStateToBePushed(){
	return stateToBePushed;
}