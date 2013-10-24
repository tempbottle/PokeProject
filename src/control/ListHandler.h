#ifndef POKEMONPROJECT_LISTHANDLER_H
#define POKEMONPROJECT_LISTHANDLER_H

#include <list>

class Updatable;
class Renderable;
class EventHandleable;

/**
 * Contains lists of objects that should be held categorized in different ways
 *
 * Author: Lolirofle
 */
class ListHandler{
public:
	std::list<Updatable*> updatables;
	std::list<Renderable*> renderables;
	std::list<EventHandleable*> eventHandleables;

	ListHandler(){};
	~ListHandler(){};
};

#endif
