#ifndef POKEMONPROJECT_TEXTURE_H
#define POKEMONPROJECT_TEXTURE_H

#include "geom2d/Rectangle.h"
#include "Renderable.h"
#include <SDL2/SDL.h>

struct SDL_Texture;

class Texture : public Renderable{
public:
	Rectangle<int> dimensions;
	SDL_Texture* texture;

	Texture(SDL_Texture* texture);
	~Texture();

	void render(SDL_Renderer* renderer);
};

#endif
