package de.newsh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import de.newsh.battle.Battle;
import de.newsh.battle.BattleParticipant;

@Controller
public class BattleSimController {
	@GetMapping
	public String get(Model model) {
		return "battleSimInput";
	}

	@PostMapping
	public String post(Model model, @RequestParam(required = false) String[] heroes,
			@RequestParam(required = false) String[] willpowerHeros,
			@RequestParam(required = false) String[] strengthHeros, @RequestParam(required = false) String creature,
			@RequestParam(required = false) Integer willpowerCreature,
			@RequestParam(required = false) Integer strengthCreature, @RequestParam(required = false) String[] item1,
			@RequestParam(required = false) String[] item2, @RequestParam(required = false) String[] item3,
			@RequestParam(required = false) String[] item4) {

		List<BattleParticipant> heroGroup = new ArrayList<>();
		for (int i = 0; i < heroes.length; i++) {
			BattleParticipant hero = BattleParticipant.getClassByName(heroes[i]);
			hero.setStrength(Short.valueOf(strengthHeros[i]));
			hero.setWillpower(Short.valueOf(willpowerHeros[i]));
			List<String> itemsList = Arrays.asList(item1[i], item2[i], item3[i], item4[i]);
			if (isSelectedAsItem("helmet", itemsList))
				hero.setHelmet(true);
			if (isSelectedAsItem("blackDice", itemsList))
				hero.setBlackDice(true);
			if (isSelectedAsItem("witchBrew1x", itemsList))
				hero.setWitchsBrew(hero.getWitchsBrew() + 1);
			if (isSelectedAsItem("witchBrew2x", itemsList))
				hero.setWitchsBrew(hero.getWitchsBrew() + 2);
			if (isSelectedAsItem("witchBrew3x", itemsList))
				hero.setWitchsBrew(hero.getWitchsBrew() + 3);
			if (isSelectedAsItem("witchBrew4x", itemsList))
				hero.setWitchsBrew(hero.getWitchsBrew() + 4);
			if (isSelectedAsItem("witchBrew5x", itemsList))
				hero.setWitchsBrew(hero.getWitchsBrew() + 5);
			if (isSelectedAsItem("witchBrew6x", itemsList))
				hero.setWitchsBrew(hero.getWitchsBrew() + 6);
			if (isSelectedAsItem("medicinalHerb3", itemsList))
				hero.setMedicinalHerb(3);
			if (isSelectedAsItem("medicinalHerb4", itemsList))
				hero.setMedicinalHerb(4);
			if (isSelectedAsItem("medicinalHerb7", itemsList))
				hero.setMedicinalHerb(7);
			if (isSelectedAsItem("medicinalHerb8", itemsList))
				hero.setMedicinalHerb(8);
			if (isSelectedAsItem("medicinalHerb11", itemsList))
				hero.setMedicinalHerb(11);
			if (isSelectedAsItem("ballista", itemsList))
				hero.setOnBallista(true);
			heroGroup.add(hero);
		}
		BattleParticipant enemy = BattleParticipant.getClassByName(creature);
		enemy.setWillpower(willpowerCreature.shortValue());
		enemy.setStrength(strengthCreature.shortValue());

		Battle battle = new Battle(heroGroup, enemy);
		battle.runSimulations();
		model.addAttribute("battle", battle);
		return "battleSimResult";

	}

	private boolean isSelectedAsItem(String itemName, List<String> items) {
		return itemName.equals(items.get(0)) || itemName.equals(items.get(1)) || itemName.equals(items.get(2))
				|| itemName.equals(items.get(3));
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.GERMAN);
		return slr;
	}
}
