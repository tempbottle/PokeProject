#include "Entity.h"

#include <SDL2/SDL.h>

#include "geom2d/Rectangle.h"
#include "traits/Renderable.h"

Entity::Entity(int x,int y) : OverworldObject(x,y,(Rectangle<unsigned int>){1,1}),direction(0.0f),moveSpeed(1.0f),moveTileSpeed(1),isMoving(false),renderXOffset(0.0f),renderYOffset(0.0f){}

void Entity::render(SDL_Renderer* renderer){
	SDL_SetRenderDrawColor(renderer,128,192,255,255);
	SDL_Rect rect = {
		this->x*(signed)OverworldObject::tileSize + (int)this->renderXOffset,
		this->y*(signed)OverworldObject::tileSize + (int)this->renderYOffset,
		(signed)this->collisionBox.width * (signed)OverworldObject::tileSize,
		(signed)this->collisionBox.height * (signed)OverworldObject::tileSize};
	SDL_RenderFillRect(renderer,&rect);
}

void Entity::update(int deltaTime){
	if(this->isMoving){
		if(renderXOffset>moveSpeed)
			renderXOffset-=moveSpeed;
		else if(-renderXOffset>moveSpeed)
			renderXOffset+=moveSpeed;
		else
			renderXOffset=0.0f;
		

		if(renderYOffset>moveSpeed)
			renderYOffset-=moveSpeed;
		else if(-renderYOffset>moveSpeed)
			renderYOffset+=moveSpeed;
		else
			renderYOffset=0.0f;
		
		if(renderXOffset==0.0f && renderYOffset==0.0f){
			this->isMoving=false;
			this->onMoveFinished();
		}
	}
}

bool Entity::moveLeft(unsigned short steps){
	if(this->isMoving || !this->onMove(this->x-(signed)moveTileSpeed,this->y))
		return false;
	this->x-=(signed)moveTileSpeed;
	this->isMoving=true;
	this->direction=EntityDirection::LEFT;
	renderXOffset=(signed)OverworldObject::tileSize;
	return true;
}

bool Entity::moveRight(unsigned short steps){
	if(this->isMoving || !this->onMove(this->x+(signed)moveTileSpeed,this->y))
		return false;
	this->x+=(signed)moveTileSpeed;
	this->isMoving=true;
	this->direction=EntityDirection::RIGHT;
	renderXOffset=-(signed)OverworldObject::tileSize;
	return true;
}

bool Entity::moveUp(unsigned short steps){
	if(this->isMoving || !this->onMove(this->x,this->y-(signed)moveTileSpeed))
		return false;
	this->y-=(signed)moveTileSpeed;
	this->isMoving=true;
	this->direction=EntityDirection::UP;
	renderYOffset=(signed)OverworldObject::tileSize;
	return true;
}

bool Entity::moveDown(unsigned short steps){
	if(this->isMoving || !this->onMove(this->x,this->y+(signed)moveTileSpeed))
		return false;
	this->y+=(signed)moveTileSpeed;
	this->isMoving=true;
	this->direction=EntityDirection::DOWN;
	renderYOffset=-(signed)OverworldObject::tileSize;
	return true;
}

void Entity::addToList(ListHandler* handler){
	Updatable::addToList(handler);
	Renderable::addToList(handler);
}

void Entity::removeFromList(ListHandler* handler){
	Updatable::removeFromList(handler);
	Renderable::removeFromList(handler);
}

#include <cmath>

namespace EntityDirection{
	const float LEFT  = atan2( 0.0f,-1.0f);
	const float RIGHT = atan2( 0.0f, 1.0f);
	const float UP    = atan2(-1.0f, 0.0f);
	const float DOWN  = atan2( 1.0f, 0.0f);
}
