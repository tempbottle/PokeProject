#include "ListHandler.h"

void ListHandler::render(Renderer* r){
	for(std::list<Renderable*>::iterator i=this->renderables.begin();i!=this->renderables.end();i++)
		(*i)->render(r);
}

void ListHandler::update(int deltaTime){
	for(std::list<Updatable*>::iterator i=this->updatables.begin();i!=this->updatables.end();i++)
		(*i)->update(deltaTime);
}

void ListHandler::event(SDL_Event* event){
	for(std::list<EventHandleable*>::iterator i=this->eventHandleables.begin();i!=this->eventHandleables.end();i++)
		(*i)->event(event);
}

void ListHandler::addToList(ListHandler* handler){
	Updatable::addToList(handler);
	Renderable::addToList(handler);
	EventHandleable::addToList(handler);
}

void ListHandler::removeFromList(ListHandler* handler){
	Updatable::removeFromList(handler);
	Renderable::removeFromList(handler);
	EventHandleable::removeFromList(handler);
}