#ifndef POKEMONPROJECT_LISTHANDLEABLE_H
#define POKEMONPROJECT_LISTHANDLEABLE_H

class ListHandler;

/**
 * List Handleable defines a type of object that is handled and held by list handlers
 *
 * Author: Lolirofle
 */
class ListHandleable{
public:
	/**
	 * Adds this object to a list handler
	 */
	virtual void addToList(ListHandler* handler) = 0;

	/**
	 * Removes this object from a list handler
	 */
	virtual void removeFromList(ListHandler* handler) = 0;
};

#endif
