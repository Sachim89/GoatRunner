function signup() {

	var studID = document.getElementById("StudentID").value;
	var name = document.getElementById("Name").value;
	var email = document.getElementById("Email").value;
	var password = document.getElementById("Password").value;
	var address = document.getElementById("Address").value;
	var phone = document.getElementById("phone").value;
	var fav_location = document.getElementById("fav").value;
	var quest;
	document.getElementById("questions").addEventListener("change", function() {
		quest = this.value;
	});
	var answer = document.getElementById("answer").value;

	var xhr = new XMLHttpRequest();
	xhr.open("POST",
			"http://localhost:8080/GoatRunner/application/user/signup", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	var j = {
		"name" : name,
		"student_id" : studID,
		"password" : password,
		"phone_number" : phone,
		"address" : address,
		"favourite_location" : fav_location,
		"email_id" : email,
		"security_question" : quest,
		"answer" : answer
	};
	xhr.send(JSON.stringify(j));
	xhr.addEventListener("readystatechange", processRequest, false);
	xhr.onreadystatechange = processRequest;

	function processRequest(e) {
		if (xhr.readyState == 4 && xhr.status == 200) {
			window.location.href = "Login.html";
		}
	}
}