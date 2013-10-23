#ifndef POKEMONPROJECT_EVENTHANDLEABLE_H
#define POKEMONPROJECT_EVENTHANDLEABLE_H

#include "control/ListHandleable.h"

union SDL_Event;
class ListHandler;

class EventHandleable : public ListHandleable{
public:
	virtual void event(SDL_Event* event) = 0;

	virtual void addToList(ListHandler* handler);
	virtual void removeFromList(ListHandler* handler);
};

#endif
