#include "Texture.h"

#include <cstddef>
#include <SDL2/SDL.h>
#include "graphics/Renderer.h"

Texture::Texture(SDL_Texture* texture) : texture(texture){
	SDL_QueryTexture(texture,NULL,NULL,&this->dimensions.width,&this->dimensions.height);
}

void Texture::render(Renderer* renderer){
	renderer->drawTexture(this);
}
