function makeHeroWillpowerPreSelection() {
	var items = document.getElementsByClassName('willpowerHeroes');
	for (var i = 0; i < items.length; i++) {
		items[i].value = 7;
	}
}

function setCreatureWillpowerAndStrength() {
	//Find out what creature is picked, set values accordingly
	var strengthVal;
	var willpowerVal;
	var selectedCreature = document.getElementById('creature').value;
	switch (selectedCreature) {
		case 'Gor':
			willpowerVal = 4;
			strengthVal = 3;
			break;
		case 'Skral':
			willpowerVal = 6;
			strengthVal = 6;
			break;
		case 'Troll':
			willpowerVal = 12;
			strengthVal = 14;
			break;
		case 'Wardrak':
			willpowerVal = 7;
			strengthVal = 10;
			break;
		case 'Barbarian Warrior':
			willpowerVal = 1;
			strengthVal = 3;
			break;
		case 'Barbarian Chieftain':
			willpowerVal = 1;
			strengthVal = 7;
			break;
		case 'Barbarian King':
			willpowerVal = 1;
			strengthVal = 12;
			break;
		case 'BlackHerald':
			willpowerVal = 6;
			strengthVal = 10;
			break;
		case 'BarbarianWarrior':
			willpowerVal = 1;
			strengthVal = 3;
			break;
		case 'BarbarianChieftain':
			willpowerVal = 1;
			strengthVal = 7;
			break;
		case 'BarbarianKing':
			willpowerVal = 1;
			strengthVal = 12;
			break;
	}


	document.getElementById('strengthCreature').value = strengthVal;
	document.getElementById('willpowerCreature').value = willpowerVal;
}

function addDiv() {
	$(document).ready(function() {
		if ($('.hero').length > 5) {
			alert(`${maxPlayerReachedAlertl10n}`);
			return;
		}
		var willpowerOptions = "";
		for (var i = 1; i <= 20; i++) {
			if (i == 7) willpowerOptions += `<option value="${i}" selected>${i}</option>`;
			else willpowerOptions += `<option value="${i}">${i}</option>`;
		}
		var strengthOptions = "";
		for (var i = 1; i <= 30; i++)
			strengthOptions += `<option value="${i}">${i}</option>`;
		if ($('.hero').length <= 1)
			$('.firstStrength').after(`<i class="removeButton bi bi-trash" style="font-size: 24px; margin-left:10px" ></i>`);

		$('#heroesDiv').append(`<div class="hero">
									<select name="heroes">
										<option value="Archer">${archerl10n}</option>
										<option value="Warrior">${warriorl10n}</option>
										<option value="Wizard">${wizardl10n}</option>
										<option value="Dwarf">${dwarfl10n}</option>
										<option value="Orfen">${wolfWarriorl10n}</option>
									</select> 
									<label> WP:
									<select class="willpowerHeroes" name="willpowerHeros">${willpowerOptions} </select>
									</label>
									<label> SP:
									<select class = "firstStrength" name="strengthHeros">${strengthOptions} </select>
									</label>
									<i class="removeButton bi bi-trash" style="font-size: 24px; margin-left:10px" ></i>
									<label><br>${itemsl10n}:<br>
									<select name="item1">
										<option>-</option>
										<option value="helmet">${helmetl10n}</option>
										<option value="blackDice">${blackDicel10n}</option>
										<option value="witchBrew1x">${witchBrew1l10n}</option>
										<option value="witchBrew2x">${witchBrew2l10n}</option>
										<option value="witchBrew3x">${witchBrew3l10n}</option>
										<option value="witchBrew4x">${witchBrew4l10n}</option>
										<option value="witchBrew5x">${witchBrew5l10n}</option>
										<option value="witchBrew6x">${witchBrew6l10n}</option>
										<option value="medicinalHerb3">${medicinalHerb3l10n}</option>
										<option value="medicinalHerb4">${medicinalHerb4l10n}</option>
										<option value="medicinalHerb7">${medicinalHerb7l10n}</option>
										<option value="medicinalHerb8">${medicinalHerb8l10n}</option>
										<option value="medicinalHerb11">${medicinalHerb11l10n}</option>
										<option value="ballista">${ballistal10n}</option>
									</select> <br>
									</label>
									<select name="item2">
										<option>-</option>
										<option value="helmet">${helmetl10n}</option>
										<option value="blackDice">${blackDicel10n}</option>
										<option value="witchBrew1x">${witchBrew1l10n}</option>
										<option value="witchBrew2x">${witchBrew2l10n}</option>
										<option value="witchBrew3x">${witchBrew3l10n}</option>
										<option value="witchBrew4x">${witchBrew4l10n}</option>
										<option value="witchBrew5x">${witchBrew5l10n}</option>
										<option value="witchBrew6x">${witchBrew6l10n}</option>
										<option value="medicinalHerb3">${medicinalHerb3l10n}</option>
										<option value="medicinalHerb4">${medicinalHerb4l10n}</option>
										<option value="medicinalHerb7">${medicinalHerb7l10n}</option>
										<option value="medicinalHerb8">${medicinalHerb8l10n}</option>
										<option value="medicinalHerb11">${medicinalHerb11l10n}</option>
										<option value="ballista">${ballistal10n}</option>
									</select> <br>
									<select name="item3">
										<option>-</option>
										<option value="helmet">${helmetl10n}</option>
										<option value="blackDice">${blackDicel10n}</option>
										<option value="witchBrew1x">${witchBrew1l10n}</option>
										<option value="witchBrew2x">${witchBrew2l10n}</option>
										<option value="witchBrew3x">${witchBrew3l10n}</option>
										<option value="witchBrew4x">${witchBrew4l10n}</option>
										<option value="witchBrew5x">${witchBrew5l10n}</option>
										<option value="witchBrew6x">${witchBrew6l10n}</option>
										<option value="medicinalHerb3">${medicinalHerb3l10n}</option>
										<option value="medicinalHerb4">${medicinalHerb4l10n}</option>
										<option value="medicinalHerb7">${medicinalHerb7l10n}</option>
										<option value="medicinalHerb8">${medicinalHerb8l10n}</option>
										<option value="medicinalHerb11">${medicinalHerb11l10n}</option>
										<option value="ballista">${ballistal10n}</option>
									</select> <br>
									<select name="item4">
										<option>-</option>
										<option value="helmet">${helmetl10n}</option>
										<option value="blackDice">${blackDicel10n}</option>
										<option value="witchBrew1x">${witchBrew1l10n}</option>
										<option value="witchBrew2x">${witchBrew2l10n}</option>
										<option value="witchBrew3x">${witchBrew3l10n}</option>
										<option value="witchBrew4x">${witchBrew4l10n}</option>
										<option value="witchBrew5x">${witchBrew5l10n}</option>
										<option value="witchBrew6x">${witchBrew6l10n}</option>
										<option value="medicinalHerb3">${medicinalHerb3l10n}</option>
										<option value="medicinalHerb4">${medicinalHerb4l10n}</option>
										<option value="medicinalHerb7">${medicinalHerb7l10n}</option>
										<option value="medicinalHerb8">${medicinalHerb8l10n}</option>
										<option value="medicinalHerb11">${medicinalHerb11l10n}</option>
										<option value="ballista">${ballistal10n}</option>
									</select>
									 
	`)
	});
}

$(document).ready(function() {
	$('#heroesDiv').on('click', '.removeButton', function(events) {
		$(this).closest('div').remove();
		if ($('.hero').length == 1) {
			$("i").remove(".removeButton");
		}
	});

});
