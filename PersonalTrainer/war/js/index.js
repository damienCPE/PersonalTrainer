var loaded = function() {
	$.get("/index", function(data) {
	})
	$.get("/home", function(data) {
		$("#homeMessage").html(data);
	})
}