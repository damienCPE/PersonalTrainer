var training = {
	"title" : "",
	"desciption" : "",
	"domain" : "",
	"exercices" : [ {
		"title" : "",
		"desciption" : "",
		"time" : 0
	} ],
	"time" : 0
}

var addTraining = function() {
	training.title = document.getElementById('inputTitle').value;
	training.description = document.getElementById('inputDescription').value;
	training.domain = document.getElementById('e1').value;
	training.time = document.getElementById('totalTimeValue').value;
}

var beforeAddTraining = function() {

}

var addExercice = function() {
	training.exercices.push({
		"title" : document.getElementById('titleDescription').value,
		"desciption" : document.getElementById('exerciceDescription').value,
		"time" : document.getElementById('timeHours').value
				+ document.getElementById('timeMinutes').value
				+ document.getElementById('timeSecondes').value
	});
}

var removeTraining = function() {

}

var updateTime = function() {

}