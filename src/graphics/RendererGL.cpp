#include "RendererGL.h"

#include "graphics/Texture.h"
#include <SDL2/SDL_opengl.h>
#include <SDL2/SDL.h>

RendererGL::RendererGL(SDL_Window* window) : context(SDL_GL_CreateContext(window)),window(window){
	glDisable(GL_DEPTH_TEST);
	glDisable(GL_LIGHTING);
	glEnable(GL_TEXTURE_2D);
	glEnable(GL_BLEND);

	glShadeModel(GL_FLAT);
	glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);

	glClearColor(0,0,0,1);

	int w,h;SDL_GetWindowSize(window,&w,&h);
	this->initView(w,h);
}

void RendererGL::initView(unsigned int width,unsigned int height){
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0,width,height,0,1,-1);
	glMatrixMode(GL_MODELVIEW);

	glViewport(0,0,width,height);
}

void RendererGL::setColor(float red,float green,float blue){
	glColor3f(red,blue,green);
}

void RendererGL::setColor(float red,float green,float blue,float alpha){
	glColor4f(red,blue,green,alpha);
}

void RendererGL::setColor(unsigned char red,unsigned char green,unsigned char blue){
	glColor3ub(red,green,blue);
}

void RendererGL::setColor(unsigned char red,unsigned char green,unsigned char blue,unsigned char alpha){
	glColor4ub(red,green,blue,alpha);
}

void RendererGL::drawRectangle(Rectangle<unsigned int> rect){
	glRecti(0,0,rect.width,rect.height);
}

void RendererGL::drawTexture(Texture* texture){
	
}

void RendererGL::drawTexture_tiled(Texture* texture,int width,int height){

}

void RendererGL::positionTranslate(Vector<float> v){
	glTranslatef(v.x,v.y,0.0f);
}

void RendererGL::begin(){
	SDL_GL_MakeCurrent(this->window,this->context);
	glClear(GL_COLOR_BUFFER_BIT);
	glPushMatrix();
}

void RendererGL::end(){
	glPopMatrix();
	SDL_GL_SwapWindow(this->window);
}
