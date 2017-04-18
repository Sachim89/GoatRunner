$(document).ready(function () {
	$("#GetStarted").click(function(){
		validate();
	});
	
	//Displays the rules for Student ID when mouse pointer is over ?
	$("#rules").hover(function(){
		$(this).html($('<span style="color:red;">Student ID should be 9 digits and Password should be minimum 6 characters</span>'))
	});
	
});

function validate()
{
	var id = $("#StudentID").val();
	var password = $("#Password").val();
	var isValid = true;
	localStorage.setItem("StudentID",id);
	
	//Check the Password and Student ID
	$('input').filter('[required]').each(function(){
		if($(this).val() == ""){
		$('input').css("border","2px solid red");
		$('input').css("box-shadow","0 0 3px red");
		$('select').css("border","2px solid red");
		$('select').css("box-shadow","0 0 3px red");
		isValid = false;
		return false;
		}
	});
	
	if(password !="" || id != ""){	
			if(id.length < 9 && password.length < 6){
				alert("Student ID should be 9 digits and Password should be minimum 6 characters");
			}
			else if(password.length < 6){
				alert("Password should be minimum 6 characters");
			}
			else if(id.length < 9){
				alert("Student ID should be 9 digits");
			}
			else if(id.length > 9){
				alert("Student ID should be 9 digits");
			}
	}
	
	if(!isValid){alert("Some fields are missing");}
	
	//Send the valid data to Service layer
	if(isValid){
	
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
		success: function(resultData) 
		{window.location.href = "http://localhost:8080/GoatRunner/LoginPage.html";}
	});
	}
}