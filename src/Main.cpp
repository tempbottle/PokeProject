#include <iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
 
#include "ExitCodes.h"
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
	SDL_Rect textureDimensions;
	SDL_QueryTexture(texture,NULL,NULL,&textureDimensions.w,&textureDimensions.h); 

	SDL_Rect rectangle = {32,48,16,32};
	//rectangle.x=32; rectangle.y=48; rectangle.w=16; rectangle.h=32;

	SDL_Event events;

	//Main loop

	do{
		//Events and input
		SDL_PollEvent(&events);
		switch(events.type){//http://wiki.libsdl.org/SDL_Event
			case SDL_WINDOWEVENT:
				switch(events.window.event){
					case SDL_WINDOWEVENT_RESIZED:
						//initGL_viewport(events.window.data1,events.window.data2);
						break;
				}
				break;
		}

		//Update

		//Render
		SDL_SetRenderDrawColor(renderer,0,0,0,255);
		SDL_RenderClear(renderer);//Clear screen
		
		SDL_RenderCopy(renderer,texture,NULL,&textureDimensions);
		SDL_SetRenderDrawColor(renderer,128,192,255,255);
		SDL_RenderFillRect(renderer,&rectangle);

		SDL_RenderPresent(renderer);//Swap screen
		
		//Prepare for next step
		SDL_Delay(10);//TODO: FPS syncing
	}while(events.type!=SDL_QUIT);

	//Clean up
	SDL_DestroyTexture(texture);
    SDL_DestroyRenderer(renderer);
	SDL_DestroyWindow(window);
	SDL_Quit();

	return EXIT_SUCCESS; 
}
