package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class SeaTroll extends BattleParticipant {
	public SeaTroll() {
		super(6, 12);
		setName("Sea Troll");
		setDiceDistribution(3, 5, 5);
		setHelmet(true);
	}
}
