#ifndef POKEMONPROJECT_LISTHANDLER_H
#define POKEMONPROJECT_LISTHANDLER_H

#include <list>
#include "traits/Renderable.h"
#include "traits/Updatable.h"
#include "traits/EventHandleable.h"

class EventHandleable;

/**
 * Contains lists of objects (ListHandleables) that should be held categorized in different ways
 *
 * Author: Lolirofle
 */
class ListHandler : public Renderable,public Updatable,public EventHandleable{
public:
	std::list<Updatable*> updatables;
	std::list<Renderable*> renderables;
	std::list<EventHandleable*> eventHandleables;

	void render(Renderer* r);
	void event(SDL_Event* event);
	void update(int deltaTime);

	void addToList(ListHandler* handler);
	void removeFromList(ListHandler* handler);

	ListHandler(){};
	virtual ~ListHandler(){};
};

#endif
