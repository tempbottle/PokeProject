#ifndef POKEMONPROJECT_GAMEOBJECT_H
#define POKEMONPROJECT_GAMEOBJECT_H

#include "Renderable.h"
#include "Updatable.h"
#include "geom2d/Rectangle.h"

class GameObject : public Renderable, public Updatable{
public:
	int x,y;
	Rectangle collisionBox;

	GameObject(int x,int y,Rectangle collisionBox);
};

#endif
