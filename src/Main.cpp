#include <iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <list>
 
#include "ExitCodes.h"
#include "overworld/OverworldObject.h"
#include "overworld/Player.h"
#include "State.h"
#include "ListHandler.h"
#include "graphics/Texture.h"
#include "graphics/RendererSDL.h"

#define INITIAL_GAMEWINDOW_WIDTH 480
#define INITIAL_GAMEWINDOW_HEIGHT 360

int main(int argc, char *argv[]){
	// Initialize SDL video
	if(SDL_Init(SDL_INIT_VIDEO)<0){
		std::cout<<"SDL: Could not initiate: "<<SDL_GetError()<<std::endl;
		return EXIT_ERROR_SDL_INIT;
	}
	
	SDL_Window* window = SDL_CreateWindow(
		"Pokemon Project++",
		SDL_WINDOWPOS_UNDEFINED,
		SDL_WINDOWPOS_UNDEFINED,
		INITIAL_GAMEWINDOW_WIDTH,
		INITIAL_GAMEWINDOW_HEIGHT,
		SDL_WINDOW_SHOWN | SDL_WINDOW_RESIZABLE
	);
	if(window==NULL){
		std::cout << "Could not create window: " << SDL_GetError() << std::endl;
		SDL_Quit();
		return EXIT_ERROR_SDL_WINDOW_CREATE;
	}

	if((IMG_Init(IMG_INIT_PNG) & IMG_INIT_PNG) != IMG_INIT_PNG){
		std::cout << "Could not initiate PNG loader: " << std::endl;
		return 1;
	}

	//Prepare for rendering
	RendererSDL* renderer = new RendererSDL(SDL_CreateRenderer(window,-1,SDL_RENDERER_ACCELERATED));
	if(renderer==NULL){
		std::cout << "Could not create renderer: " << SDL_GetError() << std::endl;
		SDL_Quit();
		return EXIT_ERROR_SDL_RENDERER_CREATE;
	}

	Texture* texture = new Texture(IMG_LoadTexture(renderer->renderer,"test.png"));//TODO: All textures belongs to one specific renderer and our model is incorrect because you can pass any renderer to a texture when rendering

	ListHandler* listHandler = new ListHandler();

	//Player creation	
	(new Player(2,4))->addToList(listHandler);
	
	SDL_Event events;
Vector<float> v={0,0};
	//Main loop
	do{
		//Events and input
		while(SDL_PollEvent(&events)){
			switch(events.type){//http://wiki.libsdl.org/SDL_Event
				case SDL_KEYDOWN:
					switch(events.key.keysym.sym){
						case SDLK_ESCAPE:
							goto GameLoop_End;
					}
					break;
				case SDL_QUIT:
					goto GameLoop_End;
			}

    		for(std::list<EventHandleable*>::iterator i=listHandler->eventHandleables.begin();i!=listHandler->eventHandleables.end();i++)
    			(*i)->event(&events);
		}

		//Update
		for(std::list<Updatable*>::iterator i=listHandler->updatables.begin();i!=listHandler->updatables.end();i++)
			(*i)->update(1);//TODO: For now, 1 is the temporary delta time per step

		//Render
		renderer->begin();
			texture->render(renderer);

			for(std::list<Renderable*>::iterator i=listHandler->renderables.begin();i!=listHandler->renderables.end();i++)
				(*i)->render(renderer);
		renderer->end();
		
		//Prepare for next step
		SDL_Delay(10);//TODO: FPS syncing
	}while(true);

	GameLoop_End:

	//Clean up
	SDL_DestroyTexture(texture->texture);
	delete texture;
	SDL_DestroyRenderer(renderer->renderer);
	delete renderer;
	delete listHandler;//TODO: Clean up of list handler's held objects
	SDL_DestroyWindow(window);
	SDL_Quit();

	return EXIT_SUCCESS;
}
