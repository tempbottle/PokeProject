#include "Rectangle.h"

#include <cmath>

float Rectangle::hypotenuse() const{
	return hypot(this->width,this->height);
}

float Rectangle::area() const{
	return this->width*this->height;
}

float Rectangle::perimeter() const{
	return this->width*2 + this->height*2;
}