#include "EventHandleable.h"

#include "control/ListHandler.h"

void EventHandleable::addToList(ListHandler* handler){
	handler->eventHandleables.push_front(this);
}

void EventHandleable::removeFromList(ListHandler* handler){
	handler->eventHandleables.remove(this);
}
