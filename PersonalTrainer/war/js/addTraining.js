var training = {
	"training" : {
		"title" : "Le titre",
		"description" : "La description",
		"domaineId" : "13556777",
		"exercices" : [ {
			"title" : "Titre exercice",
			"description" : "Description exercice",
			"time" : 1,
			"repetitions" : 1,
			"trainingId" : 0
		}, {
			"title" : "Titre exercice",
			"description" : "Description exercice",
			"time" : 1,
			"repetitions" : 1,
			"trainingId" : 0
		}, {
			"title" : "Titre exercice",
			"description" : "Description exercice",
			"time" : 1,
			"repetitions" : 1,
			"trainingId" : 0
		} ]
	}
};

//$(function() {
//    $('form').submit(function() {
//    	
////        $('#result').text(JSON.stringify($('form').serializeObject()));
////        return false;
//    });
//});

var addTraining = function() {
/*	// alert('start');
	training.training.title = document.getElementById('inputTitle').value;
	training.training.description = document.getElementById('inputDescription').value;
	training.training.domainId = document.getElementById('e1').value;
	/*
	 * console.log(training); alert(training);
	 
	$.get("trainingQueue", {
		"training" : training
	});
	
*/	alert('start');
	JSON.stringify($('#formAddTraining').serializeObject());

	
}
$.fn.serializeObject = function()
	{
	    var o = {};
	    var a = this.serializeArray();
	    //console.log(this);
	    console.log(a);
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    console.log(o);
	    return o;
	};
var beforeAddTraining = function() {

}

var addExercice = function() {
	training.exercices.push({
		"title" : document.getElementById('titleDescription').value,
		"desciption" : document.getElementById('exerciceDescription').value,
		"time" : document.getElementById('timeHours').value
				+ document.getElementById('timeMinutes').value
				+ document.getElementById('timeSecondes').value,
		"repetitions" : 10
	});
}

var updateExercice = function() {

}

var removeTraining = function() {

}

var updateTime = function() {

}

var updateSportList = function() {
	$.get("http://localhost:8888/search", function(data) {
		// displayContent(data);

		var response = JSON.parse(data);
		var myselect = document.getElementById('e1');
		console.log(response[0]);
		for ( var i in response) {

			var objOption = document.createElement("option");
			console.log(response[i]);
			objOption.text = response[i].name;
			objOption.value = response[i].id;

			// myselect.add(objOption);
			myselect.options.add(objOption);
		}
	})
};