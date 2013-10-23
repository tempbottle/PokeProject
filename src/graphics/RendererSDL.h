#ifndef POKEMONPROJECT_RENDERERSDL_H
#define POKEMONPROJECT_RENDERERSDL_H

#include "geom2d/Vector.h"
#include "geom2d/Position.h"
#include "graphics/Renderer.h"

class Texture;
struct SDL_Renderer;

class RendererSDL : public Renderer{
public:
	SDL_Renderer* renderer;
	Position<float> position;
	
	RendererSDL(SDL_Renderer* renderer);

	void setColor(float red,float blue,float green);
	void setColor(float red,float blue,float green,float alpha);
	void setColor(unsigned char red,unsigned char blue,unsigned char green);
	void setColor(unsigned char red,unsigned char blue,unsigned char green,unsigned char alpha);
	void drawRectangle(Rectangle<unsigned int> rect);
	void drawTexture(Texture* texture);
	void positionTranslate(Vector<float> v);
	void begin();
	void end();
};

#endif
