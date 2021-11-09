package de.newsh.heroes;

import de.newsh.battle.BattleParticipant;

public class Dwarf extends BattleParticipant {
	public Dwarf() {
		this(7, 1);
	}

	public Dwarf(int willpower, int strength) {
		super(willpower, strength);
		setName("Dwarf");
		setDiceDistribution(1, 2, 3);
	}
}
