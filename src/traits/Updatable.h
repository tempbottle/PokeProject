#ifndef POKEMONPROJECT_UPDATABLE_H
#define POKEMONPROJECT_UPDATABLE_H

#include "traits/ListHandleable.h"

class ListHandler;

class Updatable : public ListHandleable{
public:
	virtual void update(int deltaTime) = 0;

	virtual void addToList(ListHandler* handler);
	virtual void removeFromList(ListHandler* handler);
};

#endif
