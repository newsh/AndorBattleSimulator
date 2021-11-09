package de.newsh.heroes;

import de.newsh.battle.BattleParticipant;

public class Orfen extends BattleParticipant {
	public Orfen() {
		this(7, 1);
	}

	public Orfen(int willpower, int strength) {
		super(willpower, strength);
		setName("Wolf Warrior");
		setDiceDistribution(2, 2, 3);
	}
}
