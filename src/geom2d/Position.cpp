#include "Position.h"

#include <cmath>

template <typename T>
double Position<T>::distanceTo(Position<T> pos){
	return hypot(pos.x-this->x,pos.y-this->y);
}

template <typename T>
double Position<T>::directionTo(Position<T> pos){
	return atan2(pos.y-this->y,pos.x-this->x);
}
