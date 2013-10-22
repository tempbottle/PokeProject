#ifndef POKEMONPROJECT_GEOM2D_RECTANGLE_H
#define POKEMONPROJECT_GEOM2D_RECTANGLE_H

struct Rectangle{
	float width;
	float height;

	float hypotenuse() const;
	float area() const;
	float perimeter() const;
};

#endif
