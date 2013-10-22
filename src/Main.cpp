#include <iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_opengl.h>
 
#include "RendererOpenGL2.h"

#define INITIAL_GAMEWINDOW_WIDTH 640
#define INITIAL_GAMEWINDOW_HEIGHT 480

void initGL_2D(){
	glDisable(GL_DEPTH_TEST);
	glDisable(GL_LIGHTING);
	glEnable(GL_TEXTURE_2D);
	glEnable(GL_BLEND);

	glShadeModel(GL_FLAT);
	glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);

	glClearColor(0,0,0,1);
}

void initGL_viewport(unsigned int width,unsigned int height){
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0,width,height,0,1,-1);
	glMatrixMode(GL_MODELVIEW);

	glViewport(0,0,width,height);
}

int main(int argc, char *argv[]){
	// Initialize SDL video
	SDL_Init(SDL_INIT_VIDEO);
	
	SDL_Window* window = SDL_CreateWindow(
		"Pokemon Project++",
		SDL_WINDOWPOS_UNDEFINED,
		SDL_WINDOWPOS_UNDEFINED,
		INITIAL_GAMEWINDOW_WIDTH,
		INITIAL_GAMEWINDOW_HEIGHT,
		SDL_WINDOW_SHOWN|SDL_WINDOW_RESIZABLE
	);

	if(window==NULL){
		std::cout<<"Could not create window: "<<SDL_GetError()<<std::endl;
		SDL_Quit();
		return 1;
	}
	
	//Create context for rendering in window
	SDL_GLContext glContext = SDL_GL_CreateContext(window);
	
	//Prepare for rendering
	initGL_2D();
	initGL_viewport(INITIAL_GAMEWINDOW_WIDTH,INITIAL_GAMEWINDOW_HEIGHT);
 	Renderer* renderer = new RendererOpenGL2();

	SDL_Event events;

	//Main loop
	do{
		//Events and input
		SDL_PollEvent(&events);

		//Update

		//Render
		glClear(GL_COLOR_BUFFER_BIT);
	
		renderer->drawRectangle(32,48,48,60);
		renderer->render(&glContext);
		
		//Prepare for next step
		SDL_GL_SwapWindow(window); //Swap the window/buffer to display result.
		SDL_Delay(10);//TODO: FPS syncing
	}while(events.type!=SDL_QUIT);

	//Clean up
	SDL_GL_DeleteContext(glContext); 	
	SDL_DestroyWindow(window);
	SDL_Quit();

	return 0; 
}
