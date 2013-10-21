#include <iostream>
#include "SDL2/SDL.h"

int main(int argc,char const *argv[]){
	SDL_Init(SDL_INIT_VIDEO); // Initialize SDL2
	
	SDL_Window* window = SDL_CreateWindow(
		"Title",
		SDL_WINDOWPOS_UNDEFINED,
		SDL_WINDOWPOS_UNDEFINED,
		640,
		480,
		SDL_WINDOW_SHOWN
	);
	
	if(window==NULL){
		std::cout<<"Could not create window: "<<SDL_GetError()<<std::endl;
		SDL_Quit();
		return 1;
	}
	
	SDL_Delay(3000); // Wait for 3000 milliseconds, for example
	
	SDL_DestroyWindow(window);
	SDL_Quit();

	return 0; 
}