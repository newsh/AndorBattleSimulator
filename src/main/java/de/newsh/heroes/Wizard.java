package de.newsh.heroes;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.math3.util.Precision;

import de.newsh.battle.BattleParticipant;
import java.util.Arrays;

public class Wizard extends BattleParticipant {
	public Wizard() {
		this(7, 1);
	}

	public Wizard(int willpower, int strength) {
		super(willpower, strength);
		setName("Wizard");
		setDiceDistribution(1, 1, 1);
	}

	public Integer rollDices(boolean isVerbose) {
		int rolledVal = 0;
		if (hasBlackDice)
			rolledVal = rollBlackDice();
		else
			rolledVal = rollRegularDice();
		if (hasWitchsBrew() && witchsBrewIsReasonableToUse(Arrays.asList(rolledVal), rolledVal)) {
			rolledVal = useWitchsBrew(Arrays.asList(rolledVal));
		}
		if(hasMedicinalHerb())
			rolledVal = useMedicinalHerb(rolledVal);
		if (isVerbose)
			System.out.println("Wizard rolled " + rolledVal);
		return rolledVal;
	}

	public Integer rollDice() {
		return rollDices(false);
	}

	public Integer rollDiceVerbose() {
		return rollDices(true);
	}

	protected int rollBlackDice() {
		int randIndex = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		int[] blackDiceValues = { 10, 10, 10, 10, 12, 12 };
		return blackDiceValues[randIndex];
	}

	private Integer rollRegularDice() {
		int randIndex = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		int[] regularDiceValues = { 4, 4, 5, 5, 6, 6 };
		return regularDiceValues[randIndex];
	}
	public double getAverageDamagePerRound() {
		Integer totalDamage = 0;
		double rolls = 50000;
		int witchsBrewAmountTmp = this.getWitchsBrew();
		int medicinalHerbAmountTmp = this.getMedicinalHerb();
		for (int i = 0; i < rolls; i++) {
			this.setWitchsBrew(witchsBrewAmountTmp);
			this.setMedicinalHerb(medicinalHerbAmountTmp);
			totalDamage += this.getStrength()+ rollDices(false);
		}
		return Precision.round(totalDamage / rolls,1);
	}
}
