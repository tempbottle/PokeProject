#ifndef POKEMONPROJECT_RENDERABLECONTAINER_H
#define POKEMONPROJECT_RENDERABLECONTAINER_H

#include "traits/Renderable.h"
#include "geom2d/Position.h"
#include <list>

/**
 * Used when there is a list of renderables that should follow the position of the container
 *
 * Author: Lolirofle
 */
class RenderableContainer : public Renderable{
public:
	virtual void render(Renderer* r);
	virtual std::list<Renderable*>* getRenderableList()=0;
	virtual Position<float> getPosition()=0;
};

#endif
