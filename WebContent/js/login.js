function submitform() {
	var email = document.getElementById("userid").value;
	var password = document.getElementById("password").value;

	var xhr = new XMLHttpRequest();
	xhr.open("GET",
			"http://localhost:8080/GoatRunner/application/user/login?userId="
					+ email + "&password=" + password, true);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	xhr.send();
	xhr.addEventListener("readystatechange", processRequest, false);
	xhr.onreadystatechange = processRequest;

	function processRequest(e) {
		if (xhr.readyState == 4 && xhr.status == 200) {
			window.location = "BookingPage.html";
		}
	}
}
