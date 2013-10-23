#ifndef POKEMONPROJECT_RENDERABLE_H
#define POKEMONPROJECT_RENDERABLE_H

struct SDL_Renderer;

class Renderable{
public:
	virtual void render(SDL_Renderer* r) = 0;

	static const int tileSize=16;
};

#endif
