#ifndef POKEMONPROJECT_EVENTHANDLEABLE_H
#define POKEMONPROJECT_EVENTHANDLEABLE_H

#include "ListHandleable.h"
#include "ListHandler.h"

union SDL_Event;

class EventHandleable : public ListHandleable{
public:
	virtual void event(SDL_Event* event) = 0;

	virtual void addToList(ListHandler* handler){
		handler->eventHandleables.push_front(this);
	}

	virtual void removeFromList(ListHandler* handler){
		handler->eventHandleables.remove(this);
	}
};

#endif
