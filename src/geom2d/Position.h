#ifndef POKEMONPROJECT_GEOM2D_POSITION_H
#define POKEMONPROJECT_GEOM2D_POSITION_H

#include "geom2d/Vector.h"

/**
 * Position defines a position in a two dimensional plane
 *
 * Author: Lolirofle
 */
template <typename T>
struct Position{
	T x;
	T y;

	double distanceTo(Position<T> pos);
	double directionTo(Position<T> pos);
	Position<T> operator-(){
		return (Position<T>){-this->x,-this->y};
	}

	Position<T> operator+(Vector<T> other){
		return (Position<T>){this->x+other.x,this->y+other.y};
	}


	Position<T> operator-(Vector<T> other){
		return (Position<T>){this->x-other.x,this->y-other.y};
	}
};

#endif
