#include <iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <list>
 
#include "ExitCodes.h"
#include "overworld/OverworldObject.h"
#include "control/State.h"
#include "graphics/Renderer.h"
#include "graphics/RendererGL.h"
#include "graphics/RendererSDL.h"
#include "control/GameState.h"
#include "graphics/Texture.h"

#define INITIAL_GAMEWINDOW_WIDTH 480
#define INITIAL_GAMEWINDOW_HEIGHT 360

int main(int argc, char *argv[]){
	// Initialize SDL video
	if(SDL_Init(SDL_INIT_VIDEO)<0){
		std::cerr<<"SDL: Could not initiate: "<<SDL_GetError()<<std::endl;
		return EXIT_ERROR_SDL_INIT;
	}
	
	GameState* game = new GameState();

	if((IMG_Init(IMG_INIT_PNG) & IMG_INIT_PNG) != IMG_INIT_PNG){
		std::cerr << "Could not initiate PNG loader: " << std::endl;
		return 1;
	}

	//Prepare for rendering
	Renderer* renderer = new RendererSDL(game->window);

	//game->texture = new Texture(IMG_LoadTexture(sdlRenderer,"test.png"));//TODO: All textures belongs to one specific renderer and our model is incorrect because you can pass any renderer to a texture when rendering;

	SDL_Event events;

	//Main loop
	do{
		//Events and input
		while(SDL_PollEvent(&events))
			game->event(&events);

		//Update
		game->update(1);//TODO: For now, 1 is the temporary delta time per step

		//Render
		renderer->begin();
			game->render(renderer);
		renderer->end();

		//Prepare for next step
		SDL_Delay(10);//TODO: FPS syncing
	}while(true);

	//Clean up
	SDL_DestroyTexture(game->texture->texture);
	delete game->texture;
	delete renderer;
	delete game;//TODO: Clean up of list handler's held objects
	SDL_Quit();

	return EXIT_SUCCESS;
}
