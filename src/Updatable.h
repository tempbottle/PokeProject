#ifndef POKEMONPROJECT_UPDATABLE_H
#define POKEMONPROJECT_UPDATABLE_H

#include "ListHandleable.h"
#include "ListHandler.h"

class Updatable : public ListHandleable{
public:
	virtual void update(int deltaTime) = 0;

	virtual void addToList(ListHandler* handler){
		handler->updatables.push_front(this);
	}

	virtual void removeFromList(ListHandler* handler){
		handler->updatables.remove(this);
	}
};

#endif
