#ifndef POKEMONPROJECT_GEOM2D_VECTOR_H
#define POKEMONPROJECT_GEOM2D_VECTOR_H

template <typename T>
struct Position;

/**
 * Vector defines a change in position in a two dimensional plane
 *
 * Author: Lolirofle
 */
template <typename T>
struct Vector{
	T x;
	T y;

	Vector(T x,T y) : x(x),y(y){}
	Vector(Position<T> pos);
	virtual ~Vector(){}

	double length();
	double direction();
	
	Vector<T> operator-(){
		return (Vector<T>){-this->x,-this->y};
	}

	Vector<T> operator-(Vector<T> other){
		return (Vector<T>){this->x-other.x,this->y-other.y};
	}

	Vector<T> operator+(Vector<T> other){
		return (Vector<T>){this->x+other.x,this->y+other.y};
	}
};

#endif
