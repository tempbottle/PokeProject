#ifndef POKEMONPROJECT_PLAYER_H
#define POKEMONPROJECT_PLAYER_H

#include "Entity.h"
#include "traits/EventHandleable.h"

/**
 * Players have some kind of input handler on top of their Entity ancestor
 *
 * Author: Lolirofle
 */
class Player : public Entity, public EventHandleable{
public:
	Player(int x,int y);

	void event(SDL_Event* event);
	void render(Renderer* r);

	virtual void addToList(ListHandler* handler);
	virtual void removeFromList(ListHandler* handler);

	bool onMovement(int x,int y);
	bool onMove(int xTo,int yTo);
	void onMoveFinished();
private:
	struct{
		char left:1;
		char right:1;
		char up:1;
		char down:1;
	} keyDown;
};

#endif
