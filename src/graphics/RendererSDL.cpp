#include "RendererSDL.h"

#include <SDL2/SDL.h>
#include "graphics/Texture.h"

RendererSDL::RendererSDL(SDL_Renderer* renderer) : renderer(renderer),position((Position<float>){0.0f,0.0f}){}

void RendererSDL::setColor(float red,float blue,float green){
	SDL_SetRenderDrawColor(renderer,(unsigned char)(red*255),(unsigned char)(blue*255),(unsigned char)(green*255),255);
}

void RendererSDL::setColor(float red,float blue,float green,float alpha){
	SDL_SetRenderDrawColor(renderer,(unsigned char)(red*255),(unsigned char)(blue*255),(unsigned char)(green*255),(unsigned char)(alpha*255));
}

void RendererSDL::setColor(unsigned char red,unsigned char green,unsigned char blue){
	SDL_SetRenderDrawColor(renderer,red,green,blue,255);
}

void RendererSDL::setColor(unsigned char red,unsigned char green,unsigned char blue,unsigned char alpha){
	SDL_SetRenderDrawColor(renderer,red,green,blue,alpha);
}

void RendererSDL::drawRectangle(Rectangle<unsigned int> rect){
	SDL_Rect r = {(int)this->position.x,(int)this->position.y,(int)rect.width,(int)rect.height};
	SDL_RenderFillRect(renderer,&r);
}

void RendererSDL::drawTexture(Texture* texture){
	SDL_Rect r = {(int)this->position.x,(int)this->position.y,texture->dimensions.width,texture->dimensions.height};
	SDL_RenderCopy(this->renderer,texture->texture,NULL,&r);
}

void RendererSDL::positionTranslate(Vector<float> v){
	this->position=this->position+v;
}

void RendererSDL::begin(){
	this->position=(Position<float>){0.0f,0.0f};
	SDL_SetRenderDrawColor(renderer,0,0,0,255);//Clear color
	SDL_RenderClear(renderer);//Clear screen
}

void RendererSDL::end(){
	SDL_RenderPresent(renderer);//Swap screen
}
