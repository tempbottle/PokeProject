#include "GameState.h"

#include <SDL2/SDL.h>
#include <iostream>
#include "overworld/Player.h"
#include "ExitCodes.h"
#include "graphics/Texture.h"
#include "graphics/RendererSDL.h"

GameState::GameState() : State(
	SDL_CreateWindow(
		"Pokemon Project++",
		SDL_WINDOWPOS_UNDEFINED,
		SDL_WINDOWPOS_UNDEFINED,
		INITIAL_GAMEWINDOW_WIDTH,
		INITIAL_GAMEWINDOW_HEIGHT,
		SDL_WINDOW_SHOWN | SDL_WINDOW_RESIZABLE | SDL_WINDOW_OPENGL
	)),
	view(0,0){
	if(this->window==NULL){
		std::cout << "Could not create window: " << SDL_GetError() << std::endl;
		SDL_Quit();
		exit(EXIT_ERROR_SDL_WINDOW_CREATE);
	}

	//Player creation	
	(new Player(2,4))->addToList(this);
}

GameState::~GameState(){
	SDL_DestroyWindow(window);
}

void GameState::render(Renderer* r){
	r->positionTranslate(view);
		ListHandler::render(r);
		//texture->render(r);
		//r->drawTexture_tiled(texture,56,48);
	r->positionTranslate(-view);
}

void GameState::event(SDL_Event* events){
	ListHandler::event(events);

	switch(events->type){//http://wiki.libsdl.org/SDL_Event
		case SDL_KEYDOWN:
			switch(events->key.keysym.sym){
				case SDLK_ESCAPE:
					exit(0);
					break;
				case SDLK_w:
					view.y+=8;
					break;
				case SDLK_a:
					view.x+=8;
					break;
				case SDLK_s:
					view.y-=8;
					break;
				case SDLK_d:
					view.x-=8;
					break;
			}
			break;
		case SDL_QUIT:
			exit(0);
			break;
	}
}

void GameState::addToList(ListHandler* handler){
	ListHandler::addToList(handler);
}

void GameState::removeFromList(ListHandler* handler){
	ListHandler::removeFromList(handler);
}
