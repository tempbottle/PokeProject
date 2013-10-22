#ifndef POKEMONPROJECT_GAMEOBJECT_H
#define POKEMONPROJECT_GAMEOBJECT_H

#include "Renderable.h"
#include "Updatable.h"
#include <SDL2/SDL.h>

class GameObject : public Renderable, public Updatable{
public:
	SDL_Rect dim;

	GameObject(int x,int y,int w,int h);
};

#endif
