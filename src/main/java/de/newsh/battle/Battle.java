package de.newsh.battle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Battle {
	private int numberOfSimulationsToRun = 50000;
	private List<BattleParticipant> heroes = new ArrayList<BattleParticipant>();
	private List<BattleParticipant> heroesTmp = new ArrayList<BattleParticipant>();
	private BattleParticipant creature;
	private BattleParticipant creatureTmp;
	private int baseStrengthHeroes;
	private int rolledStrengthByHeroes;
	private int strengthTotalHeroes;
	private int baseStrengthCreature;
	private int rolledStrengthByCreature;
	private int strengthTotalCreature;
	private int heroesCratureStrengthDiff;
	private int roundNumber;
	private int willpowerLost;
	private int willpowerLostTotal;
	private int heroesWinCount;
	private int hoursNeededTotal;

	public Battle(BattleParticipant hero, BattleParticipant creature) {
		this(Arrays.asList(hero), creature);
	}

	public Battle(List<BattleParticipant> group, BattleParticipant creature) {
		this.creature = creature.clone();
		for (BattleParticipant groupMember : group) {
			this.heroes.add(groupMember.clone());
		}
		baseStrengthHeroes = getHeroesBaseStrength();
		baseStrengthCreature = creature.getStrength();
	}

	/**
	 * @see Battle#runSimulations()
	 */
	private void runSimulations(boolean isVerbose) {
		for (int i = 0; i < numberOfSimulationsToRun; i++) {
			resetBattleData();
			while (participantsStillHaveWillpowerPts()) {
				roundNumber++;
				hoursNeededTotal++;
				rolledStrengthByHeroes = 0;
				for (BattleParticipant hero : heroesTmp) {
					if (isVerbose && i < 10)
						rolledStrengthByHeroes += hero.rollDiceVerbose();
					else
						rolledStrengthByHeroes += hero.rollDice();
				}
				strengthTotalHeroes = rolledStrengthByHeroes + baseStrengthHeroes;
				rolledStrengthByCreature = 0;
				if (isVerbose && i < 10)
					rolledStrengthByCreature = creatureTmp.rollDiceVerbose();
				else
					rolledStrengthByCreature = creatureTmp.rollDice();
				strengthTotalCreature = rolledStrengthByCreature + baseStrengthCreature;
				heroesCratureStrengthDiff = Math.abs(strengthTotalHeroes - strengthTotalCreature);
				if (strengthTotalHeroes > strengthTotalCreature) {
					creatureTmp.decreaseWillpowerBy(heroesCratureStrengthDiff);
				}
				if (strengthTotalHeroes < strengthTotalCreature) {
					heroesTmp.stream().forEach(hero -> hero.decreaseWillpowerBy(heroesCratureStrengthDiff));
					willpowerLost += heroesCratureStrengthDiff;
					willpowerLostTotal += heroesCratureStrengthDiff;
				}
				if (isVerbose && i < 10) {
					printBattleroundData();
					if (i == 9)
						System.out.println(String.format("(+%d more simulations. Not displayed.)\n",
								numberOfSimulationsToRun - 9));
				}
			}
			if (creatureTmp.getWillpower() <= 0)
				heroesWinCount++;
		}
		printSummary();
	}

	/**
	 * Prints a summary of battle's most relevant data.
	 */
	public void printSummary() {
		System.out.println(
				"Summary for:\n" + getParticipantsHeroes()
						+ "\n	vs. \n" + getParticipantsCreature() + "\n" + "\nWin rate: "
						+ getWinRatePercentage()
						+ "\nWillpower lost: ∅ " + getWillpowerLostAverage()
						+ "\nHours needed: ∅ " + getHoursNeededAverage()
						+ "\nSimulations run: " + numberOfSimulationsToRun);
	}
	/**
	 * Prints data of a battle's single round.
	 */
	private void printBattleroundData() {
		System.out.println(String.format("(%d+%d)-(%d+%d)", baseStrengthHeroes, rolledStrengthByHeroes,
				baseStrengthCreature, rolledStrengthByCreature));
		if (heroesCratureStrengthDiff == 0)
			System.out.println("Round draw! (" + strengthTotalCreature + "vs" + strengthTotalHeroes + ")");
		if (strengthTotalHeroes > strengthTotalCreature)
			System.out.println("Heroes WON this round by +" + heroesCratureStrengthDiff);
		if (strengthTotalHeroes < strengthTotalCreature)
			System.out.println("Heroes LOST this round by " + heroesCratureStrengthDiff);
		if (!participantsStillHaveWillpowerPts())
			System.out.println("Battle has ended. Took " + roundNumber + " hrs. " + willpowerLost
					+ " health pts were lost.\n================================");
	}

	/**
	 * Resetting battle's data in order to start next simulation with replenished
	 * participants' willpower points.
	 */
	private void resetBattleData() {
		roundNumber = 0;
		willpowerLost = 0;
		creatureTmp = creature.clone();
		heroesTmp.clear();
		for (BattleParticipant hero : heroes) {
			heroesTmp.add(hero.clone());
		}
	}

	/**
	 * Runs multiple simulations of a battle to determine the most probable outcome.
	 */
	public void runSimulations() {
		runSimulations(false);
	}

	/**
	 * A more verbose version of {@link Battle#runSimulations()}.
	 * <p>
	 * Prints detailed data of single battle rounds. Limited to display only first
	 * 10 battle simulations.
	 * 
	 * @see Battle#runSimulations()
	 */
	public void runSimulationsVerbose() {
		runSimulations(true);
	}

	/**
	 * Retrieves accumulated value of every hero's individual strength point.
	 */
	private int getHeroesBaseStrength() {
		return heroes.stream().collect(Collectors.summingInt(hero -> hero.getStrength()));
	}

	private boolean participantsStillHaveWillpowerPts() {
		return creatureTmp.getWillpower() > 0 && heroesTmp.stream().allMatch(hero -> hero.getWillpower() > 0);
	}

	/**
	 * Sets number of simulation to run. Has a default value of 50.000 if nothing is
	 * set.
	 * 
	 * @param numberOfSimulations
	 */
	public void setSimulationsToRun(int numberOfSimulations) {
		this.numberOfSimulationsToRun = numberOfSimulations;
	}
	/**
	 * Returns string representation of battle's hero participants.
	 * @return string
	 */
	public String getParticipantsHeroes() {
		return heroes.stream().map(BattleParticipant::toString).collect(Collectors.joining(", "));
	}
	/**
	 * Returns string representation of battle's creature participants.
	 * @return string
	 */
	public String getParticipantsCreature() {
		return creature.toString();
	}
	public String getWinRatePercentage() {
		DecimalFormat df = new DecimalFormat("####0.00");
		return df.format((double) heroesWinCount / numberOfSimulationsToRun * 100) + " %";
	}
	public String getWillpowerLostAverage() {
		DecimalFormat df = new DecimalFormat("####0.00");
		return df.format((double) willpowerLostTotal / numberOfSimulationsToRun);
	}
	public String getHoursNeededAverage() {
		DecimalFormat df = new DecimalFormat("####0.00");
		return df.format((double) hoursNeededTotal / numberOfSimulationsToRun);
	}
}
