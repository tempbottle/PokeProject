#ifndef POKEMONPROJECT_RENDERABLE_H
#define POKEMONPROJECT_RENDERABLE_H

#include "ListHandleable.h"

class Renderer;
class ListHandler;

class Renderable : public ListHandleable{
public:
	virtual void render(Renderer* r) = 0;

	virtual void addToList(ListHandler* handler);
	virtual void removeFromList(ListHandler* handler);
};

#endif
