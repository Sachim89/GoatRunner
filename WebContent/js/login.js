function submitform() {
	var studentId = parseInt(document.getElementById("userid").value);
	var password = document.getElementById("password").value;
	// document.getElementById('message').innerHTML = studid;
	// document.cookie = studid;

	$.ajax({
		url : "http://localhost:8080/GoatRunner/application/user/login?userId="
				+ studentId + "&password=" + password,
		type : "GET",
		contentType :"application/json",
		success : function(resultData) {
			window.location.href = "BookingPage.html"
		}
	});
	
}
