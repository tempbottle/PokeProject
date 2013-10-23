#ifndef POKEMONPROJECT_TEXTURE_H
#define POKEMONPROJECT_TEXTURE_H

#include "geom2d/Rectangle.h"
#include "Renderable.h"

struct SDL_Texture;

class Texture : public Renderable{
public:
	SDL_Texture* texture;
	Rectangle<int> dimensions;

	Texture(SDL_Texture* texture);
	virtual ~Texture(){}

	void render(Renderer* renderer);
};

#endif
