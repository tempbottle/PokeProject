#include "Updatable.h"

#include "ListHandler.h"

void Updatable::addToList(ListHandler* handler){
	handler->updatables.push_front(this);
}

void Updatable::removeFromList(ListHandler* handler){
	handler->updatables.remove(this);
}
