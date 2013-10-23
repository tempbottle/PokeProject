#ifndef POKEMONPROJECT_RENDERABLE_H
#define POKEMONPROJECT_RENDERABLE_H

#include "ListHandleable.h"
#include "ListHandler.h"

struct SDL_Renderer;

class Renderable : public ListHandleable{
public:
	virtual void render(SDL_Renderer* r) = 0;

	static const int tileSize=16;

	virtual void addToList(ListHandler* handler){
		handler->renderables.push_front(this);
	}

	virtual void removeFromList(ListHandler* handler){
		handler->renderables.remove(this);
	}
};

#endif
