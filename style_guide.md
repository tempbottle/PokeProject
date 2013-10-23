Style guide for PokemonProject
==============================

## Naming:

#### Source filenames:
- Start with capital letter
- Separated by camelcase

#### Constants:
- Upper case only
- Separated by underscore

#### Classnames and structures
- Start with capital letter

#### Variables and functions:
- Start with lowercase
- Separated by camelcase

#### Pointers:
- Asterisk directly after type
- Multiple pointers declarated of the same type should be separated for avoiding simple mistakes and clarity

## Guidelines

Recommended to write a description of a source file after includes.
Relevant information to include:
- Author
- Functionality of the structure in the file
- Examples of use

Do not comment out code, unless reason is given.
Documentation should use the following template:

	/** 
	 * Description of function
	 *
	 * Param argName: Description of parameter
	 * Return:        Description of return value
	 */

	/** 
	 * Description of class/structure/enum
	 *
	 * Author: name
	 */

## Controversies:
- Whitespace
- Opening bracket on single line
- Mixing camelcase and underscore in certain cases
