#include <SDL2/SDL.h>

class Renderer{
public:
	virtual void drawRectangle(int x1,int y1,int x2,int y2) = 0;

	virtual void render(SDL_GLContext* glContext) = 0;
};