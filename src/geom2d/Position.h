#ifndef POKEMONPROJECT_GEOM2D_POSITION_H
#define POKEMONPROJECT_GEOM2D_POSITION_H

template <typename T>
struct Position{
	T x;
	T y;

	double distanceTo(Position<T> pos);
	double directionTo(Position<T> pos);
	Position<T> operator-(){
		return (Position<T>){-this->x,-this->y};
	}
};

#endif
