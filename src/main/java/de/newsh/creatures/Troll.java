package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class Troll extends BattleParticipant {
	public Troll() {
		super(12, 14);
		setName("Troll");
		setDiceDistribution(2, 3, 3);
		setHelmet(true);
	}
}
