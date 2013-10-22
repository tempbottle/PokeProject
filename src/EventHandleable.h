#ifndef POKEMONPROJECT_EVENTHANDLEABLE_H
#define POKEMONPROJECT_EVENTHANDLEABLE_H

union SDL_Event;

class EventHandleable{
public:
	virtual void event(SDL_Event* event) = 0;
};

#endif
