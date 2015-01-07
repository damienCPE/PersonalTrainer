var results = document.getElementById("results");

function displayContent(response)
{
	response = JSON.parse(response);
	results.innerHTML = "";
    
    var counter = 0;
    for (var i in response)
    {
    	if( (counter % 4) == 0){
    		if (counter != 0){
    			results.innerHTML += "</div>";
    		}
    		results.innerHTML += "<div class= \"col-md-12 col-sm-12 col-xs-12\" style=\"margin-bottom:80px\">";
    	}
    	results.innerHTML += "<div class=\"col-md-3 col-sm-3 col-xs-3\">"
    		+ "<button type=\"submit\" class=\"btn btn-default btn-lg img-thumbnail\">"
        	+ "<img src=\""+ response[i] +".jpg\" class= \"img-thumbnail\" alt=\"Picture not found\" />"
        	+ "</button>"
        	+ "<label> "+ i +" </label>"
        	+ "</div>";
    	counter++;
    }
}

$.get("http://localhost:8888/search", function(data) {
	displayContent(data);
});
