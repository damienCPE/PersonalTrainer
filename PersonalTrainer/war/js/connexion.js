var Connexion = document.getElementById("Connexion");

function displayConnexionContent(response) {
	response = JSON.parse(response);
	var logged = response.login;

	//console.log(logged);
	
	if (logged == "true") {
		//console.log("Je suis true (" + response.logoutUrl + ")");
		Connexion.innerHTML = "<div class=\" col-md-2 col-sm-2 col-xs-2\">"
			+ "<form class=\"navbar-form navbar-right\" style=\"display:inline-flex\""
			+ "role=\"connexion\">"
			+ "<p class=\"text-info\" style=\"margin-right: 10px; margin-top:6px;\">" + response.loggedUserEmail + "</p>"
			+ "<a class=\"btn btn-default glyphicon glyphicon-log-out\" "
			+ "href=\"http://localhost:8888" + response.logoutUrl
			+ "\" title=\"Se déconnecter\"></a>" + "</form>" + "</div>";
	} else {
		//console.log("Je suis pas true");
		Connexion.innerHTML = "<div class=\" col-md-2 col-sm-2 col-xs-2\">"
				+ "<form class=\"navbar-form navbar-right\" style=\"width: 100%;\""
				+ "role=\"connexion\">"
				+ "<a class=\"btn btn-default btn-social-icon btn-google-plus\""
				+ "href=\""+ response.loginUrlGoogle +"\""
				+ "title=\"Se connecter avec Google Plus\" rel=\"nofollow\"> <i "
				+ "class=\"fa fa-google-plus\"></i>"
				+ "</a> <a class=\"btn btn-default btn-social-icon btn-yahoo\""
				+ "href=\""+ response.loginUrlYahoo +"\""
				+ "title=\"Se connecter avec Yahoo!\" rel=\"nofollow\"> <i "
				+ "class=\"fa fa-yahoo\"></i>"
				+ "</a> <a class=\"btn btn-default btn-social-icon btn-openid\""
				+ "href=\""+ response.loginUrlOpenId +"\""
				+ "title=\"Se connecter avec OpenId\" rel=\"nofollow\"> <i "
				+ "class=\"fa fa-openid\"></i>" + "</a>" + "</form>" + "</div>";
	}
}

$.get("http://localhost:8888/connexion", function(data) {
//	console.log("connexion.js");
	displayConnexionContent(data);
});