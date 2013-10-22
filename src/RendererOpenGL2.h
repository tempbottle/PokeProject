#ifndef POKEMONPROJECT_RENDEREROPENGL2_H
#define POKEMONPROJECT_RENDEREROPENGL2_H

#include <SDL2/SDL.h>
#include <SDL2/SDL_opengl.h>

#include "Renderer.h"

class RendererOpenGL2 : public Renderer{
public:
	RendererOpenGL2();
	~RendererOpenGL2();

	void drawRectangle(int x1,int y1,int x2,int y2);
	void setColor(float red,float blue,float green);
	void setColor(float red,float blue,float green,float alpha);
	void setColor(unsigned char red,unsigned char blue,unsigned char green);
	void setColor(unsigned char red,unsigned char blue,unsigned char green,unsigned char alpha);
	void drawRectangle(int x,int y,unsigned int w,unsigned int h);

	void render(SDL_GLContext* glContext);
};

#endif
