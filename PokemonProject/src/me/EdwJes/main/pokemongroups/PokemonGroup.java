package me.EdwJes.main.pokemongroups;

import me.EdwJes.main.pokemon.data.Pokemon;

public interface PokemonGroup{
	public int getPokemonCount();
	public int getMaxPokemonCount();
	public Pokemon getPokemon(int index);
}
