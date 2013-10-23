#ifndef POKEMONPROJECT_GEOM2D_RECTANGLE_H
#define POKEMONPROJECT_GEOM2D_RECTANGLE_H

/**
 * The geometric two dimensional shape Rectangle
 *
 * Author: Lolirofle
 */
template <typename T>
struct Rectangle{
	T width;
	T height;

	T hypotenuse() const;
	T area() const;
	T perimeter() const;
};

#endif
