#ifndef POKEMONPROJECT_OVERWORLDOBJECT_H
#define POKEMONPROJECT_OVERWORLDOBJECT_H

#include "traits/Renderable.h"
#include "traits/Updatable.h"
#include "geom2d/Rectangle.h"

/**
 * Overworld objects are everything that exists in the overworld.
 * They have a position and a collision box.
 *
 * Author: Lolirofle
 */
class OverworldObject : public Renderable, public Updatable{
public:
	int x,y;
	Rectangle<unsigned int> collisionBox;

	static const unsigned int tileSize=16;

	OverworldObject(int x,int y,Rectangle<unsigned int> collisionBox);
};

#endif
