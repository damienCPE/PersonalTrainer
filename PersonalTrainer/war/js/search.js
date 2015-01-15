var filter = "";
var exercises = "";
var plans = "";
var planResults;
var exercicesResults;

$("#searchForm").on("submit", function(e) {
	e.preventDefault();
	filter = document.getElementById("searchFilter").value;
	console.log(filter);
	window.location.href = 'results.html?filter=' + filter;
});

function getResultData() {
	// Récupération du filtre
	query = window.location.search.substring(1);
	var vars = query.split("=");
	var filter = vars[1];
	var trainingTitle;
	// console.log(filter);
	// Récupération des "plan"
	if (vars[0] == "filter") {
		$
				.get(
						"/searchPlan?filter=" + filter,
						function(data) {
							var counter = 0;
							// console.log(data);
							data = JSON.parse(data);
							planResults = document
									.getElementById("plans-results");
							planResults.innerHTML = "";

							for ( var i in data) {
								trainingTitle = data[i].title;
								planResults.innerHTML += "<div class=\"row\">"
										+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
										+ "<a href=\"planDetails.html?id="
										+ data[i].id
										+ "&title="
										+ trainingTitle
										+ "\"><span class=\"glyphicon glyphicon-calendar\"></span> "
										+ data[i].title
										+ " </a>"
										+ "</div>"
										+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
										+ "<label> " + data[i].description
										+ " </label>" + "</div>" + "</div>";
								counter++;
							}
							if (counter == 0) {
								planResults.innerHTML += "<div class=\"row\">"
										+ "<div class=\"col-md-6 col-sm-6 col-xs-6\">"
										+ "<label> Aucun plan n'a été trouvé </label>"
										+ "</div>" + "</div>";
							}
						});
		// Récupération des "exercise"
		$
				.get(
						"/searchExercise?filter=" + filter,
						function(data) {
							var counter = 0;
							// console.log(data);
							data = JSON.parse(data);
							planResults = document
									.getElementById("exercises-results");
							planResults.innerHTML = "";

							for ( var i in data) {
								planResults.innerHTML += "<div class=\"row\">"
										+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
										+ "<a href=\"planDetails.html?id="
										+ data[i].trainingId
										+ "&title="
										+ trainingTitle
										+ "\"><span class=\"glyphicon glyphicon-time\"></span> "
										+ data[i].title
										+ " </a>"
										+ "</div>"
										+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
										+ "<label> " + data[i].description
										+ " </label>" + "</div>" + "</div>";
								counter++;
							}
							if (counter == 0) {
								planResults.innerHTML += "<div class=\"row\">"
										+ "<div class=\"col-md-6 col-sm-6 col-xs-6\">"
										+ "<label> Aucun exercice n'a été trouvé </label>"
										+ "</div>" + "</div>";
							}
						});
		updateNewsRssList();
	} else {
		$
				.get(
						"/searchPlan?domaineId=" + filter,
						function(data) {
							var counter = 0;
							// console.log(data);
							data = JSON.parse(data);
							planResults = document
									.getElementById("plans-results");
							planResults.innerHTML = "";

							for ( var i in data) {
								trainingTitle = data[i].title;
								planResults.innerHTML += "<div class=\"row\">"
										+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
										+ "<a href=\"planDetails.html?id="
										+ data[i].id
										+ "&title="
										+ trainingTitle
										+ "\"><span class=\"glyphicon glyphicon-calendar\"></span> "
										+ data[i].title
										+ " </a>"
										+ "</div>"
										+ "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
										+ "<label> " + data[i].description
										+ " </label>" + "</div>" + "</div>";
								counter++;
							}
							if (counter == 0) {
								planResults.innerHTML += "<div class=\"row\">"
										+ "<div class=\"col-md-6 col-sm-6 col-xs-6\">"
										+ "<label> Aucun plan n'a été trouvé </label>"
										+ "</div>" + "</div>";
							}
						});
		element = document.getElementById("exercises-result-section");
		element.parentNode.removeChild(element);
		element = document.getElementById("news-result-section");
		element.parentNode.removeChild(element);
	}
}

function displaySport(domainId) {
	// console.log(domainId);
	window.location.href = 'results.html?domaineId=' + domainId;
}