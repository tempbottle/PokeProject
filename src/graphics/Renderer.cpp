#include "Renderer.h"

#include "geom2d/Vector.h"

void Renderer::addPosition(Vector<float> v,void(*renderFunc)(Renderer*)){
	addPosition(v);
		renderFunc(this);
	addPosition(-v);
}
