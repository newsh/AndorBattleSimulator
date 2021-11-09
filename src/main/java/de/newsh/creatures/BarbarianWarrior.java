package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class BarbarianWarrior extends BattleParticipant {
	public BarbarianWarrior() {
		super(1, 3);
		setName("Barbarian");
		setDiceDistribution(2, 2, 2);
		setHelmet(true);
	}
}
