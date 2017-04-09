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
	$.ajax({
		url: "http://localhost:8080/GoatRunner/application/user/signup",
		type: "POST",
		data : JSON.stringify(j),
		contentType :"application/json",
		success: function(resultData) {window.location.href = "BookingPage.html"}
	});
	
}