Coding Style guide for PokemonProject
=====================================

## Naming:
Use clear names. They should not be obscure abbreviations like "headset" -> "hdst" or something like that. The more understandable stuff like "pointer" -> "ptr" are accepted for example.

#### Source filenames:
- Start with capital letter
- Separated by camelcase
- Example: MyFile.cpp

#### Constants:
- Upper case only
- Separated by underscore
- Example: MY_CONSTANT

#### Classnames and structures
- Start with capital letter
- Separated by camelcase
- Example: class MyClass;

#### Variables and functions:
- Start with lowercase
- Separated by camelcase
- Example: int myVariable;

#### Pointers:
- Asterisk directly after type
- Multiple pointers declarated of the same type should be separated for avoiding simple mistakes and clarity
- Example: int* i;

#### Include guards:
- Upper case
- Separated by underscore
- Example: POKEMONPROJECT_FOLDER_ANOTHERFOLDER_FILENAME_H

## Guidelines

#### Examples:
If statement:

	if(condition)
		doThis();
	else if(
		thisIsTrue ||
		thatIsTrue ||
		otherIsTrue ||
		everythingIsTrue ||
		nothingIsTrue
	){
		doSomething();
	}else
		doOther();

Switch statement:

	switch(value){
		case 0:
			doThis(1,2,3);
			break;
		default:
			doOther(
				SOME_CONSTANT,
				ANOTHER_CONSTANT,
				WELL_HERE_IS_ANOTHER_ONE
			);
			break;
	}

Scoped statements:

	{//Comment about this scope
		ImportantClass* temporaryVariable = new ImportantClass();
		temporaryVariable->badCodeStructure();
		delete temporaryVariable;
	}

Namespace:

	namespace SomethingThatHasAGoodName{
		int doYouEven();
		const int IMPORTANT_STUFF = 0;
	}

Variable declaration:

	//Short names
	int a,b;

	//Longer names
	int anWonderfulEvening,
	    bePreparedForFight;

#### Indentation:
Use tabs (\t) and not spaces for indentation.
Use whitespace ONLY for code alignment, e.g. when declaring multiple variables of the same type. Tab indentation should be applied first, and then the whitespace alignment.
Indentation after each layer of scope, this also applies to switch case labels, namespaces and long argument lists.

#### Brackets and paranthesis
If the brackets are belonging to something (like a statement or structure declaration), then it should be placed at the end of the last line for that something. To sum it up, opening brackets should not be placed on a new line.

#### Spaces
Not defined yet

#### Documentation
Description of a definition/declaration should be in the header file.
Comments on the implementation should be in the source file.

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

#### Other stuff:
No limit for line length, but it is better to keep it short while following the guidelines above.
Use operator overloading where it is appropriate.
Do not cast away const.
Use TODO comments when something needs to be fixed in the future.

## Controversies:
- Whitespace
- Mixing camelcase and underscore in certain cases
