package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class Arrog extends BattleParticipant {
	public Arrog() {
		super(10, 24);
		setName("Arrog");
		setDiceDistribution(3, 5, 5);
		setHelmet(true);
	}
}
