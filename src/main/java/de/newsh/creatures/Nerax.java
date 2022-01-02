package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class Nerax extends BattleParticipant {
	public Nerax() {
		super(5, 8);
		setName("Nerax");
		setDiceDistribution(3, 5, 5);
		setHelmet(true);
	}
}
