#include "Texture.h"

Texture::Texture(SDL_Texture* texture) : texture(texture){
	SDL_QueryTexture(texture,NULL,NULL,&this->dimensions.width,&this->dimensions.height);
}

void Texture::render(SDL_Renderer* renderer){
	SDL_Rect r = {0,0,this->dimensions.width,this->dimensions.height};
	SDL_RenderCopy(renderer,this->texture,NULL,&r);
}
