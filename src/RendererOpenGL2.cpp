#include "RendererOpenGL2.h"

#include <SDL2/SDL.h>
#include <SDL2/SDL_opengl.h>

RendererOpenGL2::RendererOpenGL2(){}
RendererOpenGL2::~RendererOpenGL2(){}

void RendererOpenGL2::drawRectangle(int x1,int y1,int x2,int y2){
	glRecti(x1,y1,x2,y2);
}

void RendererOpenGL2::setColor(float red,float blue,float green){
	glColor3f(red,green,blue);
}

void RendererOpenGL2::setColor(float red,float blue,float green,float alpha){
	glColor4f(red,green,blue,alpha);
}

void RendererOpenGL2::setColor(unsigned char red,unsigned char blue,unsigned char green){
	glColor3ub(red,green,blue);
}

void RendererOpenGL2::setColor(unsigned char red,unsigned char blue,unsigned char green,unsigned char alpha){
	glColor4ub(red,green,blue,alpha);
}

void RendererOpenGL2::render(SDL_GLContext* glContext){}
