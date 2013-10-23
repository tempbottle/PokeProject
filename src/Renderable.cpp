#include "Renderable.h"

#include "control/ListHandler.h"

void Renderable::addToList(ListHandler* handler){
	handler->renderables.push_front(this);
}

void Renderable::removeFromList(ListHandler* handler){
	handler->renderables.remove(this);
}
