package de.newsh.heroes;

import de.newsh.battle.BattleParticipant;

public class SeaWarrior extends BattleParticipant {
	public SeaWarrior() {
		this(7, 1);
	}

	public SeaWarrior(int willpower, int strength) {
		super(willpower, strength);
		setName("Sea Warrior");
		setDiceDistribution(2, 2, 3);
	}
}
