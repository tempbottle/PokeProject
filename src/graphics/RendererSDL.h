#ifndef POKEMONPROJECT_RENDERERSDL_H
#define POKEMONPROJECT_RENDERERSDL_H

#include "geom2d/Vector.h"
#include "geom2d/Position.h"
#include "graphics/Renderer.h"

class Texture;
struct SDL_Window;
struct SDL_Renderer;

class RendererSDL : public Renderer{
private:
	Position<float> position;
	SDL_Renderer* renderer;

public:	
	RendererSDL(SDL_Window* window);
	~RendererSDL();

	void setColor(float red,float green,float blue);
	void setColor(float red,float green,float blue,float alpha);
	void setColor(unsigned char red,unsigned char green,unsigned char blue);
	void setColor(unsigned char red,unsigned char green,unsigned char blue,unsigned char alpha);
	void drawRectangle(Rectangle<unsigned int> rect);
	void drawTexture(Texture* texture);
	void drawTexture_tiled(Texture* texture,int width,int height);
	void addPosition(Vector<float> v);
	Position<float> getPosition();
	SDL_Renderer* getRenderer();
	void begin();
	void end();
};

#endif
