CC=g++
LINKER=g++
CFLAGS=-g $(shell sdl2-config --cflags)
LDFLAGS=$(shell sdl2-config --libs)

BINDIR=bin
OBJDIR=obj
SRCDIR=src
OUT=PokemonProject


OUTPREFIX=
OUTPOSTFIX=

OBJPOSTFIX=

ifeq ($(OS),Windows_NT)
	OUTPOSTFIX=.exe
	OBJPOSTFIX=.obj
	LDFLAGS+= -lopengl32 -lglu32
else
	UNAME_S := $(shell uname -s)
	ifneq "$(or ($(UNAME_S),Linux),($(UNAME_S),Darwin))" ""
		OBJPOSTFIX=.o
		LDFLAGS+= -lGL
	endif
endif

vpath %.cpp $(SRCDIR)

rwildcard=$(foreach d,$(wildcard $1*),$(call rwildcard,$d/,$2) $(filter $(subst *,%,$2),$d))

SOURCES=$(call rwildcard,./$(SRCDIR),*.cpp)
OBJECTS=$(SOURCES:./$(SRCDIR)/%.cpp=$(OBJDIR)/%$(OBJPOSTFIX))

all: $(OUT)

$(OBJDIR)/%$(OBJPOSTFIX): %.cpp
	$(CC) $< -I$(SRCDIR) $(CFLAGS) -o $@ -c

clean:
	rm -f $(OBJECTS) $(BINDIR)/$(OUTPREFIX)$(OUT)$(OUTPOSTFIX)

run:
	./$(BINDIR)/$(OUTPREFIX)$(OUT)$(OUTPOSTFIX)

$(OUT): $(OBJECTS)
	$(LINKER) $^ -o $(BINDIR)/$(OUTPREFIX)$@$(OUTPOSTFIX) $(LDFLAGS)
