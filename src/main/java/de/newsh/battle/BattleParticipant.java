package de.newsh.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import de.newsh.creatures.*;
import de.newsh.heroes.*;

/**
 * Class representation of hero or creature participating in a battle.
 * 
 * @author Andi
 *
 */
public abstract class BattleParticipant implements Cloneable {
	private String name;
	protected short willpower;
	private short strength;
	/**
	 * Dice distribution depending on willpower points as represented on hero
	 * boards. <br>
	 * [0] -> Amount of dice in 1st row (0-6 willpower points) <br>
	 * [1] -> Amount of dice in 2nd row (7-13 willpower points) <br>
	 * [2] -> Amount of dice in 3rd row (14-20 willpower points)
	 */
	protected short[] diceDistribution;
	private boolean hasHelmet;
	protected boolean hasBlackDice;

	protected BattleParticipant(int willpower, int strength) {
		this.willpower = (short) willpower;
		this.strength = (short) strength;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public short getWillpower() {
		return willpower;
	}

	public void decreaseWillpowerBy(int willpower) {
		this.willpower -= willpower;
	}

	public short getStrength() {
		return strength;
	}

	/**
	 * Returns highest value after rolling all available dices.
	 * 
	 * <p>
	 * Available dices are specified by character class, current willpower points
	 * and equipment held.
	 * 
	 * @param isVerbose
	 * @return
	 */
	private Integer rollDices(boolean isVerbose) {
		short diceCount = getAmountOfDice();
		ArrayList<Integer> valuesRolled = new ArrayList<Integer>();
		for (int i = 0; i < diceCount; i++) {
			if (hasBlackDice)
				valuesRolled.add(rollBlackDice());
			else
				valuesRolled.add(rollRegularDice());
		}
		int highestVal = 0;
		if (hasHelmet)
			highestVal = getDoubles(valuesRolled);
		else
			highestVal = Collections.max(valuesRolled);
		if (isVerbose)
			System.out.println(name + " rolled " + highestVal + ". " + valuesRolled.toString());
		return highestVal;
	}

	/**
	 * Verbose version of {@link BattleParticipant#rollDices(boolean)}
	 * 
	 * @see BattleParticipant#rollDices(boolean)
	 * @return
	 */
	protected Integer rollDiceVerbose() {
		return rollDices(true);
	}

	/**
	 * Implicit non verbose version of {@link BattleParticipant#rollDices(boolean)}
	 * 
	 * @see BattleParticipant#rollDices(boolean)
	 * @return
	 */
	protected Integer rollDice() {
		return rollDices(false);
	}

	/**
	 * Returns random side of a black six sided dice. Dice's sides are
	 * 6,6,8,10,10,12.
	 * 
	 * @return random value from 6,6,8,10,10,12
	 */
	protected int rollBlackDice() {
		int randIndex = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		int[] blackDiceValues = { 6, 6, 8, 10, 10, 12 };
		return blackDiceValues[randIndex];
	}

	/**
	 * Returns random side of a regular six sided dice.
	 * 
	 * @return random value from 1 to 6
	 */
	private Integer rollRegularDice() {
		return ThreadLocalRandom.current().nextInt(1, 6 + 1);
	}

	/**
	 * Retrieves amount of dice available to battle participant.
	 * 
	 * @return diceAmount
	 */
	protected short getAmountOfDice() {
		if (hasBlackDice)
			return 1;
		short diceAmount = 0;
		if (willpower >= 0 && willpower <= 6)
			diceAmount = diceDistribution[0];
		if (willpower >= 7 && willpower <= 13)
			diceAmount = diceDistribution[1];
		if (willpower >= 14 && willpower <= 20)
			diceAmount = diceDistribution[2];
		return diceAmount;
	}

	/**
	 * Retrieves highest rolled dice result considering doubles of 2,3,4,...n
	 * 
	 * <p>
	 * [4,6,3,5,4](4-doublet) -> returns 8
	 * 
	 * @param valuesRolled
	 * @return
	 */
	protected static int getDoubles(List<Integer> valuesRolled) {
		int highestSoFar = 0;
		int val, valTotal = 0;
		for (int i = 0; i < valuesRolled.size(); i++) {
			val = valuesRolled.get(i);
			valTotal = val;
			for (int j = i + 1; j < valuesRolled.size(); j++) {
				if (valuesRolled.get(j) == val)
					valTotal += valuesRolled.get(j);
			}
			if (valTotal > highestSoFar)
				highestSoFar = valTotal;
		}
		return highestSoFar;
	}

	public String toString() {
		String str = null;
		str = String.format("%s(%dW,%dS", name, willpower, getStrength());
		if (hasHelmet)
			str += ",Helmet";
		if (hasBlackDice)
			str += ",Black Dice";
		str += ")";
		return str;
	}

	@Override
	public BattleParticipant clone() {
		try {
			return (BattleParticipant) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	protected void setDiceDistribution(int diceCountFor0to6Willpower, int diceCountFor7to13Willpower,
			int diceCountFor14to20Willpower) {
		this.diceDistribution = new short[] { (short) diceCountFor0to6Willpower, (short) diceCountFor7to13Willpower,
				(short) diceCountFor14to20Willpower };
	}

	public void setHelmet(boolean hasHelmet) {
		this.hasHelmet = hasHelmet;
	}

	public void setBlackDice(boolean hasBlackDice) {
		this.hasBlackDice = hasBlackDice;
	}

	public static BattleParticipant getClassByName(String className) {
		switch (className) {
		case "Archer":
			return new Archer();
		case "Warrior":
			return new Warrior();
		case "Wizard":
			return new Wizard();
		case "Dwarf":
			return new Dwarf();
		case "Orfen":
			return new Orfen();
		case "Gor":
			return new Gor();
		case "Skral":
			return new Skral();
		case "Wardrak":
			return new Wardrak();
		case "Troll":
			return new Troll();
		case "BarbarianWarrior":
			return new BarbarianWarrior();
		case "BarbarianChieftain":
			return new BarbarianChieftain();
		case "BarbarianKing":
			return new BarbarianKing(12);
		default:
			break;
		}
		return null;
	}
}
