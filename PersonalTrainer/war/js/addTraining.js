var training = {};

var addTraining = function() {
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
}

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
					"time" : document.getElementById('timeHours').value
							+ document.getElementById('timeMinutes').value
							+ document.getElementById('timeSecondes').value,
					"repetition" : 1
				}
						|| '');
	} else {
		training["exercices"] = [ {
			"title" : document.getElementById('titleDescription').value,
			"description" : document.getElementById('exerciceDescription').value,
			"time" : document.getElementById('timeHours').value
					+ document.getElementById('timeMinutes').value
					+ document.getElementById('timeSecondes').value,
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
	11
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
}

var removeExercice = function(index) {
	training["exercices"].splice(index, 1);
	updateExercice();
}

var updateTime = function() {

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