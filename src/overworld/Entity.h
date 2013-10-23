#ifndef POKEMONPROJECT_ENTITY_H
#define POKEMONPROJECT_ENTITY_H

#include "GameObject.h"

class Entity : public GameObject{
public:
	/**
	 * In radians
	 */
	float direction;

	float moveSpeed;
	unsigned int moveTileSpeed;
	bool isMoving;

	float renderXOffset;
	float renderYOffset;

	Entity(int x,int y);

	virtual void render(SDL_Renderer* r);
	virtual void update(int deltaTime);

	virtual void addToList(ListHandler* handler);
	virtual void removeFromList(ListHandler* handler);

	bool moveLeft(unsigned short steps);
	bool moveRight(unsigned short steps);
	bool moveUp(unsigned short steps);
	bool moveDown(unsigned short steps);

	virtual bool onMovement(int x,int y)=0;
	virtual bool onMove(int xTo,int yTo)=0;
	virtual void onMoveFinished()=0;
};

#endif
