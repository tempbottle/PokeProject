#ifndef POKEMONPROJECT_GEOM2D_RECTANGLE_H
#define POKEMONPROJECT_GEOM2D_RECTANGLE_H

template <typename T>
struct Rectangle{
	T width;
	T height;

	T hypotenuse() const;
	T area() const;
	T perimeter() const;
};

#endif
