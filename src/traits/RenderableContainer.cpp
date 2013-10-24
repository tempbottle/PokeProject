#include "RenderableContainer.h"

#include "graphics/Renderer.h"
#include "geom2d/Vector.h"

void RenderableContainer::render(Renderer* r){
	Position<float> pos = this->getPosition();
	r->positionTranslate(Vector<float>(pos.x,pos.y));
	for(std::list<Renderable*>::iterator i=this->getRenderableList()->begin();i!=this->getRenderableList()->end();i++)
		(*i)->render(r);
	r->positionTranslate(Vector<float>(-pos.x,-pos.y));
}
