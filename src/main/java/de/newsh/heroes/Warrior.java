package de.newsh.heroes;

import de.newsh.battle.BattleParticipant;

public class Warrior extends BattleParticipant {
	public Warrior() {
		this(7, 1);
	}

	public Warrior(int willpower, int strength) {
		super(willpower, strength);
		setName("Warrior");
		setDiceDistribution(2, 3, 4);
	}
}