#ifndef POKEMONPROJECT_RENDERER_H
#define POKEMONPROJECT_RENDERER_H

#include "geom2d/Vector.h"
#include "geom2d/Rectangle.h"

class Texture;

class Renderer{
public:
	virtual ~Renderer(){}

	virtual void setColor(float red,float blue,float green) = 0;
	virtual void setColor(float red,float blue,float green,float alpha) = 0;
	virtual void setColor(unsigned char red,unsigned char green,unsigned char blue) = 0;
	virtual void setColor(unsigned char red,unsigned char green,unsigned char blue,unsigned char alpha) = 0;
	virtual void drawRectangle(Rectangle<unsigned int> rect) = 0;
	virtual void drawTexture(Texture* texture) = 0;
	virtual void drawTexture_tiled(Texture* texture,int width,int height) = 0;
	virtual void positionTranslate(Vector<float> v) = 0;
	void positionTranslate(Vector<float> v,void(*renderFunc)(Renderer*));

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
