#ifndef POKEMONPROJECT_RENDERERGL_H
#define POKEMONPROJECT_RENDERERGL_H

#include "geom2d/Vector.h"
#include "geom2d/Position.h"
#include "graphics/Renderer.h"
#include <SDL2/SDL.h>

class Texture;

class RendererGL : public Renderer{
public:
	SDL_GLContext context;
	SDL_Window* window;

	RendererGL(SDL_Window* window);
	void initView(unsigned int width,unsigned int height);

	void setColor(float red,float green,float blue);
	void setColor(float red,float green,float blue,float alpha);
	void setColor(unsigned char red,unsigned char green,unsigned char blue);
	void setColor(unsigned char red,unsigned char green,unsigned char blue,unsigned char alpha);
	void drawRectangle(Rectangle<unsigned int> rect);
	void drawTexture(Texture* texture);
	void drawTexture_tiled(Texture* texture,int width,int height);
	void positionTranslate(Vector<float> v);
	void begin();
	void end();
};

#endif
