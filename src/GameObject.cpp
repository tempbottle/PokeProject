#include "GameObject.h"

#include "geom2d/Rectangle.h"

GameObject::GameObject(int x,int y,Rectangle collisionBox) : x(x),y(y),collisionBox(collisionBox){}