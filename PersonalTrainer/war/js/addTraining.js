var training = {
	"title" : "",
	"desciption" : "",
	"domain" : "",
	"exercices" : [
	{
	"title" : "",
	"desciption" : "",
	"time" : [ {
		"hour" : 0,
		"minutes" : 0,
		"secondes" : 0
	}
	],
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
	
}

var removeTraining = function() {

}

var updateTime = function() {

}