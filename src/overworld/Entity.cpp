#include "Entity.h"

#include <SDL2/SDL.h>

#include "geom2d/Rectangle.h"
#include "Renderable.h"

Entity::Entity(int x,int y) : GameObject(x,y,(Rectangle<unsigned int>){1,1}),direction(0.0f),moveSpeed(1.0f),moveTileSpeed(1),isMoving(false),renderXOffset(0.0f),renderYOffset(0.0f){}

void Entity::render(SDL_Renderer* renderer){
	SDL_SetRenderDrawColor(renderer,128,192,255,255);
	SDL_Rect rect = {
		this->x*Renderable::tileSize + (int)this->renderXOffset,
		this->y*Renderable::tileSize + (int)this->renderYOffset,
		(int)this->collisionBox.width * Renderable::tileSize,
		(int)this->collisionBox.height * Renderable::tileSize};
	SDL_RenderFillRect(renderer,&rect);
}

void Entity::update(int deltaTime){
	if(this->isMoving){
		if(renderXOffset>moveTileSpeed)
			renderXOffset-=moveTileSpeed;
		else if(-renderXOffset>moveTileSpeed)
			renderXOffset+=moveTileSpeed;
		else
			renderXOffset=0.0f;
		

		if(renderYOffset>moveTileSpeed)
			renderYOffset-=moveTileSpeed;
		else if(-renderYOffset>moveTileSpeed)
			renderYOffset+=moveTileSpeed;
		else
			renderYOffset=0.0f;
		
		if(renderXOffset==0.0f && renderYOffset==0.0f){
			this->isMoving=false;
			this->onMoveFinished();
		}
	}
}

bool Entity::moveLeft(unsigned short steps){
	if(this->isMoving || !this->onMove(this->x-moveTileSpeed,this->y))
		return false;
	this->x-=moveTileSpeed;
	this->isMoving=true;
	renderXOffset=Renderable::tileSize;
	return true;
}

bool Entity::moveRight(unsigned short steps){
	if(this->isMoving || !this->onMove(this->x+moveTileSpeed,this->y))
		return false;
	this->x+=moveTileSpeed;
	this->isMoving=true;
	renderXOffset=-Renderable::tileSize;
	return true;
}

bool Entity::moveUp(unsigned short steps){
	if(this->isMoving || !this->onMove(this->x,this->y-moveTileSpeed))
		return false;
	this->y-=moveTileSpeed;
	this->isMoving=true;
	renderYOffset=Renderable::tileSize;
	return true;
}

bool Entity::moveDown(unsigned short steps){
	if(this->isMoving || !this->onMove(this->x,this->y+moveTileSpeed))
		return false;
	this->y+=moveTileSpeed;
	this->isMoving=true;
	renderYOffset=-Renderable::tileSize;
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
