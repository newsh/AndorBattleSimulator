package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class BlackHerald extends BattleParticipant {
	public BlackHerald() {
		super(6, 10);
		setName("Black Herald");
		setBlackDice(true);
		setHelmet(true);
	}
}
