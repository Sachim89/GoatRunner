
function update()
{
		
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
			success: function(resultData) {
				window.location.href = "http://localhost:8080/GoatRunner/BookingPage.html";
					}
			});
		}

















$(document).ready(function () {
	$("#StudentID").on("change", function() {
		   
		   var studid=$(this).val();
		   $("#StudentID").val(studid);
		});
	
	$("#Name").on("change", function() {
		  
		   var name=$(this).val();
		   $("#Name").val(name);
		});
	
	$("#Email").on("change", function() {
		   
		   var Email=$(this).val();
		   $("#Email").val(Email);
		});
	
	$("#Password").on("change", function() {
		   
		   var Password=$(this).val();
		   $("#Password").val(Password);
		});
	
	$("#Address").on("change", function() {
		  
		   var Address=$(this).val();
		   $("#Address").val(Address);
		});
	
	$("#phone").on("change", function() {
		  
		   var phone=$(this).val();
		   $("#phone").val(phone);
		});
	
	$("#fav").on("change", function() {
		   
		   var fav=$(this).val();
		   $("#fav").val(fav);
		});
	
	$("#questions").on("change", function() {
		   
		   var questions=$(this).val();
		   $("#questions").val(questions);
		});
	
	
	
	$("#answer").on("change", function() {
		  
		   var answer=$(this).val();
		   $("#answer").val(answer);
		});
	
	
	
});