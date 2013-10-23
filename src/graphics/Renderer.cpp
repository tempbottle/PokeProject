#include "Renderer.h"

#include "geom2d/Position.h"

void Renderer::positionTranslate(Position<float> pos,void(*renderFunc)(Renderer*)){
	positionTranslate(pos);
		renderFunc(this);
	positionTranslate(-pos);
}
