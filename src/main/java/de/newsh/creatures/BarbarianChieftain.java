package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class BarbarianChieftain extends BattleParticipant {
	public BarbarianChieftain() {
		super(1, 7);
		setName("BarbarianChieftain");
		setDiceDistribution(3, 3, 3);
		setHelmet(true);
	}
}
