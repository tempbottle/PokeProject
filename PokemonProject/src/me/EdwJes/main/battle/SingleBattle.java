package me.EdwJes.main.battle;

public class SingleBattle extends Battle {
	
	BattleEntity activePokemon1, activePokemon2;

	public SingleBattle(Trainer trn1, Trainer trn2) {
		super(trn1, trn2);
		activePokemon1 = trn1.pokemonList.get(0);
		activePokemon2 = trn2.pokemonList.get(0);
		handleInput();
	}
	
	public void handleInput(){
		
	}

}
