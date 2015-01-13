var updateNewsRssList = function() {
	$.get("http://localhost:8888/newsRSS", function(data) {
		// displayContent(data);

		var response = JSON.parse(data);
		var newsRsslist = document.getElementById("newsRssList");
		newsRsslist.innerHTML = "";
		for ( var i in response) {
			newsRsslist.innerHTML += "<p>"+
			"From " + "<a href=\"response[i].link\">"+response[i].link +"</a>" +
			response[i].title +
			response[i].description +
			"</p>"
		}
	})
};