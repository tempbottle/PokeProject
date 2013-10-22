#ifndef POKEMONPROJECT_RENDERER_H
#define POKEMONPROJECT_RENDERER_H

#include <SDL2/SDL.h>

class Renderer{
public:
	virtual void setColor(float red,float blue,float green) = 0;
	virtual void setColor(float red,float blue,float green,float alpha) = 0;
	virtual void setColor(unsigned char red,unsigned char blue,unsigned char green) = 0;
	virtual void setColor(unsigned char red,unsigned char blue,unsigned char green,unsigned char alpha) = 0;
	virtual void drawRectangle(int x1,int y1,int x2,int y2) = 0;
	virtual void drawRectangle(int x,int y,unsigned int w,unsigned int h) = 0;

	virtual void render(SDL_GLContext* glContext) = 0;
};

#endif
