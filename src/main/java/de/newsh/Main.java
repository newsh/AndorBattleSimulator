package de.newsh;

import java.util.ArrayList;
import java.util.List;

import de.newsh.battle.Battle;
import de.newsh.battle.BattleParticipant;
import de.newsh.creatures.*;
import de.newsh.heroes.*;

public class Main {
	public static void main(String[] args) {
		List<BattleParticipant> group = new ArrayList<>();
		Wizard wizard = new Wizard(7,3);
		wizard.setBlackDice(true);
		Warrior warrior = new Warrior(7,3);
		warrior.setHelmet(true);
		Orfen orfen = new Orfen();
		group.add(wizard);
		group.add(warrior);
		group.add(orfen);
		Battle battle = new Battle(group, new Troll());
		battle.runSimulationsVerbose();
	}
	
}
