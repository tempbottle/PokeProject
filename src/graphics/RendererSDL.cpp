#include "RendererSDL.h"

#include <SDL2/SDL.h>
#include "graphics/Texture.h"
#include <iostream>
#include "ExitCodes.h"

RendererSDL::RendererSDL(SDL_Window* window) : renderer(SDL_CreateRenderer(window,-1,SDL_RENDERER_ACCELERATED)),position((Position<float>){0.0f,0.0f}){
	if(this->renderer==NULL){
		std::cerr << "Could not create renderer: " << SDL_GetError() << std::endl;
		SDL_Quit();
		exit(EXIT_ERROR_SDL_RENDERER_CREATE);
	}
}

RendererSDL::~RendererSDL(){
	SDL_DestroyRenderer(this->renderer);
}

void RendererSDL::setColor(float red,float green,float blue){
	SDL_SetRenderDrawColor(renderer,(unsigned char)(red*255),(unsigned char)(blue*255),(unsigned char)(green*255),255);
}

void RendererSDL::setColor(float red,float green,float blue,float alpha){
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

void RendererSDL::drawTexture_tiled(Texture* texture,int width,int height){//TODO: If we care about performance, then this function may be worth looking at
	SDL_Rect dest = {(int)this->position.x,(int)this->position.y,texture->dimensions.width,texture->dimensions.height};
	SDL_Rect src = {0,0,texture->dimensions.width,texture->dimensions.height};

	for(const int w=this->position.x+width;dest.x<w;dest.x+=texture->dimensions.width){
		dest.y=(int)this->position.y;
		if(dest.x+texture->dimensions.width>w)
			dest.w = src.w = dest.x+texture->dimensions.width-w;
		for(const int h=this->position.y+height;dest.y<h;dest.y+=texture->dimensions.height){
			if(dest.y+texture->dimensions.height>h)
				dest.w = src.h = dest.y+texture->dimensions.height-h;
			SDL_RenderCopy(this->renderer,texture->texture,&src,&dest);
		}
	}
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
