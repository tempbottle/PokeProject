#include "Vector.h"

#include "geom2d/Position.h"

template <typename T>
Vector<T>::Vector(Position<T> pos) : x(pos.x),y(pos.y){}
