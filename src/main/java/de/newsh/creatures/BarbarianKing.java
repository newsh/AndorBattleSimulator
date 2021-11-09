package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class BarbarianKing extends BattleParticipant {
	public BarbarianKing(int strength) {
		super(1, strength);
		setName("BarbarianKing");
		setDiceDistribution(3, 3, 3);
		setHelmet(true);
	}
}
