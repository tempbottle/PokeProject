#include "Rectangle.h"

#include <cmath>

template <typename T>
T Rectangle<T>::hypotenuse() const{
	return hypot(this->width,this->height);
}

template <typename T>
T Rectangle<T>::area() const{
	return this->width*this->height;
}

template <typename T>
T Rectangle<T>::perimeter() const{
	return this->width*2 + this->height*2;
}

