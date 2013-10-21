#include <SDL2/SDL.h>
#include <SDL2/SDL_opengl.h>

#include "Renderer.h"

class RendererOpenGL2 : public Renderer{
public:
	RendererOpenGL2();
	~RendererOpenGL2();

	void drawRectangle(int x1,int y1,int x2,int y2);

	void render(SDL_GLContext* glContext);
};