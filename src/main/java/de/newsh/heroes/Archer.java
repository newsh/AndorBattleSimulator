package de.newsh.heroes;

import de.newsh.battle.BattleParticipant;

public class Archer extends BattleParticipant {
	public Archer() {
		this(7, 1);
	}

	public Archer(int willpower, int strength) {
		super(willpower, strength);
		setName("Archer");
		setDiceDistribution(2, 3, 4);
	}

	/**
	 * A helmet has no effect on archer class. He has to throw available dices
	 * individually and decide to continue or take it's current value. No doubles
	 * are possible.
	 */
	public void setHelmet(boolean hasHelmet) {
		hasHelmet = false;
	}
}
