#include <iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <list>
 
#include "ExitCodes.h"
#include "GameObject.h"
#include "Player.h"
#include "State.h"

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

	//Prepare for rendering
	SDL_Renderer* renderer = SDL_CreateRenderer(window,-1,SDL_RENDERER_ACCELERATED);
	if(renderer==NULL){
		std::cout << "Could not create renderer: " << SDL_GetError() << std::endl;
		SDL_Quit();
		return EXIT_ERROR_SDL_RENDERER_CREATE;
	}

	SDL_Texture* texture = IMG_LoadTexture(renderer,"test.png");
	SDL_Rect textureDimensions={0,0,0,0};
	SDL_QueryTexture(texture,NULL,NULL,&textureDimensions.w,&textureDimensions.h); 

	std::list<Updatable*> updatables;
	std::list<Renderable*> renderables;
	std::list<EventHandleable*> eventHandleables;

	{//Player creation	
		Player* player = new Player(32,64);
		updatables.push_front(player);
		renderables.push_front(player);
		eventHandleables.push_front(player);
	}

	SDL_Event events;

	//Main loop
	do{
		//Events and input
		while(SDL_PollEvent(&events)){
			switch(events.type){//http://wiki.libsdl.org/SDL_Event
				case SDL_WINDOWEVENT:
					switch(events.window.event){
						case SDL_WINDOWEVENT_RESIZED:
							//initGL_viewport(events.window.data1,events.window.data2);
							break;
					}
					break;
				case SDL_KEYDOWN:
					switch(events.key.keysym.sym){
						case SDLK_ESCAPE:
							goto GameLoop_End;
					}
					break;
				case SDL_QUIT:
					goto GameLoop_End;
			}

    		for(std::list<EventHandleable*>::iterator i=eventHandleables.begin();i!=eventHandleables.end();i++)
    			(*i)->event(&events);
		}

		//Update
		for(std::list<Updatable*>::iterator i=updatables.begin();i!=updatables.end();i++)
			(*i)->update(0);//TODO: For now, 0 is the temporary delta time per step

		//Render
		SDL_SetRenderDrawColor(renderer,0,0,0,255);//Clear color
		SDL_RenderClear(renderer);//Clear screen	
			SDL_RenderCopy(renderer,texture,NULL,&textureDimensions);

			for(std::list<Renderable*>::iterator i=renderables.begin();i!=renderables.end();i++)
				(*i)->render(renderer);
		SDL_RenderPresent(renderer);//Swap screen
		
		//Prepare for next step
		SDL_Delay(10);//TODO: FPS syncing
	}while(true);

	GameLoop_End:

	//Clean up
	SDL_DestroyTexture(texture);
    SDL_DestroyRenderer(renderer);
	SDL_DestroyWindow(window);
	SDL_Quit();

	return EXIT_SUCCESS;
}
