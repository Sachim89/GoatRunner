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
	/*
	 * var xhr = new XMLHttpRequest(); xhr.open("GET",
	 * "http://localhost:8080/GoatRunner/application/user/login?userId=" + email +
	 * "&password=" + password, true); xhr.setRequestHeader('Content-Type',
	 * 'application/json; charset=UTF-8'); xhr.send();
	 * xhr.addEventListener("readystatechange", processRequest, false);
	 * xhr.onreadystatechange = processRequest;
	 * 
	 * function processRequest(e) { if (xhr.readyState == 4 && xhr.status ==
	 * 200) { window.location.href = "BookingPage.html"; } }
	 */
}
