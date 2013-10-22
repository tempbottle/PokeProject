#include <iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_opengl.h>
#include <SDL2/SDL_image.h>
 
#include "RendererOpenGL2.h"
#include "ExitCodes.h"

#define INITIAL_GAMEWINDOW_WIDTH 480
#define INITIAL_GAMEWINDOW_HEIGHT 360

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
		SDL_WINDOW_SHOWN | SDL_WINDOW_OPENGL | SDL_WINDOW_RESIZABLE
	);

	if(window==NULL){
		std::cout << "Could not create window: " << SDL_GetError() << std::endl;
		SDL_Quit();
		return EXIT_ERROR_SDL_WINDOW_CREATE;
	}
	
	//Create context for rendering in window
	SDL_GLContext glContext = SDL_GL_CreateContext(window);
	
	//Prepare for rendering
	initGL_2D();
	initGL_viewport(INITIAL_GAMEWINDOW_WIDTH,INITIAL_GAMEWINDOW_HEIGHT);
 	Renderer* renderer = new RendererOpenGL2();

 	#define IMAGE_FILENAME "test.png"
 	SDL_Surface* image = IMG_Load(IMAGE_FILENAME);
	if(image==NULL){
		std::cout << "SDL Image: Could not load image \"" << IMAGE_FILENAME << "\": " << IMG_GetError() << std::endl;
		SDL_Quit();
		return EXIT_ERROR_SDL_IMAGE_LOAD;
	}

	SDL_Event events;

	//Main loop
	do{
		//Events and input
		SDL_PollEvent(&events);
		switch(events.type){//http://wiki.libsdl.org/SDL_Event
			case SDL_WINDOWEVENT:
				switch(events.window.event){
					case SDL_WINDOWEVENT_RESIZED:
						initGL_viewport(events.window.data1,events.window.data2);
						break;
				}
				break;
		}

		//Update

		//Render
		glClear(GL_COLOR_BUFFER_BIT);

		renderer->setColor(0.5f,1.0f,0.75f);
		renderer->drawRectangle(32,48,48,60);
		SDL_BlitSurface(image,0,SDL_GetWindowSurface(window),0);
		renderer->render(&glContext);
		
		//Prepare for next step
		SDL_GL_SwapWindow(window); //Swap the window/buffer to display result.
		SDL_Delay(10);//TODO: FPS syncing
	}while(events.type!=SDL_QUIT);

	//Clean up
	SDL_GL_DeleteContext(glContext); 	
	SDL_DestroyWindow(window);
	SDL_Quit();

	return EXIT_SUCCESS; 
}
