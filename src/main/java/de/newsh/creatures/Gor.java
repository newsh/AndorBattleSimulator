package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class Gor extends BattleParticipant {
	public Gor() {
		super(4, 3);
		setName("Gor");
		setDiceDistribution(2, 2, 2);
		setHelmet(true);
	}
}
