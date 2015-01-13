var filter = "";
var exercises = "";
var plans = "";
var planResults;
var exercicesResults;

$("#searchForm").on("submit", function(e) {
	e.preventDefault();
	filter = document.getElementById("searchFilter").value;
	console.log(filter);
	window.location.href='results.html?filter='+filter;
});

function getResultData(){
	//Récupération du filtre
	query = window.location.search.substring(1);
	  var vars = query.split("=");
	  var filter=vars[1];
	  //console.log(filter);
	//Récupération des "plan"
	$.get("http://localhost:8888/searchPlan?filter="+filter, function(data) {
		console.log(data);
		data = JSON.parse(data);
		planResults = document.getElementById("plans-results");
		planResults.innerHTML = "";
		
		for (var i in data)
	    {
	    	planResults.innerHTML += "<div class=\"row\">"
	    		+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
	    		+ "<label> " + data[i].title + " </label>"+ "</div>"
	    		+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
	        	+ "<label> " + data[i].description + " </label>"+ "</div>"
	        	+ "</div>";
	    }
	});
	//Récupération des "exercise"
	$.get("http://localhost:8888/searchExercise?filter="+filter, function(data) {
		console.log(data);
		data = JSON.parse(data);
		planResults = document.getElementById("exercises-results");
		planResults.innerHTML = "";
		
		for (var i in data)
	    {
	    	planResults.innerHTML += "<div class=\"row\">"
	    		+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
	    		+ "<label> " + data[i].title + " </label>"+ "</div>"
	    		+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
	        	+ "<label> " + data[i].description + " </label>"+ "</div>"
	        	+ "</div>";
	    }
	});
}