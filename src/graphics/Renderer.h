#ifndef POKEMONPROJECT_RENDERER_H
#define POKEMONPROJECT_RENDERER_H

#include "geom2d/Position.h"

class Texture;
struct Rectangle;

class Renderer{
public:
	virtual void setColor(float red,float blue,float green) = 0;
	virtual void setColor(float red,float blue,float green,float alpha) = 0;
	virtual void setColor(unsigned char red,unsigned char blue,unsigned char green) = 0;
	virtual void setColor(unsigned char red,unsigned char blue,unsigned char green,unsigned char alpha) = 0;
	virtual void drawRectangle(Rectangle* rect) = 0;
	virtual void drawTexture(Texture* rect) = 0;
	virtual void positionTranslate(Position<float> pos) = 0;
	virtual void positionTranslate(Position<float> pos,void(*renderFunc)(Renderer*));

	/**
	 * Begin rendering session
	 * Should be called before rendering each step
	 */
	virtual void begin() = 0;

	/**
	 * End rendering session
	 * Should be called after rendering each step
	 */
	virtual void end() = 0;
};

#endif
