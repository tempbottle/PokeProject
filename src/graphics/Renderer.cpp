#include "Renderer.h"

#include "geom2d/Vector.h"

void Renderer::positionTranslate(Vector<float> v,void(*renderFunc)(Renderer*)){
	positionTranslate(v);
		renderFunc(this);
	positionTranslate(-v);
}
