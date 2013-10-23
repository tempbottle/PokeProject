#ifndef POKEMONPROJECT_LISTHANDLEABLE_H
#define POKEMONPROJECT_LISTHANDLEABLE_H

class ListHandler;

class ListHandleable{
public:
	virtual void addToList(ListHandler* handler) = 0;
	virtual void removeFromList(ListHandler* handler) = 0;
};

#endif
