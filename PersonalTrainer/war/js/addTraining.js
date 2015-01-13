var training = {};

// $(function() {
// $('form').submit(function() {
//    	
// // $('#result').text(JSON.stringify($('form').serializeObject()));
// // return false;
// });
// });

var addTraining = function() {
/*
 * // alert('start'); training.training.title =
 * document.getElementById('inputTitle').value; training.training.description =
 * document.getElementById('inputDescription').value; training.training.domainId =
 * document.getElementById('e1').value; /* console.log(training);
 * alert(training);
 * 
 * $.get("trainingQueue", { "training" : training });
 * 
 */	
	//alert('start');
	//JSON.stringify($('#formAddTraining').serializeObject());
	
	var jqxhr = $.post( "/trainingQueue",  { training : JSON.stringify($('#formAddTraining').serializeObject())}, function() {
		  //alert( "success" );
		})
		  .done(function() {
		    //alert( "second success" );
		  })
		  .fail(function() {
		    //alert( "error" );
		  })
		  .always(function() {
		    //alert( "finished" );
		});

	
}
$.fn.serializeObject = function()
	{
	    var a = this.serializeArray();
	    // console.log(this);
	    console.log(a);
	    $.each(a, function() {
	        if (training[this.name] !== undefined) {
	            if (!training[this.name].push) {
	            	training[this.name] = [training[this.name]];
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
        	training["exercices"] = [training["exercices"]];
        }
        training["exercices"].push({
		"title" : document.getElementById('titleDescription').value,
		"description" : document.getElementById('exerciceDescription').value,
		"time" : document.getElementById('timeHours').value
				+ document.getElementById('timeMinutes').value
				+ document.getElementById('timeSecondes').value,
		"repetition" : 1
	} || '');
    } else {
    	training["exercices"] = [{
    			"title" : document.getElementById('titleDescription').value,
    			"description" : document.getElementById('exerciceDescription').value,
    			"time" : document.getElementById('timeHours').value
    					+ document.getElementById('timeMinutes').value
    					+ document.getElementById('timeSecondes').value,
    			"repetition" : 1
    		}] || '';
    }
// training.exercices.push({
// "title" : document.getElementById('titleDescription').value,
// "desciption" : document.getElementById('exerciceDescription').value,
// "time" : document.getElementById('timeHours').value
// + document.getElementById('timeMinutes').value
// + document.getElementById('timeSecondes').value,
// "repetitions" : 10
// });
	// console.log('training');
	//console.log(training);
	updateExercice();
}

var updateExercice = function() {
	if(training["exercices"] === undefined)
		return;
	console.log(training["exercices"]);
	var exercicelist = document.getElementById("exerciceList");
	exercicelist.innerHTML = "";
	$.each(training["exercices"], function(index, value) {
		console.log(index);
		console.log(value);
		exercicelist.innerHTML +="<tr>"+
		"<td>"+value.repetition+"</td>"+
		"<td>"+value.title+"</td>"+
		"<td class=\"hidden-xs\"><p>"+value.description+"</p></td>"+
		"<td>"+value.time+"</td>"+
		"<td>"+
			"<button type=\"submit\" class=\"btn btn-danger btn-sm\">"+
				"<span class=\"glyphicon glyphicon-remove\"></span>"+
			"</button>"+
		"</td>"+
	"</tr>"+
	"<tr>";
    });
}

var removeTraining = function() {

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