package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class Skral extends BattleParticipant {
	public Skral() {
		super(6, 6);
		setName("Skral");
		setDiceDistribution(2, 2, 2);
		setHelmet(true);
	}
}
