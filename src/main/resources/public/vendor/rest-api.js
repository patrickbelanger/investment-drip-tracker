const currentHost = window.location.origin + "/";

function doGet(endpoint, callback) {
	 $.get(currentHost + endpoint, function(data, status){
		if (status == "success") {
		 	callback(data);
	 	}
	 });
}