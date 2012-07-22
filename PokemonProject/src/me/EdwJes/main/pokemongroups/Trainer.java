package me.EdwJes.main.pokemongroups;

import me.EdwJes.main.pokemon.Pokemon;

public class Trainer implements PokemonGroup{
	private static int MAX_POKEMONS=6;

	@Override
	public int getPokemonCount(){
		//Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxPokemonCount(){
		return MAX_POKEMONS;
	}

	@Override
	public Pokemon getPokemon(int index){
		//Auto-generated method stub
		return null;
	} 
}
