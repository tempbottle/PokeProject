#include <iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
 
#include "ExitCodes.h"

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

	SDL_Rect rectangle = {.x=32,.y=48,.w=16,.h=32};
	struct{
		char left:1;
		char right:1;
		char up:1;
		char down:1;
	} keyDown={false,false,false,false};

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
						case SDLK_LEFT:
							keyDown.left=true;
							break;
						case SDLK_RIGHT:
							keyDown.right=true;
							break;
						case SDLK_UP:
							keyDown.up=true;
							break;
						case SDLK_DOWN:
							keyDown.down=true;
							break;
						case SDLK_ESCAPE:
							goto GameLoop_End;
							break;
					}
					break;
				case SDL_KEYUP:
					switch(events.key.keysym.sym){
						case SDLK_LEFT:
							keyDown.left=false;
							break;
						case SDLK_RIGHT:
							keyDown.right=false;
							break;
						case SDLK_UP:
							keyDown.up=false;
							break;
						case SDLK_DOWN:
							keyDown.down=false;
							break;
					}
					break;
			}
		}
		if(keyDown.left)
			rectangle.x-=2;
		if(keyDown.right)
			rectangle.x+=2;
		if(keyDown.up)
			rectangle.y-=2;
		if(keyDown.down)
			rectangle.y+=2;

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

	GameLoop_End:

	//Clean up
	SDL_DestroyTexture(texture);
    SDL_DestroyRenderer(renderer);
	SDL_DestroyWindow(window);
	SDL_Quit();

	return EXIT_SUCCESS;
}
