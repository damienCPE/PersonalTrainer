var training = {};

$('#formAddTraining').on('submit', function(e) {
	e.preventDefault();

	if (beforeAddTraining() == -1) {
		alert("il faut au moins un exercice");
		return false;
	}

	var jqxhr = $.post("/trainingQueue", {
		training : JSON.stringify($('#formAddTraining').serializeObject())
	}, function() {
		// alert( "success" );
	}).done(function() {
		// alert( "second success" );
	}).fail(function() {
		// alert( "error" );
	}).always(function() {
		// alert( "finished" );
	});
	return false;
})

$.fn.serializeObject = function() {
	var a = this.serializeArray();
	// console.log(this);
	console.log(a);
	$.each(a, function() {
		if (training[this.name] !== undefined) {
			if (!training[this.name].push) {
				training[this.name] = [ training[this.name] ];
			}
			training[this.name].push(this.value || '');
		} else {
			training[this.name] = this.value || '';
		}
	});
	console.log(training);
	return training;
};

var beforeAddTraining = function() {
	if (training["exercices"] === undefined) {
		return -1;
	}
}

var addExercice = function() {
	if (training["exercices"] !== undefined) {
		if (!training["exercices"].push) {
			training["exercices"] = [ training["exercices"] ];
		}
		training["exercices"]
				.push({
					"title" : document.getElementById('titleDescription').value,
					"description" : document
							.getElementById('exerciceDescription').value,
					"time" : parseInt(document.getElementById('timeHours').value)
							* 3600
							+ parseInt(document.getElementById('timeMinutes').value)
							* 60
							+ parseInt(document.getElementById('timeSecondes').value),
					"repetition" : 1
				}
						|| '');
	} else {
		training["exercices"] = [ {
			"title" : document.getElementById('titleDescription').value,
			"description" : document.getElementById('exerciceDescription').value,
			"time" : parseInt(document.getElementById('timeHours').value)
					* 3600
					+ parseInt(document.getElementById('timeMinutes').value)
					* 60
					+ parseInt(document.getElementById('timeSecondes').value),
			"repetition" : 1
		} ]
				|| '';
	}
	// console.log('training');
	// console.log(training);
	updateExercice();
}

var updateExercice = function() {
	if (training["exercices"] === undefined)
		return;
	var exercicelist = document.getElementById("exerciceList");
	exercicelist.innerHTML = "";
	$
			.each(
					training["exercices"],
					function(index, value) {
						console.log(index);
						console.log(value);
						exercicelist.innerHTML += "<tr id=\"exercice"
								+ index
								+ "\">"
								+ "<td>"
								+ value.repetition
								+ "</td>"
								+ "<td>"
								+ value.title
								+ "</td>"
								+ "<td class=\"hidden-xs\"><p>"
								+ value.description
								+ "</p></td>"
								+ "<td>"
								+ value.time
								+ "</td>"
								+ "<td>"
								+ "<button type=\"submit\" class=\"btn btn-danger btn-sm\" onclick=\"removeExercice("
								+ index
								+ ")\">"
								+ "<span class=\"glyphicon glyphicon-remove\"></span>"
								+ "</button>" + "</td>" + "</tr>" + "<tr>";
					});
	updateTime();
}

var removeExercice = function(index) {
	training["exercices"].splice(index, 1);
	updateExercice();
}

var updateTime = function() {
	var totalTime = document.getElementById("totalTimeValue");
	totalTime.innerHTML = "";
	var totalExerciceTime = 0;
	totalExerciceTime = parseInt(totalExerciceTime);
	$.each(training["exercices"], function(index, value) {
		totalExerciceTime += parseInt(value.time);
	});
	var totalHour = (totalExerciceTime / 3600 >> 0);
	var totalMinutes = ((totalExerciceTime - (totalHour * 3600)) / 60 >> 0);
	var totalSecondes = totalExerciceTime - (totalHour * 3600)
			- (totalMinutes * 60);
	totalTime.innerHTML = "<span class=\"glyphicon glyphicon-time\"></span> "
			+ totalHour + ":" + totalMinutes + ":" + totalSecondes + "";
}

var updateSportList = function() {
	$.get("http://localhost:8888/sports", function(data) {
		// displayContent(data);

		var response = JSON.parse(data);
		var myselect = document.getElementById('e1');
		for ( var i in response) {

			var objOption = document.createElement("option");
			// console.log(response[i]);
			objOption.text = response[i].name;
			objOption.value = response[i].id;

			// myselect.add(objOption);
			myselect.options.add(objOption);
		}
	})
};