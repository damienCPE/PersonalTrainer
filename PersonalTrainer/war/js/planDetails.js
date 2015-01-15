function loadPlan() {
	query = window.location.search.substring(1);
	var paramList = query.split("&");
	var param = paramList[0].split("=");
	var trainingId = param[1];
	// console.log(trainingId);
	var param = paramList[1].split("=");
	var trainingTitle = param[1];
	// console.log(trainingTitle);
	var counter = 0;

	trainingTitleH3 = document.getElementById("trainingTitle");
	trainingTitleH3.innerHTML =  "Détails du lan d'entrainement "
			+ decodeURIComponent(trainingTitle);
	// Chargement des exercices
	$
			.get(
					"http://localhost:8888/searchExercise?trainingId="
							+ trainingId,
					function(data) {
						var times;
						counter = 1;
						// console.log(data);
						data = JSON.parse(data);

						exercisesResults = document
								.getElementById("exercises-results");
						var code = "";
						var h, m, s, time;
						var times = [];
						code += "<div class=\"col-md-11 col-sm-12 col-xs-12\">"
								+ "<table class=\"table table-striped table-condensed\">";
						for ( var i in data) {
							// console.log(data[i].time);
							time = parseInt(data[i].time);
							// console.log("set times[" + counter + "] = " +
							// time);
							times[counter] = time;
							// console.log("Verif : times[" + counter + "] = " +
							// times[counter]);
							h = Math.floor(time / 3600);
							m = Math.floor((time % 3600) / 60);
							s = (time % 60);
							// console.log(h+":"+m+":"+s);
							time = "     ";
							if (h != 0) {
								time += h + " h, ";
							}
							if (m != 0) {
								time += m + " min, ";
							}
							if (s != 0) {
								time += s + " sec";
							}

							code += "<tr>"
									+ "<td class=\"col-md-12 col-sm-12 col-xs-12\">"
									+ "<div class=\"row\">"
									+ "<div class=\"col-md-3 col-sm-12 col-xs-12\">"
									+ "<h3>"
									+ data[i].title
									+ "</h3>"
									+ "</div>"
									+ "<div class=\"col-md-2 col-sm-2 col-xs-2\">"
									+ "<p id=\"totalTimeValue\" style=\"margin-top: 25px\">"
									+ "<span class=\"glyphicon glyphicon-time\"></span>"
									+ time
									+ "</p>"
									+ "</div>"
									+ "</div>"
									+ "<div class=\"row\">"
									+ "<div class=\"col-md-1 col-sm-0 col-xs-0\"></div>"
									+ "<div class=\"col-md-6 col-sm-12 col-xs-12\">"
									+ "<p>"
									+ data[i].description
									+ "</p>"
									+ "</div>"
									+ "<div class=\"col-md-3 col-sm-12 col-xs-12\">"
									+ "<div class=\"col-md-12 col-sm-12 col-xs-12\">"
									+ "<div id=\"flipcountdownbox"
									+ counter
									+ "\"></div>"
									+ "</div>"
									+ "<div class=\"col-md-12 col-sm-12 col-xs-12 centered\">"
									+ "<div class=\"btn-group\">"
									+ "<button type=\"button\" class=\"btn btn-default\""
									+ " onclick=\"startCountdown"
									+ counter
									+ "()\">"
									+ "<span class=\"glyphicon glyphicon-play\"></span>"
									+ "</button>"
									+ "<button type=\"button\" class=\"btn btn-default\""
									+ " onclick=\"breakCountdown"
									+ counter
									+ "()\">"
									+ "<span class=\"glyphicon glyphicon-pause\"></span>"
									+ "</button>"
									+ "<button type=\"button\" class=\"btn btn-default\""
									+ " onclick=\"stopCountdown"
									+ counter
									+ "()\">"
									+ "<span class=\"glyphicon glyphicon-stop\"></span>"
									+ "</button>"
									+ "</div>"
									+ "<button type=\"button\" class=\"btn btn-default\""
									+ " onclick=\"reinitCountdown"
									+ counter
									+ "()\">"
									+ "<span class=\"glyphicon glyphicon-repeat\"></span>"
									+ "</button>"
									+ "</div>"
									+ "</div>"
									+ "</div>"
									+ "<div class=\"col-md-12 ol-sm-12 col-xs-12 text-left\">"
									+ "<button type=\"submit\" class=\"btn btn-success btn-lg\">"
									+ "<span class=\"glyphicon glyphicon-ok\"></span>"
									+ "</button>"
									+ "<button type=\"submit\" class=\"btn btn-danger btn-sm\">"
									+ "<span class=\"glyphicon glyphicon-fast-forward\"></span>"
									+ "</button>"
									+ "</div>"
									+ "</td>"
									+ "</tr>";
							counter++;
						}
						code += "</table></div>";
						if (counter == 0) {
							code += "<div class=\"row\">"
									+ "<div class=\"col-md-6 col-sm-6 col-xs-6\">"
									+ "<label> Aucun exercice n'a été trouvé </label>"
									+ "</div>" + "</div>";
						}
						exercisesResults.innerHTML = code;
						essai(counter, times);
					});
}

function essai(counter, times) {
	var code = "";
	// Ajout du timer à zéro pour l'auto stop
	code += "var date=new Date('2.01.2015 00:00:00');"
			+ "var time=date.getTime();";
	// Ajout des variables des compteurs
	for (i = 1; i < counter; i++) {
		time = times[i];
		h = Math.floor(time / 3600);
		m = Math.floor((time % 3600) / 60);
		s = (time % 60);
		// console.log(h+":"+m+":"+s);
		code += "var date" + i + " = new Date('2.01.2015 " + h + ":" + m + ":"
				+ s + "');" + "var time" + i + " = date" + i + ".getTime();"
				+ "var countdown" + i + "=true;" + "initTime" + i + "=date" + i
				+ ".getTime();";
	}
	code += "$(document).ready(function() {";
	for (i = 1; i < counter; i++) {
		time = times[i];
		h = Math.floor(time / 3600);
		m = Math.floor((time % 3600) / 60);
		s = (time % 60);
		// console.log(h+":"+m+":"+s);
		// Ajout de la fonction de décompte
		code += "$(\"#flipcountdownbox" + i + "\").flipcountdown({"
				+ "speedFlip : 60," + "tick : function() {" + "if (time" + i
				+ " >= time && countdown" + i + " == true){" + "if(time" + i
				+ " == time)" + "currentTime = time" + i + ";"
				+ "else currentTime = time" + i + " - 1000;" + "time" + i
				+ " = currentTime;" + "date" + i + " = new Date(currentTime);"
				+ "return date" + i + ";}}" + "});";
	}
	code += "});";
	for (i = 1; i < counter; i++) {
		// Ajout des fonctions start/break/stop/reinit
		code += "function stopCountdown" + i + "(){" + "time" + i + "=time;}"
				+ "function breakCountdown" + i + "(){" + "countdown" + i
				+ " = false;}" + "function startCountdown" + i + "(){"
				+ "countdown" + i + " = true;}" + "function reinitCountdown"
				+ i + "(){" + "time" + i + "=initTime" + i + ";" + "countdown" + i + "=true;}";
	}
	countdownScript = document.getElementById("countdownScript");
	countdownScript.innerHTML = code;
};