#include "OverworldObject.h"

#include "geom2d/Rectangle.h"

OverworldObject::OverworldObject(int x,int y,Rectangle<unsigned int> collisionBox) : x(x),y(y),collisionBox(collisionBox){}
