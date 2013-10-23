#ifndef POKEMONPROJECT_RENDERABLE_H
#define POKEMONPROJECT_RENDERABLE_H

#include "ListHandleable.h"

struct SDL_Renderer;
class ListHandler;

class Renderable : public ListHandleable{
public:
	virtual void render(SDL_Renderer* r) = 0;

	static const int tileSize=16;

	virtual void addToList(ListHandler* handler);
	virtual void removeFromList(ListHandler* handler);
};

#endif
