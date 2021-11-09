package de.newsh.creatures;

import de.newsh.battle.BattleParticipant;

public class Wardrak extends BattleParticipant {
	public Wardrak() {
		super(7, 10);
		setName("Wardraks");
		setDiceDistribution(1, 2, 3);
		setHelmet(true);
		setBlackDice(true);
	}

	protected short getAmountOfDice() {
		short diceAmount = 0;
		if (willpower >= 0 && willpower <= 6)
			diceAmount = diceDistribution[0];
		if (willpower >= 7 && willpower <= 13)
			diceAmount = diceDistribution[1];
		if (willpower >= 14 && willpower <= 20)
			diceAmount = diceDistribution[2];
		return diceAmount;
	}
}
