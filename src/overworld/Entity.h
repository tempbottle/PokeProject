#ifndef POKEMONPROJECT_ENTITY_H
#define POKEMONPROJECT_ENTITY_H

#include "OverworldObject.h"

/**
 * Constants defining directions in radians
 */
namespace EntityDirection{
	extern const float LEFT;
	extern const float RIGHT;
	extern const float UP;
	extern const float DOWN;
}

/**
 * Entities are overworld objects that are "living".
 * Organic lives, which can move around.
 *
 * Author: Lolirofle
 */
class Entity : public OverworldObject{
public:
	/**
	 * In radians
	 */
	float direction;

	/**
	 * Movement speed 
	 */
	float moveSpeed;

	unsigned int moveTileSpeed;

	/**
	 * Indicates if the entity is currently moving
	 */
	bool isMoving;

	/**
	 * Offset position for rendering.
	 * Used in movement
	 */
	float renderXOffset;
	float renderYOffset;

	Entity(int x,int y,float moveSpeed=1.0f);

	virtual void render(Renderer* r);
	virtual void update(int deltaTime);

	virtual void addToList(ListHandler* handler);
	virtual void removeFromList(ListHandler* handler);

	bool moveLeft(unsigned short steps);
	bool moveRight(unsigned short steps);
	bool moveUp(unsigned short steps);
	bool moveDown(unsigned short steps);

	/**
	 * Called when the entity has succeded moving one tile
	 */
	virtual bool onMovement(int x,int y)=0;

	/**
	 * Called when the entity has began to move
	 */
	virtual bool onMove(int xTo,int yTo)=0;

	/**
	 * Called when the entity has reached its destination when moving
	 */
	virtual void onMoveFinished()=0;
};

#endif
